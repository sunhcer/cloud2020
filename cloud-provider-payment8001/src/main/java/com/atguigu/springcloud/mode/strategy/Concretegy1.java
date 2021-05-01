package com.atguigu.springcloud.mode.strategy;

/**
 * 具体策略角色1
 * @author sunhcer.shi
 * @date 2021/04/13 14:08
 **/

public class Concretegy1 implements Strategy{
    @Override
    public void doSomething() {
        System.out.println("具体策略1的运算法则");
    }

}
