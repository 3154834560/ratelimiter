package com.example.demo.domain.model.enums;

import lombok.Getter;

/**
 * 账户状态枚举类
 *
 * @author 王景阳
 * @date 2023-04-06 11:42
 */
@Getter
public enum AccountStatusEnum {
    normal(1, "正常"),
    freeze(2, "已冻结");

    AccountStatusEnum(int code, String msg) {
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
