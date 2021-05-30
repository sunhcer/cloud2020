package com.atguigu.springcloud.service.impl;

@FunctionalInterface
public interface MyFunction2<T,R> {
    public R getValue(T t1,T t2);
}
