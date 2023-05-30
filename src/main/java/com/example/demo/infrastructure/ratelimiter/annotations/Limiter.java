package com.example.demo.infrastructure.ratelimiter.annotations;

import com.example.demo.infrastructure.ratelimiter.enums.RateLimiterType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 限速器注解
 *
 * @author 王景阳
 * @date 2023-04-07 20:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Limiter {
    /**
     * 资源的key
     * 作用：不同的接口，不同的流量控制
     */
    String key() default "";

    /**
     * 窗口大小
     * 单位：毫秒
     * 所属类型：fixed_windows，sliding_windows
     */
    long windowSize() default 1000;

    /**
     * 阈值
     * 所属类型：fixed_windows，sliding_windows
     */
    long threshold() default 300;

    /**
     * 滑动时间窗口划分的小周期数量
     * 所属类型：sliding_windows
     */
    int subCycleNumber() default 10;

    /**
     * 桶的大小
     * 所属类型：leaky_bucket，token_bucket
     */
    long capacity() default 300;

    /**
     * 初始桶中的水量
     * 所属类型：leaky_bucket
     */
    long water() default 0;

    /**
     * 单位时间，漏水速率/生成令牌速率
     * 所属类型：leaky_bucket，token_bucket
     */
    int rate() default 300;

    /**
     * 初始令牌的数量
     * 所属类型：token_bucket
     */
    long tokens() default 300;

    /**
     * 限速器类型
     */
    RateLimiterType type() default RateLimiterType.token_bucket;

    /**
     * 获取锁的尝试时间
     */
    long tryTime() default 1000;

    /**
     * 获取锁的尝试时间单位， 默认:毫秒
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

    /**
     * 被限速的提示语
     */
    String msg() default "系统繁忙,请稍后再试！";
}
