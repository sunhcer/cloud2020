package com.atguigu.springcloud.mulitthread.lock;

import com.atguigu.springcloud.entities.User;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 验证偏向锁
 * @author sunhcer
 * @date 2022/05/28 09:36
 **/
public class Bias {

    //因为加载静态变量的线程和main不是一个线程, 静态变量在main线程诞生之前就已经产生了, 出生时就偏向给了某个jvm的线程
    private static Object obj = new Object();

    public static void main(String[] args) {

        //计算对象的大小,单位为字节
        //所以这里所以这里在用main线程去访问他的时候,就相当于产生了竞争?? 放屁,都没用suynchronized,所以就是 轻量级锁了?
        System.out.println("ClassLayout.parseInstance(obj).instanceSize() = " + ClassLayout.parseInstance(obj).instanceSize());

        System.out.println("ClassLayout.parseInstance(obj).toPrintable() = " + ClassLayout.parseInstance(obj).toPrintable());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ClassLayout.parseInstance(obj).toPrintable() = " + ClassLayout.parseInstance(obj).toPrintable());

        System.out.println("------------------------------------------------------");
        User user = new User();

        System.out.println("ClassLayout.parseInstance(user).toPrintable() = " + ClassLayout.parseInstance(user).toPrintable());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ClassLayout.parseInstance(user).toPrintable() = " + ClassLayout.parseInstance(user).toPrintable());


//        ReentrantLock reentrantLock = new ReentrantLock();
//        Condition condition = reentrantLock.newCondition();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(()->{
            synchronized (user){
                System.out.println(Thread.currentThread().getName()+"get Lock" );
                System.out.println("aa-inner " + ClassLayout.parseInstance(user).toPrintable());
            }
            countDownLatch.countDown();
        },"AA").start();
//
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println("phase = " + ClassLayout.parseInstance(user).toPrintable());

        new Thread(()->{
            synchronized (user){
                System.out.println(Thread.currentThread().getName()+"get Lock" );
                System.out.println("bb-inner " + ClassLayout.parseInstance(user).toPrintable());
            }
        },"BB").start();
        System.out.println("final = " + ClassLayout.parseInstance(user).toPrintable());

    }

}
