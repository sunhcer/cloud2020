package com.atguigu.springcloud.mode.simpleFactory.test;

import com.atguigu.springcloud.mode.simpleFactory.OrderPizza;
import com.atguigu.springcloud.mode.simpleFactory.SimpleFactory;

/**
 * 测试
 * @author sunhcer
 * @date 2021/01/21 01:44
 **/
//相当于一个客户端，发出订购
public class PizzaStore {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //new OrderPizza();

        //使用简单工厂模式
        new OrderPizza(new SimpleFactory());
        System.out.println("~~退出程序~~");

//        new OrderPizza2();
    }

}
