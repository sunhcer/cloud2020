package com.atguigu.springcloud.mode.observer.mytest2;

import java.util.Observable;

/**
 * 生产者
 * @author sunhcer.shi
 * @date 2021/03/29 09:11
 **/

public class Provider extends Observable {

    public void publish(String msg){
        System.out.println("生产者开始发布消息:"+msg);
        setChanged();
        notifyObservers(msg);//发送消息 注意
    }
}
