package com.atguigu.springcloud.mode.factorymethod.Pizza;

/**
 * BJPepperPizza
 * @author sunhcer
 * @date 2021/01/21 23:41
 **/
public class BJPepperPizza extends Pizza {
    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        setName("北京的胡椒pizza");
        System.out.println(" 北京的胡椒pizza 准备原材料");
    }
}