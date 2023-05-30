package com.example.demo.infrastructure.ratelimiter.impl;

import com.example.demo.infrastructure.ratelimiter.RateLimiter;
import com.example.demo.infrastructure.ratelimiter.annotations.Limiter;
import lombok.extern.slf4j.Slf4j;

/**
 * 固定窗口限速器
 *
 * @author 王景阳
 * @date 2023-04-07 8:18
 */
@Slf4j
public class FixedWindowsRateLimiter implements RateLimiter {
    public FixedWindowsRateLimiter(Limiter limiter) {
        this.windowSize = limiter.windowSize();
        this.threshold = limiter.threshold();
    }

    /**
     * 统计请求数
     */
    public int counter = 0;
    /**
     * 固定时间窗口大小，默认是1000ms
     */
    public long windowSize;

    /**
     * 窗口阀值，默认为300
     */
    public final long threshold;
    /**
     * 上次counter刷新时间戳
     */
    private long lastRefreshTimestamp = System.currentTimeMillis();

    @Override
    public boolean pass() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastRefreshTimestamp > windowSize) {
            counter = 0;
            lastRefreshTimestamp = currentTime;
        }
        if (counter < threshold) {
            counter++;
            return true;
        }
        return false;
    }
}
