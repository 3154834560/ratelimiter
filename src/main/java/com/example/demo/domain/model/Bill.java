package com.example.demo.domain.model;

import com.example.demo.domain.model.acomm.BaseEntity;
import com.example.demo.domain.model.enums.BillStatusEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 账单表
 *
 * @author 王景阳
 * @date 2023-04-06 10:33
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bill extends BaseEntity {
    public Bill(Long customerId, Long accountId, Long targetAccountId, Double amount) {
        super();
        this.customerId = customerId;
        this.accountId = accountId;
        this.targetAccountId = targetAccountId;
        this.amount = -amount;
    }

    /**
     * 转账人id
     */
    private Long customerId;
    /**
     * 转出账户id
     */
    private Long accountId;
    /**
     * 收账人id
     */
    @Setter
    private Long targetCustomerId;
    /**
     * 转入账户id
     */
    private Long targetAccountId;
    /**
     * 转账金额
     */
    private Double amount;
    /**
     * 备注
     */
    @Setter
    private String remark;
    /**
     * 订单状态
     */
    @Setter
    private BillStatusEnum status = BillStatusEnum.not_started;
}
