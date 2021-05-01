package com.atguigu.springcloud.mode.strategy;

/**
 * 调用过程
 * @author sunhcer.shi
 * @date 2021/04/13 14:15
 **/

public class Client {

    public static void main(String[] args) {
        //声明一个具体的策略
        Concretegy1 concretegy1 = new Concretegy1();
        //声明上下文对象
        Context context = new Context(concretegy1);
        context.doAnything();
    }
}
