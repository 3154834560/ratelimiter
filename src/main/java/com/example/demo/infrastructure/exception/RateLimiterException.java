package com.example.demo.infrastructure.exception;

/**
 * 限速器异常类
 *
 * @author 王景阳
 * @date 2023-04-06 10:48
 */
public class RateLimiterException extends RuntimeException {
    public RateLimiterException(String msg) {
        super(msg);
    }

    public RateLimiterException(String message, Throwable cause) {
        super(message, cause);
    }
}
