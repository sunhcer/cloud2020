package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * quartz集群2
 * @author sunhcer
 * @date 2021/02/15 20:13
 **/

@SpringBootApplication
@EnableDiscoveryClient
public class QuartzMain3002 {
    public static void main(String[] args) {
        SpringApplication.run(QuartzMain3002.class,args);
    }
}
