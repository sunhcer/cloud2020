package com.atguigu.springcloud.mode.factorymethod.Pizza;

/**
 * LDPepperPizza
 * @author sunhcer
 * @date 2021/01/21 23:40
 **/
public class LDPepperPizza extends Pizza{
    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        setName("伦敦的胡椒pizza");
        System.out.println(" 伦敦的胡椒pizza 准备原材料");
    }
}