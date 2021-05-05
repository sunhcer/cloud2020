package com.atguigu.springcloud.mode.responsibilitychain.test2;

/**
 * info节点
 * @author sunhcer.shi
 * @date 2021/04/16 17:18
 **/

public class InfoLogger2 extends AbstractLogger2 {

    public InfoLogger2(int level) {
        this.level=level;
    }

    @Override
    public void write(String message) {
        System.out.println(this.getClass().getName()+" 消费消息: "+message);
    }
}
