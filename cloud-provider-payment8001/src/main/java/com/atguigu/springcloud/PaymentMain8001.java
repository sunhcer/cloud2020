package com.atguigu.springcloud;

import com.atguigu.springcloud.config.AbstractBeanFinder;
import com.atguigu.springcloud.config.BeanFinder;
import org.apache.commons.collections4.MapUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Map;


@SpringBootApplication
@EnableEurekaClient//			iv. 服务提供者启动类增加@client
@EnableDiscoveryClient
@MapperScan("com.atguigu.springcloud.dao")
public class PaymentMain8001 {
    public static void main(String[] args) {
        //mainbo快捷键---------用不了
        SpringApplication.run(PaymentMain8001.class,args);
    }
    
    @Bean
    public Redisson redisson(){
        //单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379").setDatabase(0);
        return (Redisson)Redisson.create(config);
    }

    @Bean
    public BeanFinder beanFinder(@Autowired ApplicationContext context) {
        return new AbstractBeanFinder() {
            @Override
            protected <T> Collection<T> findBeans(Class<T> targetClass) {
                Map<String, T> beans = context.getBeansOfType(targetClass);
                return MapUtils.isEmpty(beans) ? null : beans.values();
            }
        };
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
