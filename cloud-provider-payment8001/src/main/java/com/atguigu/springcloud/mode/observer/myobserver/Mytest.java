package com.atguigu.springcloud.mode.observer.myobserver;

/**
 * 我的测试类
 * @author sunhcer.shi
 * @date 2021/03/28 15:25
 **/

public class Mytest {

    public static void main(String[] args) {
        //1: 可被观察对象 = 发布推送的对象 = 被标记为改变的对象 (生产者)
        Wechat wechat = new Wechat();

        //2: 订阅的对象 (消费者)
        Person matt = new Person("matt");
        Person sunhcer = new Person("sunhcer");
        //3: 添加订阅
        wechat.addObserver(matt);
        wechat.addObserver(sunhcer);
        //4: 可被观察对象 推送消息
        wechat.publish("-----------Rain tomorrow-------");



        //退订
        wechat.deleteObserver(sunhcer);
        System.out.println("custom sunhcer Unsubscribe");
        //
        wechat.publish("Coupons available tomorrow");

        //多线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <100 ; i++) {
                    System.out.println("matt"+"第"+i+matt.print());
                    if (i==50){
                        wechat.publish("wechat interrpt========");
                    }
                }
            }
        });

        wechat.addObserver(sunhcer);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <100 ; i++) {
                    System.out.println("sunhcer"+"第"+i+sunhcer.print());
                    if (i==50){
                        wechat.publish("wechat interrpt========");
                    }
                }
            }
        });

        thread.start();
        thread1.start();
    }
}
