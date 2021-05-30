package com.atguigu.springcloud.service;

@FunctionalInterface
public interface MyFunction {
    //该抽象方法 的 实现对传进来的字符串做一系列操作
    public String getValue(String string);
}
