package com.atguigu.springcloud.mode.observer.myobserver;

import java.util.Observable;

/**
 * wechat推送类
 * @author sunhcer.shi
 * @date 2021/03/28 15:15
 **/

public class Wechat extends Observable {
    //被观察者继承可观察类 ,必须 写一个任意的方法发布消息 , 该方法必须 1:将对象标记为已更改 +  2:唤醒所有的订阅者,同时给唤醒的对象传参
    public void  publish(String msg){
        System.out.println("wechat开始推送消息:"+msg);
        setChanged();
        notifyObservers(msg);
    }
}
