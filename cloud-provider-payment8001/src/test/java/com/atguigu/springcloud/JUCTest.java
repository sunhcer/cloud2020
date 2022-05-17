package com.atguigu.springcloud;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.Data;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * juc测试类
 *
 * @author sunhcer
 * @date 2022/03/20 11:41
 **/
public class JUCTest {

    /*
     * @Description 测试ThreadLocal用的类 @link com.atguigu.springcloud.JUCTest.threadLocalTest
     * @author sunhcer
     * @date 2022/03/20 11:50
     * @param null
     * @return
     */
    @Data
    static class Foo {
        //调用过初始化方法生成的实例总数
        static final AtomicInteger AMOUNT = new AtomicInteger(0);

        //对象编号-该对象是第几个通过初始化方法生成\
        int index = 0;

        //对象的内容
        int bar = 10;

        //构造器
        public Foo() {
            //初始化次数递增并且给对象编号
            index = AMOUNT.incrementAndGet();
        }

        @Override
        public String toString() {
            return "Foo{" +
                    "index=" + index +
                    ", bar=" + bar +
                    '}';
        }
    }

    //定义线程本地变量 @link com.atguigu.springcloud.JUCTest.threadLocalTest
//    private static final ThreadLocal<Foo> LOCAL_FOO = new ThreadLocal<Foo>();
    private static final ThreadLocal<Foo> LOCAL_FOO = ThreadLocal.withInitial(Foo::new);

    @Test
    public void threadLocalTest() {
        //获取简易型线程池,并且提交5个任务
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            newFixedThreadPool.execute(() -> {
                //获取本地线程的变量
//                if (LOCAL_FOO.get()==null){
//                    //设定值
//                    LOCAL_FOO.set(new Foo());
//                }
                // 可以通过改变成员变量的赋值,去掉以上if判断


                System.out.println("线程:" + Thread.currentThread().getName() + " 初始的ThreadLocal值为:" + LOCAL_FOO.get());

                //每个线程的ThreadLocal值的对象内容累加10次
                for (int j = 0; j < 10; j++) {
                    Foo foo = LOCAL_FOO.get();
                    foo.setBar(foo.getBar() + 1);

                    //增加 set值之间的时间间隔,观察5个任务,不同线程之间的值时候互相干扰
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //打印10次本地累加之后的结果
                System.out.println("线程:" + Thread.currentThread().getName() + " 累加后的ThreadLocal值为:" + LOCAL_FOO.get());
            });
            //移除ThreadLocal,不然会造成内存泄漏
            LOCAL_FOO.remove();//这对于线程池中的线程尤其重要
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /*
     * @Description 使用减少计数判定线程是否都执行完了
     * @author sunhcer
     * @date 2022/03/20 16:28
     * @return void
     */
    @Test
    public void test() throws InterruptedException {

        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);

        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            newFixedThreadPool.execute(() -> {
                try {
                    Thread.sleep(RandomUtil.randomInt(10, 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "执行完毕.");
            });
        }

        countDownLatch.await();
        System.out.println("main over!!");
    }

    /*
     * @Description 测试ScheduleExecutorService单个任务丢失,吃异常
     * @author sunhcer
     * @date 2022/04/04 09:25
     * @return void
     */
    @Test
    public void testScheduleBugBySingle() throws InterruptedException {
        //测试吃异常
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        LongAdder longAdder = new LongAdder();
        //测试单个周期任务吃异常
        scheduledExecutorService.scheduleAtFixedRate(
                ()->{
                    longAdder.increment();
                    long exeTime = longAdder.longValue();
                    System.out.println("线程:"+Thread.currentThread().getName()+" doing...,当前执行次数:"+exeTime);
                    if (exeTime==3){
                        int i = 1/0;
                    }
                },0,500,TimeUnit.MILLISECONDS
        );

        Thread.sleep(2000);
        //关闭线程池
        scheduledExecutorService.shutdown();
    }

    /*
     * @Description 测试ScheduleExecutorService多个任务丢失,吃异常
     * @author sunhcer
     * @date 2022/04/04 09:25
     * @return void
     */
    @Test
    public void testScheduleBugByMulti() throws InterruptedException {
        //测试吃异常
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        LongAdder longAdder = new LongAdder();
        LongAdder longAdderPre = new LongAdder();
        LongAdder longAdderAfter = new LongAdder();

        CopyOnWriteArrayList<Integer> pre = new CopyOnWriteArrayList<>();
        for (int i = 0; i <100; i++) {
            pre.add(i);
        }

        CopyOnWriteArrayList<Integer> after = new CopyOnWriteArrayList<>();
        for (int i = 0; i <100; i++) {
            after.add(i);
        }

        //测试多个周期任务吃异常
        //塞1000个前置任务
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            scheduledExecutorService.scheduleAtFixedRate(
                    ()->{
                        if (CollUtil.isNotEmpty(pre)&& ObjectUtil.isNotEmpty(pre.get(finalI))){

                            longAdder.increment();
                            long totalExeTime = longAdder.longValue();
                            System.out.println("前置线程:"+Thread.currentThread().getName()+" doing...,当前执行次数:"+totalExeTime);
                            if (totalExeTime==190){
                                int k = 1/0;
                                System.out.println("前置吃异常:totalExeTime="+ totalExeTime+"当前线程:"+Thread.currentThread().getName());
                            }
                            pre.remove(finalI);
                        }

                    },2,50,TimeUnit.MILLISECONDS
            );
        }

        //塞1000个后置任务
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            scheduledExecutorService.scheduleAtFixedRate(
                    ()->{
                        if (CollUtil.isNotEmpty(after)&& ObjectUtil.isNotEmpty(after.get(finalI))){

                            longAdder.increment();
                            long totalExeTime = longAdder.longValue();
                            System.out.println("后置线程:"+Thread.currentThread().getName()+" doing...,当前执行次数:"+totalExeTime+"当前任务号:"+finalI);
                            if (totalExeTime==190
                            ||totalExeTime==191
                            ||totalExeTime==192
                            ||totalExeTime==194
                            ||totalExeTime==195
                            ||totalExeTime==196
                            ||totalExeTime==197
                            ||totalExeTime==198
                            ||totalExeTime==199
                            ||totalExeTime==189
                            ){
                                System.out.println("后置任务吃异常:totalExeTime="+totalExeTime+"当前线程:"+Thread.currentThread().getName()+"当前任务号:"+finalI);
                                int k = 1/0;
                            }
                            after.remove(finalI);
                        }

                    },8,50,TimeUnit.MILLISECONDS
            );
        }

        Thread.sleep(12000);
        //关闭线程池
        System.out.println("关闭前:剩余任务:"+after.toString());
        scheduledExecutorService.shutdown();
    }



}
