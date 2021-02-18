package com.atguigu.springcloud.mode.simpleFactory.vo;

/**
 * 希腊披萨
 * @author sunhcer
 * @date 2021/01/21 01:32
 **/
public class GreekPizza extends Pizza{
    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        System.out.println(" 给希腊披萨 准备原材料 ");
    }

}
