package com.atguigu.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis配置
 * @author sunhcer
 * @date 2021/01/18 01:36
 **/
@Configuration
@MapperScan({"com.atguigu.springcloud.dao"})
public class MybatisConfig {

}
