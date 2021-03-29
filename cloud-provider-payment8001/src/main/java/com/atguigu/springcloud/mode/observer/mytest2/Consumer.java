package com.atguigu.springcloud.mode.observer.mytest2;

import java.util.Observable;
import java.util.Observer;

/**
 * 消费者
 * @author sunhcer.shi
 * @date 2021/03/29 09:11
 **/

public class Consumer implements Observer {

    private String name;

    public Consumer(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        //接收且消费消息
        System.out.println("消费者:"+name+" 接收到了消息:"+arg);
        System.out.println("消费者:"+name+" 消费了消息"+arg);
        System.out.println("消费者:"+name+" 订单已生成 ");
    }
}
