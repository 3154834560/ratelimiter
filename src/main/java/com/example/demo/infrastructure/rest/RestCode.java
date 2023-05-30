package com.example.demo.infrastructure.rest;

/**
 * 自定义响应码实现(最简)
 *
 * @author 王景阳
 * @date 2023-04-05 10:10
 */
public enum RestCode implements IRestCode {
    SUCCESS(200, "操作成功", true),
    FAILURE(500, "操作失败", false),
    TOKEN_FAILURE(401, "token失败", false);

    private final Integer code;
    private final String msg;
    private final Boolean flag;

    RestCode(Integer code, String msg, Boolean flag) {
        this.code = code;
        this.msg = msg;
        this.flag = flag;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public Boolean getFlag() {
        return flag;
    }
}
