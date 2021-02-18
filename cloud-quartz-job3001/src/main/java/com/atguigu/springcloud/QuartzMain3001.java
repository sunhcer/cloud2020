package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * quartz启动类
 * @author sunhcer
 * @date 2021/02/05 21:28
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class QuartzMain3001 {
    public static void main(String[] args) {
        SpringApplication.run(QuartzMain3001.class);
    }
}
