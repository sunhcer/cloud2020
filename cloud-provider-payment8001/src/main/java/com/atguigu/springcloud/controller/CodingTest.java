package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.leetcode.dfs.L46_fullArrangement;

/**
 * 算法测试类
 * @author sunhcer.shi
 * @date 2021/04/17 16:20
 **/

public class CodingTest {
    //测试类配置切面
    public static long concurrentTime1, concurrentTime2, concurrentMemory1, concurrentMemory2;

    //条件:线程A持有该资源，该资源只能被一个线程独占，
    private static Object resourceA=new Object();
    private static Object resourceB=new Object();

    public static void main(String[] args) {
        //上下文切换，保存执行现场，使得再次获得时间片之后知道上次执行到哪里

        //相互争夺，互相等待

        //线程A持有资源1并且想要争夺资源2，同时 线程b持有资源2，并且想要争夺资源1

        //创建线程A
        Thread threadA=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(resourceA){
                    System.out.println("线程A："+Thread.currentThread()+"独占了资源A");
                    try {
                        System.out.println("线程A持有并且独占了A时，睡眠一秒");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //                    System.out.println("线程A独占了资源A，并且记录资源B的等待链，准备去争抢资源B");
                    synchronized(resourceB){
                        System.out.println("线程A仍然独占资源A，并且抢到了资源B");
                    }
                }
            }
        });

        //创建线程B
        Thread threadB=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceB){
                    System.out.println("线程B："+Thread.currentThread()+"独占了资源A");
                    try {
                        System.out.println("线程B持有并且独占资源A时 睡眠1秒");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //                    System.out.println("线程B："+Thread.currentThread()+" 睡眠结束，仍然独占资源B ，准备争夺资源A");
                    synchronized(resourceA){
                        System.out.println("线程B："+Thread.currentThread()+"拿到了资源B");
                    }
                }
            }
        });


        threadA.start();

        threadB.start();
        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }
}
