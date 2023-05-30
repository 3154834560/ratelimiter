package com.example.demo.infrastructure.ratelimiter.impl;

import com.example.demo.infrastructure.ratelimiter.RateLimiter;
import com.example.demo.infrastructure.ratelimiter.annotations.Limiter;
import lombok.extern.slf4j.Slf4j;

/**
 * 漏桶限速器
 *
 * @author 王景阳
 * @date 2023-04-07 17:15
 */
@Slf4j
public class LeakyBucketRateLimiter implements RateLimiter {
    public LeakyBucketRateLimiter(Limiter limiter) {
        this.capacity = limiter.capacity();
        this.water = limiter.water();
        this.rate = limiter.rate();
    }

    /**
     * 漏桶的容量，默认是300
     */
    private final long capacity;
    /**
     * 当前桶中的水量，默认是0
     */
    private long water;
    /**
     * 漏桶出水速率，单位：请求/秒
     */
    private final int rate;
    /**
     * 上次漏水时间戳
     */
    private long lastLeakTimestamp = System.currentTimeMillis();

    @Override
    public boolean pass() {
        leak();
        if (water + 1 <= capacity) {
            water++;
            return true;
        }
        return false;
    }

    /**
     * 根据当前时间和上次漏水时间戳计算出应该漏出的水量，然后更新桶中的水量和漏水时间戳等状态
     */
    public void leak() {
        long currentTime = System.currentTimeMillis();
        long leakedWater = (currentTime - lastLeakTimestamp) / 1000 * rate;
        if (leakedWater > 0) {
            water = Math.max(0, water - leakedWater);
            lastLeakTimestamp = currentTime;
        }
    }
}
