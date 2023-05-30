package com.example.demo.infrastructure.ratelimiter;

import com.example.demo.infrastructure.ratelimiter.annotations.Limiter;
import com.example.demo.infrastructure.ratelimiter.enums.RateLimiterType;
import com.example.demo.infrastructure.ratelimiter.impl.FixedWindowsRateLimiter;
import com.example.demo.infrastructure.ratelimiter.impl.LeakyBucketRateLimiter;
import com.example.demo.infrastructure.ratelimiter.impl.SlidingWindowsRateLimiter;
import com.example.demo.infrastructure.ratelimiter.impl.TokenBucketRateLimiter;

/**
 * 限速器
 *
 * @author 王景阳
 * @date 2023-04-06 21:40
 */
public interface RateLimiter {
    /**
     * 验证请求是否可以通过
     */
    boolean pass();

    static RateLimiter getInstance(RateLimiterType rateLimiterType, Limiter limiter) {
        switch (rateLimiterType) {
            case fixed_windows:
                return new FixedWindowsRateLimiter(limiter);
            case sliding_windows:
                return new SlidingWindowsRateLimiter(limiter);
            case leaky_bucket:
                return new LeakyBucketRateLimiter(limiter);
            default:
                return new TokenBucketRateLimiter(limiter);
        }
    }
}
