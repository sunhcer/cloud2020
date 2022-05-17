package com.atguigu.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableHystrix
@MapperScan("com.atguigu.springcloud.dao")
public class OrderMain8182 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain8182.class,args);
    }
}
