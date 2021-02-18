package com.atguigu.springcloud.custom;

import org.springframework.context.annotation.Configuration;

/**
 * 自定义启动器的bean
 * @author sunhcer
 * @date 2021/01/26 22:39
 **/
@Configuration
public class MyStarter {
    static{
        System.out.println("-----------------------------");
        System.out.println("-----------MyStarter初始化----------");
    }

}
