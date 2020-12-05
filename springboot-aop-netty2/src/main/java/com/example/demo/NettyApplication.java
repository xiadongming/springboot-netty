package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: xiadongming
 * @Date: 2020/11/29 23:03
 * @描述:
 */
@MapperScan("com.example.demo.mapper")
@SpringBootApplication(scanBasePackages = {"com.example.demo"})
public class NettyApplication {
    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class, args);
    }
}
