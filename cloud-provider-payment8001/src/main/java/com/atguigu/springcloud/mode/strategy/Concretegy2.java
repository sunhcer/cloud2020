package com.atguigu.springcloud.mode.strategy;

/**
 * 具体策略角色2
 * @author sunhcer.shi
 * @date 2021/04/13 14:07
 **/

public class Concretegy2 implements Strategy {
    @Override
    public void doSomething() {
        System.out.println("具体策略2的运算法则");
    }
}
