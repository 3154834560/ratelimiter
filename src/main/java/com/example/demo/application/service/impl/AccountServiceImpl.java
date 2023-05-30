package com.example.demo.application.service.impl;

import com.example.demo.application.dto.input.account.TransferInput;
import com.example.demo.application.dto.output.account.TransferOutput;
import com.example.demo.application.service.AccountService;
import com.example.demo.domain.model.Account;
import com.example.demo.domain.model.Bill;
import com.example.demo.domain.model.enums.BillStatusEnum;
import com.example.demo.domain.repository.AccountRepository;
import com.example.demo.domain.repository.BillRepository;
import com.example.demo.infrastructure.exception.AccountException;
import com.example.demo.infrastructure.tool.JsonTool;
import com.example.demo.infrastructure.tool.SecurityTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

/**
 * 账户服务类
 *
 * @author 王景阳
 * @date 2023-04-06 10:38
 */
@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final BillRepository billRepository;
    private final RedisTemplate<Object, Object> redisTemplate;

    /**
     * 转账
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public TransferOutput transfer(TransferInput input) {
        TransferOutput output;
        Object o = redisTemplate.opsForValue().get(input.getIdempotentKey());
        if (o != null) {
            log.info("幂等key[{}]生效！", input.getIdempotentKey());
            output = JsonTool.toBean(o, TransferOutput.class);
        } else {
            Account account = accountRepository.getById(input.getAccountId());
            Account targetAccount = accountRepository.getById(input.getTargetAccountId());
            verifyIdentityAndStatus(input, account, targetAccount);
            transferFund(account, targetAccount, input.getAmount());
            output = TransferOutput.copy(generateBill(input, targetAccount), account, targetAccount);
        }
        redisTemplate.opsForValue().set(input.getIdempotentKey(), output, Duration.ofSeconds(10));
        return output;
    }

    /**
     * 验证用户身份和账户状态
     */
    private void verifyIdentityAndStatus(TransferInput input, Account account, Account targetAccount) {
        log.info("验证用户身份和账户状态！");
        account.verifyCustomer(input.getCustomerId());
        account.verifyStatus();
        targetAccount.verifyStatus();
        if (account.getId().equals(targetAccount.getId())) {
            throw new AccountException("转出账户和转入账户相同！");
        }
        // 数据库中账户密码都是 wjy
        if (!SecurityTool.matches(input.getPassword(), account.getPassword())) {
            throw new AccountException(String.format("账户[%s]密码错误！", account.getAccountNumber()));
        }
    }

    /**
     * 扣减资金
     */
    private void transferFund(Account account, Account targetAccount, Double amount) {
        log.info("账户[{}]开始向账户[{}]转账！", account.getAccountNumber(), targetAccount.getAccountNumber());
        account.reducedFund(amount);
        targetAccount.increaseFund(amount);
        updateFund(account);
        updateFund(targetAccount);
        log.info("账户[{}]转账完毕！", account.getAccountNumber());
    }

    /**
     * 更新资金
     */
    private void updateFund(Account account) {
        if (!accountRepository.updateFund(account)) {
            throw new AccountException(String.format("账户[%s]异常！", account.getAccountNumber()));
        }
    }

    /**
     * 生成账单
     */
    private Bill generateBill(TransferInput input, Account targetAccount) {
        Bill bill = new Bill(input.getCustomerId(), input.getAccountId(), input.getTargetAccountId(), input.getAmount());
        bill.setTargetCustomerId(targetAccount.getCustomerId());
        bill.setRemark(input.getRemark());
        bill.setStatus(BillStatusEnum.succeed);
        billRepository.save(bill);
        return bill;
    }
}
