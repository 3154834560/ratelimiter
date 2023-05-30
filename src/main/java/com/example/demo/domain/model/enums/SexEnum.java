package com.example.demo.domain.model.enums;

import lombok.Getter;

/**
 * 性别枚举类型
 *
 * @author 王景阳
 * @date 2023-03-25 17:56
 */
@Getter
public enum SexEnum {
    MALE("男"),
    FEMALE("女");

    SexEnum(String sex) {
        this.sex = sex;
    }

    private final String sex;
}
