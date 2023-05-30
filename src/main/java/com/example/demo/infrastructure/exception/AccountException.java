package com.example.demo.infrastructure.exception;

/**
 * 账户异常类
 *
 * @author 王景阳
 * @date 2023-04-06 10:48
 */
public class AccountException extends RuntimeException {
    public AccountException(String msg) {
        super(msg);
    }

    public AccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
