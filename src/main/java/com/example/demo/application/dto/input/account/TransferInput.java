package com.example.demo.application.dto.input.account;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 转账-请求参数
 *
 * @author 王景阳
 * @date 2023-04-05 22:32
 */
@Data
public class TransferInput {
    /**
     * 转账人id
     */
    @NotNull
    private Long customerId;
    /**
     * 幂等key
     */
    @NotBlank
    private String idempotentKey;
    /**
     * 转出账户id
     */
    @NotNull
    private Long accountId;
    /**
     * 转出账户密码
     */
    @NotNull
    private String password;
    /**
     * 转入账户id
     */
    @NotNull
    private Long targetAccountId;
    /**
     * 转账金额
     */
    @NotNull
    private Double amount;
    /**
     * 备注
     */
    private String remark;
}
