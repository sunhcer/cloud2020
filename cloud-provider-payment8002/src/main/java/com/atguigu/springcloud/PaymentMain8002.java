package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient//			iv. 服务提供者启动类增加@client
public class PaymentMain8002 {
    public static void main(String[] args) {
        //mainbo快捷键---------用不了
        SpringApplication.run(PaymentMain8002.class,args);
    }
}
