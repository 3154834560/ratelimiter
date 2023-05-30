package com.example.demo.domain.model;

import com.example.demo.domain.model.acomm.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 账户类型
 *
 * @author 王景阳
 * @date 2023-04-05 21:22
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountType extends BaseEntity {
    public AccountType(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    /**
     * 账户类型名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
}
