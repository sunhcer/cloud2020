package com.atguigu.springcloud.mode.factorymethod.test;

import com.atguigu.springcloud.mode.factorymethod.factory.PizzaFactorySonBJ;
import com.atguigu.springcloud.mode.factorymethod.factory.PizzaFactorySonLD;

/**
 * 测试
 * @author sunhcer
 * @date 2021/01/21 23:51
 **/
public class PizzaStore {

    public static void main(String[] args) {
        String loc = "bj";
        if (loc.equals("bj")) {
            //创建北京口味的各种Pizza
            new PizzaFactorySonBJ();
        } else {
            //创建伦敦口味的各种Pizza
            new PizzaFactorySonLD();
        }
    }

}
