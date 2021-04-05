package com.atguigu.springcloud.mode.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 具体观察者类
 * @author sunhcer.shi
 * @date 2021/03/28 14:24
 **/

public class Custom implements Observer {

    // 用户名
    private String name;
    public Custom(String name){
        this.name=name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("用户: "+name+", 知道了: "+arg);
    }
}
