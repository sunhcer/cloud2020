package com.atguigu.springcloud.mode.simpleFactory.vo;

/**
 * 北京胡椒披萨
 * @author sunhcer
 * @date 2021/01/21 22:14
 **/
public class BeijingPapperPizza extends PepperPizza {
    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        System.out.println(" 给-----北京 ----胡椒披萨准备原材料 ");
    }
}
