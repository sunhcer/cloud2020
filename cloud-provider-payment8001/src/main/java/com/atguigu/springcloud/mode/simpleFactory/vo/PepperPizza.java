package com.atguigu.springcloud.mode.simpleFactory.vo;

/**
 * 胡椒披萨
 * @author sunhcer
 * @date 2021/01/21 01:35
 **/
public class PepperPizza extends Pizza {

    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        System.out.println(" 给胡椒披萨准备原材料 ");
    }
}
