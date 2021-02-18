package com.atguigu.springcloud.mode.simpleFactory.test;

import com.atguigu.springcloud.mode.simpleFactory.vo.BeijingPapperPizza;
import com.atguigu.springcloud.mode.simpleFactory.vo.PepperPizza;
import com.atguigu.springcloud.mode.simpleFactory.vo.Pizza;

/**
 * 测试爷爷孙子
 * @author sunhcer
 * @date 2021/01/21 22:17
 **/
public class TestSon {
    public static void main(String[] args) {
        Pizza pizza=new BeijingPapperPizza();
        Pizza pizza1=new PepperPizza();

        pizza.prepare();
        pizza1.prepare();
    }
}
