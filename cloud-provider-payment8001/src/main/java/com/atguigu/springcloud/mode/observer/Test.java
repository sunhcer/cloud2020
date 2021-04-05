package com.atguigu.springcloud.mode.observer;

/**
 * 测试类
 *
 * @author sunhcer.shi
 * @date 2021/03/28 14:39
 **/

public class Test {
    public static void main(String[] args){
        //观察者模式组成: 观测者 被观察者 + 观察者订阅被观察者之后, 被观察者发布消息(publicNewinfo), 观察者会得到推送消息(update)

        //被观察者的角色
        Saller officalAccount = new Saller();

        //观测者
        Custom bob = new Custom(" bob");
        Custom sunhcer = new Custom("sunhcer");
        Custom lim = new Custom("Lim");

        //将观察者注册到可观察对象的观察者列表里面
        officalAccount.addObserver(bob);
        officalAccount.addObserver(sunhcer);
        officalAccount.addObserver(lim);

        //发布消息
        officalAccount.publishNewinfo("bookstore has a new book");
        officalAccount.deleteObserver(lim);
        System.out.println("=============== lim 用户退订了");

        // 退订后 再次推送新消息
        officalAccount.publishNewinfo("bookstore has two book");
    }
}
