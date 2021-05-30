package com.atguigu.springcloud;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


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

    /**
     * 逻辑删除注入器（3.1.2版本的MyBatis-Plus，默认已经配置了，所以可以不用配置）
     * 注意，如果配置了这句，自定义Sql注入器就会找到2个ISqlInjector的实现类，会导致注入失败而抛异常，
     * 所以版本符合的情况下，就不要加了
     */
//    @Bean
//    public ISqlInjector sqlInjector() {
//        return new LogicSqlInjector();
//    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
