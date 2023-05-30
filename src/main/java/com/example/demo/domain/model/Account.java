package com.example.demo.domain.model;

import com.example.demo.domain.model.acomm.BaseEntity;
import com.example.demo.domain.model.enums.AccountStatusEnum;
import com.example.demo.infrastructure.exception.AccountException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 账户表
 *
 * @author 王景阳
 * @date 2023-04-05 21:00
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity {
    public Account(Long customerId, String accountNumber) {
        super();
        this.customerId = customerId;
        this.accountNumber = accountNumber;
    }

    /**
     * 所属用户id
     */
    private Long customerId;
    /**
     * 账户号
     */
    private String accountNumber;
    /**
     * 账户密码
     */
    @Setter
    private String password;
    /**
     * 账户拥有资金
     */
    private Double fund;
    /**
     * 账户类型id
     */
    @Setter
    private Long typeId;
    /**
     * 账户状态
     */
    @Setter
    private AccountStatusEnum status = AccountStatusEnum.normal;

    /**
     * 增加资金
     */
    public void increaseFund(Double fund) {
        this.fund += fund;
    }

    /**
     * 扣除资金
     */
    public void reducedFund(Double fund) {
        this.fund -= fund;
        if (this.fund < 0) {
            throw new AccountException(String.format("账户[%s]资金不足！", this.accountNumber));
        }
    }

    /**
     * 验证账户状态
     */
    public void verifyStatus() {
        if (!status.equals(AccountStatusEnum.normal)) {
            throw new AccountException(String.format("账户[%s]：%s,无法转账！", this.accountNumber, status.getMsg()));
        }
    }

    /**
     * 验证顾客身份
     */
    public void verifyCustomer(Long customerId) {
        if (!customerId.equals(this.customerId)) {
            throw new AccountException(String.format("账户[%s]不属于用户[%s]！", this.accountNumber, customerId));
        }
    }
}
