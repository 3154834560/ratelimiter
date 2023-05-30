package com.example.demo.infrastructure.rest;

/**
 * 自定义响应码接口
 *
 * @author 王景阳
 * @date 2023-04-05 10:10
 */
public interface IRestCode {
    Integer getCode();

    String getMsg();

    Boolean getFlag();
}
