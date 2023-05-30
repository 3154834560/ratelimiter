package com.example.demo.infrastructure.rest;

import java.io.Serializable;

/**
 * @author 王景阳
 * @date 2023-04-05 10:10
 */
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;
    private Boolean success;
    private T data;

    protected R() {
    }

    protected R(Integer code, String msg, Boolean success, T data) {
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.data = data;
    }

    public static <T> R<T> data(T data) {
        return new R<>(RestCode.SUCCESS.getCode(), RestCode.SUCCESS.getMsg(), RestCode.SUCCESS.getFlag(), data);
    }

    public static R<?> ok(String msg) {
        return new R<>(RestCode.SUCCESS.getCode(), msg, RestCode.SUCCESS.getFlag(), null);
    }

    public static R<?> fail(String msg) {
        return new R<>(RestCode.FAILURE.getCode(), msg, RestCode.FAILURE.getFlag(), null);
    }

    public static R<?> fail() {
        return new R<>(RestCode.TOKEN_FAILURE.getCode(), RestCode.TOKEN_FAILURE.getMsg(), RestCode.TOKEN_FAILURE.getFlag(), null);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "R{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
