package com.atguigu.springcloud.mode.strategy.strategyfactory;

/**
 * 策略工厂模式
 * @author sunhcer.shi
 * @date 2021/04/13 14:46
 **/

public class Client {
    public static void main(String[] args) {

        ConcreteStrategy1 concreteStrategy1 = new ConcreteStrategy1();
        ConcreteStrategy2 concreteStrategy2 = new ConcreteStrategy2();
        //策略标识 ---> 手动注册
        Context.register("strategy1",concreteStrategy1);
        Context.register("strategy2",concreteStrategy2);
        //执行封装后的方法
        Context context = new Context();
        context.doAnything("strategy1");
    }
}
