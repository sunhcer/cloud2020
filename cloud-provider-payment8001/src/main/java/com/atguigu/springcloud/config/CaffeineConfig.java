package com.atguigu.springcloud.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * caffeine 配置类
 * @author sunhcer
 * @date 2021/05/30 15:46
 **/
@Configuration
public class CaffeineConfig {

    @Bean
    public Cache<String, Object> initCaffeineCache(){
        return Caffeine.newBuilder()
                //设置最后一次写入或者访问后,经过多久过期
        .expireAfterWrite(60, TimeUnit.SECONDS)
                //初始化缓存空间大小
        .initialCapacity(100)
                //缓存的最大条数
        .maximumSize(1000)
                .build();
    }
}
