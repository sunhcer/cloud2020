package com.atguigu.springcloud.mode.observer;

import java.util.Observable;

/**
 * 要给观察者的信息
 * @author sunhcer.shi
 * @date 2021/03/28 14:36
 **/

public class Saller extends Observable {

    // 比如现在有一个场景是: 用户订阅了书店 , 书店来新书 , 书店群发给所有订阅了他的用户该消息
    public void publishNewinfo(String info){
        setChanged();
        notifyObservers(info);
    }

}
