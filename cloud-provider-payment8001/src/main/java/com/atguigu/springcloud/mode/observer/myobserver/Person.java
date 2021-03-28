package com.atguigu.springcloud.mode.observer.myobserver;

import java.util.Observable;
import java.util.Observer;

/**
 * 
 * @author sunhcer.shi
 * @date 2021/03/28 15:10
 **/

public class Person implements Observer {
    //观察者继承观察者接口,实现update方法,通过该方法拿到推送的信息,并且这里是即时消费的
    // 就是mq的工作机制
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("用户:"+name+ " 得到了wechat的推送: "+arg);
        try {
            System.out.println("用户:"+name+"得到推送的时候卡两秒");
            this.wait(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String print(){
       return " 次打印--";
    }

}
