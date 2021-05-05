package com.atguigu.springcloud;

import com.atguigu.springcloud.leetcode.dfs.L46_fullArrangement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 算法测试类
 * @author sunhcer.shi
 * @date 2021/04/17 16:20
 **/

public class CodingTest {
    //测试类配置切面
    public static long concurrentTime1, concurrentTime2, concurrentMemory1, concurrentMemory2;

    @Before
    public void before() {
        //得到出个必须开始的系统时间(纳秒级, 最终转换为毫秒级,保留两位小数)
        concurrentTime1 = System.nanoTime();
        //得到虚拟机运行,程序开始运行时jvm所占用的内存
        Runtime runtime = Runtime.getRuntime();
        concurrentMemory1 = runtime.totalMemory() - runtime.freeMemory();
    }

    @After
    public void after() {
        //得到程序执行完毕的执行时间(纳秒级)
        concurrentTime2 = System.nanoTime();
        //得到执行完毕时jvm所占用的内存(byte)
        Runtime runTime = Runtime.getRuntime();
        concurrentMemory2 = runTime.totalMemory() - runTime.freeMemory();

        /**
         *计算start和end之间的代码执行期间所消耗时间与内存
         * 1ms=1000us=1000000ns
         * 1MB=1024 kb = 1024*1024 byte
         */
        String costTime=String.valueOf((concurrentTime2-concurrentTime1)/1000000);
        String costMemory=String.valueOf((concurrentMemory2-concurrentMemory1)/(1024*1024));
        System.out.println("执行时间:"+costTime+" ms");
        System.out.println("内存消耗:"+costMemory+" mb");
    }

    /**
     * dfs 广度优先搜索
     */
    @Test
    public  void testBfs(){
        L46_fullArrangement l46_fullArrangement = new L46_fullArrangement();
        l46_fullArrangement.permute(new int[]{2,5,9,75});
    }


    //条件:线程A持有该资源，该资源只能被一个线程独占，
    private static Object resourceA=new Object();
    private static Object resourceB=new Object();

    @Test
    public void testDeadLock(){
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
