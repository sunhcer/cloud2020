package com.atguigu.springcloud.mode.strategy.strategyfactory;

import com.atguigu.springcloud.annotation.StrategyTypeAnnotation;
import com.atguigu.springcloud.mode.strategy.Strategy;
import org.springframework.stereotype.Component;

/**
 * 具体策略2
 * @author sunhcer.shi
 * @date 2021/04/13 14:48
 **/
@StrategyTypeAnnotation(value="strategy2")
public class ConcreteStrategy2 implements Strategy {
    @Override
    public void doSomething() {
        System.out.println("具体策略2的运算法则");
    }

}