package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

/**
 * @author 王景阳
 * @date 2023-04-05 10:10
 */
@MapperScan(
        //指定扫描包
        basePackages = {"com.example.demo.domain.repository"},
        //限定扫描接口
        annotationClass = Repository.class)
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
