package com.example.demo.domain.model.enums;

import lombok.Getter;

/**
 * 订单状态枚举类
 *
 * @author 王景阳
 * @date 2023-04-06 18:33
 */
@Getter
public enum BillStatusEnum {
    not_started(1, "未开始"),
    in_progress(2, "进行中"),
    succeed(3, "交易成功");

    BillStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 状态码
     */
    private final int code;
    /**
     * 状态信息
     */
    private final String msg;
}
