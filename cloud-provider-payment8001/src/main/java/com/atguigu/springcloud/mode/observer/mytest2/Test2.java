package com.atguigu.springcloud.mode.observer.mytest2;

/**
 * 测试2
 * @author sunhcer.shi
 * @date 2021/03/29 09:12
 **/

public class Test2 {
    public static void main(String[] args) {
        //生产者
        Provider provider = new Provider();
        //消费者
        Consumer consumer1 = new Consumer("消费者1");
        Consumer consumer2 = new Consumer("消费者2");

        //订阅
        provider.addObserver(consumer1);
        provider.addObserver(consumer2);

        //发布
        // 首先24种设计模式里面没有发布订阅模式 , 发布订阅模式是mq的工作原型 , 发布订阅模式里面的广播模式和观察者模式分相似
        // 但是发布订阅模式的不同点是他多出了事件通道,事件通道可以看成一个消息调度中心
        provider.publish("税纪云平台的开始抢购");

    }
}
