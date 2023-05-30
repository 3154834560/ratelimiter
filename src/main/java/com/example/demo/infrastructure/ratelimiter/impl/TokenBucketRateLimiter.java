package com.example.demo.infrastructure.ratelimiter.impl;

import com.example.demo.infrastructure.ratelimiter.RateLimiter;
import com.example.demo.infrastructure.ratelimiter.annotations.Limiter;
import lombok.extern.slf4j.Slf4j;

/**
 * 令牌桶限速器
 *
 * @author 王景阳
 * @date 2023-04-06 21:46
 */
@Slf4j
public class TokenBucketRateLimiter implements RateLimiter {
    public TokenBucketRateLimiter(Limiter limiter) {
        this.capacity = limiter.capacity();
        this.rate = limiter.rate();
        this.tokens = limiter.tokens();
    }

    /**
     * 令牌桶容量
     */
    private final long capacity;
    /**
     * 令牌生成速率，单位：令牌/秒
     */
    private final int rate;
    /**
     * 当前令牌数量
     */
    private long tokens;

    /**
     * 上次令牌生成时间戳
     */
    private long lastRefreshTimestamp = System.currentTimeMillis();

    @Override
    public boolean pass() {
        refreshToken();
        boolean res = false;
        if (tokens > 0) {
            tokens--;
            res = true;
        }
        return res;
    }

    /**
     * 刷新令牌，计算令牌数量的逻辑是按照令牌生成速率每秒钟生成一定数量的令牌
     */
    public void refreshToken() {
        long currentTime = System.currentTimeMillis();
        long generatedTokens = (currentTime - lastRefreshTimestamp) / 1000 * rate;
        tokens = Math.min(capacity, tokens + generatedTokens);
        lastRefreshTimestamp = currentTime;
    }
}
