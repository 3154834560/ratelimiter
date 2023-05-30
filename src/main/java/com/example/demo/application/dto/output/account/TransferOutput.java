package com.example.demo.application.dto.output.account;

import com.example.demo.domain.model.Account;
import com.example.demo.domain.model.Bill;
import com.example.demo.domain.model.Customer;
import com.example.demo.domain.model.enums.BillStatusEnum;
import com.example.demo.domain.repository.CustomerRepository;
import com.example.demo.infrastructure.util.IocUtil;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 转账-响应参数
 *
 * @author 王景阳
 * @date 2023-04-06 18:19
 */
@Data
public class TransferOutput {
    /**
     * 账单id
     */
    private Long billId;
    /**
     * 转账人id
     */
    private Long customerId;
    /**
     * 转账人用户名
     */
    private String customerName;
    /**
     * 转出账户号
     */
    private String accountNumber;
    /**
     * 收账人id
     */
    private Long targetCustomerId;
    /**
     * 收账人用户名
     */
    private String targetCustomerName;
    /**
     * 转入账户号
     */
    private String targetAccountNumber;
    /**
     * 账单创建时间
     */
    private LocalDateTime billCreateAt;
    /**
     * 转账今额
     */
    private Double amount;
    /**
     * 订单状态
     */
    private BillStatusEnum status;

    public static TransferOutput copy(Bill bill, Account account, Account targetAccount) {
        TransferOutput output = new TransferOutput();
        output.setBillId(bill.getId());
        output.setAccountNumber(account.getAccountNumber());
        output.setTargetAccountNumber(targetAccount.getAccountNumber());
        output.setBillCreateAt(bill.getCreateAt());
        output.setAmount(bill.getAmount());
        output.setStatus(bill.getStatus());
        addCustomerInfo(output, bill);
        return output;
    }

    private static void addCustomerInfo(TransferOutput output, Bill bill) {
        CustomerRepository customerRepository = IocUtil.get(CustomerRepository.class);
        Customer customer = customerRepository.getById(bill.getCustomerId());
        Customer targetCustomer = customerRepository.getById(bill.getTargetCustomerId());
        output.setCustomerId(bill.getCustomerId());
        output.setCustomerName(customer.getName());
        output.setTargetCustomerId(bill.getTargetCustomerId());
        output.setTargetCustomerName(targetCustomer.getName());
    }
}
