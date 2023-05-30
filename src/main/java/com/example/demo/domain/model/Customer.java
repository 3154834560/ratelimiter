package com.example.demo.domain.model;

import com.example.demo.domain.model.acomm.BaseEntity;
import com.example.demo.domain.model.enums.SexEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 客户表
 *
 * @author 王景阳
 * @date 2023-04-05 20:50
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends BaseEntity {
    public Customer(String name, String password, SexEnum sex, String phone) {
        super();
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.creditScore = 100.0f;
    }

    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private SexEnum sex;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像名称-带后缀
     */
    private String headPortraitName;
    /**
     * 信誉分
     */
    private Float creditScore;
    /**
     * 客户类型id
     */
    private Long typeId;
}
