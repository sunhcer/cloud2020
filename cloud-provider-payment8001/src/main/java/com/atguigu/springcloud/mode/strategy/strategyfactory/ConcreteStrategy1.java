package com.atguigu.springcloud.mode.strategy.strategyfactory;

import com.atguigu.springcloud.annotation.StrategyTypeAnnotation;
import com.atguigu.springcloud.mode.strategy.Strategy;
import org.springframework.stereotype.Component;

/**
 * 具体策略1
 * @author sunhcer.shi
 * @date 2021/04/13 14:40
 **/
@StrategyTypeAnnotation(value="strategy1")

public class ConcreteStrategy1 implements Strategy {
    @Override
    public void doSomething() {
        System.out.println("具体策略1的运算法则");
    }
}
