package com.atguigu.springcloud.mode.factorymethod.Pizza;

/**
 * BJCheesePizza
 * @author sunhcer
 * @date 2021/01/21 23:42
 **/
public class BJCheesePizza extends Pizza {

    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        setName("北京的奶酪pizza");
        System.out.println(" 北京的奶酪pizza 准备原材料");
    }

}