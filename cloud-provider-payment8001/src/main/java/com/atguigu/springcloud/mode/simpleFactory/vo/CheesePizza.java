package com.atguigu.springcloud.mode.simpleFactory.vo;

/**
 * 骑士披萨
 * @author sunhcer
 * @date 2021/01/21 01:34
 **/
public class CheesePizza extends Pizza{


    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        System.out.println(" 给制作奶酪披萨 准备原材料 ");
    }

}
