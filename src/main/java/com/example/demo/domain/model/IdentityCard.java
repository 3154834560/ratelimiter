package com.example.demo.domain.model;

import com.example.demo.domain.model.acomm.BaseEntity;
import com.example.demo.domain.model.enums.SexEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 身份证表
 *
 * @author 王景阳
 * @date 2023-04-05 21:05
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IdentityCard extends BaseEntity {
    public IdentityCard(Long customerId) {
        super();
        this.customerId = customerId;
    }

    /**
     * 所属客户id
     */
    private Long customerId;
    /**
     * 姓名
     */
    @Setter
    private String name;
    /**
     * 性别
     */
    @Setter
    private SexEnum sex;
    /**
     * 民族
     */
    @Setter
    private String ethnicGroup;
    /**
     * 生日
     */
    @Setter
    private LocalDateTime birthday;
    /**
     * 住址
     */
    @Setter
    private String address;
    /**
     * 身份证号
     */
    @Setter
    private String idNumber;
    /**
     * 签发机关
     */
    @Setter
    private String issuingAuthority;
    /**
     * 有效期限-开始时间
     */
    @Setter
    private LocalDateTime beginAt;
    /**
     * 有效期限-截至时间
     */
    @Setter
    private LocalDateTime endAt;
}
