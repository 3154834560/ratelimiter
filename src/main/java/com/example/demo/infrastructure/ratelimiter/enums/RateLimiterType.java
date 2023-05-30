package com.example.demo.infrastructure.ratelimiter.enums;

import lombok.Getter;

/**
 * 限速器类型
 *
 * @author 王景阳
 * @date 2023-04-07 8:11
 */
@Getter
public enum RateLimiterType {
    fixed_windows(1, "固定窗口"),
    sliding_windows(2, "滑动窗口"),
    leaky_bucket(3, "漏桶"),
    token_bucket(4, "令牌桶");

    RateLimiterType(int code, String type) {
        this.code = code;
        this.type = type;
    }

    private final int code;
    private final String type;
}
