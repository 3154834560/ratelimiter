package com.example.demo.infrastructure.ratelimiter.impl;

import com.example.demo.infrastructure.ratelimiter.RateLimiter;
import com.example.demo.infrastructure.ratelimiter.annotations.Limiter;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 滑动窗口限速器
 *
 * @author 王景阳
 * @date 2023-04-07 8:34
 */
@Slf4j
public class SlidingWindowsRateLimiter implements RateLimiter {
    public SlidingWindowsRateLimiter(Limiter limiter) {
        this.subCycleNumber = limiter.subCycleNumber();
        this.windowSize = limiter.windowSize();
        this.threshold = limiter.threshold();
    }

    /**
     * 滑动时间窗口划分的小周期数量，默认为10
     */
    private final int subCycleNumber;
    /**
     * 窗口阀值，默认为300
     */
    private final long threshold;
    /**
     * 滑动时间窗口大小，默认是1000ms
     */
    public long windowSize;

    /**
     * 计数器, key为当前窗口的开始时间值秒，value为当前窗口的计数
     */
    private final TreeMap<Long, Integer> counters = new TreeMap<>();

    @Override
    public boolean pass() {
        long currentTime = System.currentTimeMillis();
        //当前窗口总请求数
        int currentWindowNum = countCurrentWindow(currentTime);
        //超过阀值限流
        if (currentWindowNum >= threshold) {
            return false;
        }
        //计数器+1
        counters.merge(currentTime, 1, Integer::sum);
        return true;
    }

    /**
     * 统计当前窗口的请求数
     */
    private int countCurrentWindow(long currentWindowTime) {
        //计算窗口开始位置
        long startTime = currentWindowTime - (subCycleNumber - 1) * windowSize / subCycleNumber;
        int count = 0;

        //遍历存储的计数器
        Iterator<Map.Entry<Long, Integer>> iterator = counters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Integer> entry = iterator.next();
            // 删除无效过期的子窗口计数器
            if (entry.getKey() < startTime) {
                iterator.remove();
            } else {
                //累加当前窗口的所有计数器之和
                count = count + entry.getValue();
            }
        }
        return count;
    }
}
