package com.atguigu.springcloud.annotation;

import java.lang.annotation.*;
/**
 * controller层日志
 * @author sunhcer
 * @date 2021/01/17 01:09
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MemoryCaculateLog {
    String name() default "测试方法";
    boolean intoDB() default false;

}
