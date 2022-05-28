package com.atguigu.springcloud.mulitthread.scheduler;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

/**
 * 定时任务调度的缺点
 * @author sunhcer
 * @date 2022/05/28 11:15
 **/
@Slf4j
public class TimerTest {

    //timer的缺点就是单线程调度,异常或者延时都会影响后续任务的执行
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task1_延时1秒 = new TimerTask() {
            @Override
            public void run() {
                log.debug("task1 延时1秒");
                try {
                    sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        TimerTask task2_延时1秒 = new TimerTask() {
            @Override
            public void run() {
                log.debug("task2");
//                try {
//                    sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        };

        timer.schedule(task1_延时1秒,1000);
        timer.schedule(task2_延时1秒,1000);
    }

}
