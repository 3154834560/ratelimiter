package com.example.demo.config;

import com.example.demo.infrastructure.exception.AccountException;
import com.example.demo.infrastructure.exception.RateLimiterException;
import com.example.demo.infrastructure.rest.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 王景阳
 * @date 2022/10/27 20:44
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RateLimiterException.class)
    public R<?> handleException(RateLimiterException e) {
        log.error(e.getMessage(), e);
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(value = AccountException.class)
    public R<?> handleException(AccountException e) {
        log.error(e.getMessage(), e);
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public R<?> handleException(Exception e) {
        log.error("TODO:兜底处理---{}", e.getMessage(), e);
        return R.fail(e.getMessage());
    }
}
