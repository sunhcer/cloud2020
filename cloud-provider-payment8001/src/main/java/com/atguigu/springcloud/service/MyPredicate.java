package com.atguigu.springcloud.service;


@FunctionalInterface
public interface MyPredicate<T> {
    //只能有一个抽象接口
    public boolean test(T t);
//    public boolean test1(T t);

}
