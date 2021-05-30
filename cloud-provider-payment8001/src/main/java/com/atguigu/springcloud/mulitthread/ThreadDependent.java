package com.atguigu.springcloud.mulitthread;

import java.util.concurrent.Semaphore;

/**
 * 线程依赖
 * @author sunhcer
 * @date 2021/02/26 22:06
 **/
public class ThreadDependent {

    private static int num;
    /** 2021/02/26 22:08:38 框架注释
     *  该信号量可以阻塞多个线程 , 释放多个线程
     */
    private static Semaphore semaphore=new Semaphore(0);


    public static void main(String[] args) {
        Thread threadA=new Thread(()->
        {
            try {
                Thread.sleep(1000);
                num=1;
                /** 2021/02/26 22:15:14 框架注释
                 *  释放锁
                 */
                semaphore.release(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        );

        Thread threadB=new Thread(()->{
            try {
                /** 2021/02/26 22:15:00 框架注释
                 *  获取锁
                 */
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("threadB---获取到的num值为:"+num);
        });

        Thread threadC=new Thread(()->{
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("threadC---获取到的num值为:"+num);
        });

        threadA.start();
        threadB.start();
        threadC.start();

    }



}
