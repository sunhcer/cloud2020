package com.atguigu.springcloud.mode.responsibilitychain.test2;

/**
 * debug节点
 * @author sunhcer.shi
 * @date 2021/04/16 17:21
 **/

public class DebugLogger2 extends AbstractLogger2{

    public DebugLogger2(int level) {
        this.level=level;
    }

    @Override
    public void write(String message) {
        System.out.println(this.getClass().getName()+" 消费消息: "+message);
    }
}
