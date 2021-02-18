package com.atguigu.springcloud.test;

/**
 * quartz 简单测试类
 * @author sunhcer
 * @date 2021/02/05 21:35
 **/
import com.atguigu.springcloud.util.OneUtil;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class QuartzTest {

    public static void main(String[] args) throws InterruptedException {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();


            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(HelloJob.class)
                                                                                            /** 2021/02/06 12:13:00 框架注释
                                                                                             *  使用jobdetail和tigger传递值 限定 同一个job里面的 不同tigger的职能边界
                                                                                             */
                    .usingJobData("jobdetail-key-1","jobdetail-value-1")
                    .withIdentity("job1", "group1")
                    .build();

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(
                                                                                                /** 2021/02/06 10:20:20 框架注释
                                                                                                 *  观察Tigger的 继承结构 core下面有四个实现类 其中有一个是simpleScheduleBuilder 依次推断出 每个实现类都会有一个builder
                                                                                                 */
                            SimpleScheduleBuilder
                                    .simpleSchedule()
                            .withIntervalInSeconds(1)
                                                                                                /** 2021/02/06 10:06:21 框架注释
                                                                                                 *  改变执行次数 任务总时间 3秒 每秒执行一次 只能重复执行一次
                                                                                                 */
                            .withRepeatCount(1))
//                            .repeatForever())
                    .build();

                                                                                                /** 2021/02/06 00:18:13 框架注释
                                                                                                 *  增加一个trigger 该触发器用name的方式来获取job1
                                                                                                 */
            SimpleTrigger trigger2 = newTrigger()
                    .withIdentity("tigger2", "group1")
                    .forJob("job1", "group1")
                    .startNow()
                    .withSchedule(
                            simpleSchedule()
                                    .withIntervalInSeconds(3)
                                    .repeatForever()
                    )
                    .build();

            /** 2021/02/06 10:34:43 框架注释
             *  使用cronBuilder 以及 cronExpression 写一个tigger
             */
            Trigger tigger3= newTrigger()
                    /** 2021/02/06 12:13:00 框架注释
                     *  使用jobdetail和tigger传递值 限定 同一个job里面的 不同tigger的职能边界
                     */
                    .usingJobData("trigger-key-1","trigger-value-1")

                    .withIdentity("trigger3","group1")
                    .startNow()
                    .withSchedule(
                            /** 2021/02/06 10:58:29 框架注释
                             *  每秒执行
                             */
                            CronScheduleBuilder.cronSchedule("5-10 * * * * ? *")
                            /** 2021/02/06 10:58:50 框架注释
                             *  每分钟的 5-10秒执行 这里注意 如果将延时设计成15 有可能不执行 因为可能没轮到 每分钟的5-10秒 每秒执行一次 所以需要延时>=60保证执行
                             *  - 至
                             */
//                            CronScheduleBuilder.cronSchedule("5-10 * * * * ? *")
                            /** 2021/02/06 11:10:38 框架注释
                             *  每分钟的 10和 20秒执行
                             *  * 与
                             */
//                            CronScheduleBuilder.cronSchedule("10,20 * * * * ? *")

                            /** 2021/02/06 11:14:12 框架注释
                             *  从 10 秒开始 每 20 秒执行一次
                             */
//                            CronScheduleBuilder.cronSchedule("5/3 * * * * ? *")


                    )
                    .build();






            // Tell quartz to schedule the job using our trigger

            /** 2021/02/06 00:36:23 框架注释
             *  这里 将两个调度的顺序改变一下就会报错 因为第一次运行不能直接按名字拿
             */
//            scheduler.scheduleJob(job, trigger);

//            scheduler.scheduleJob(trigger2);

            scheduler.scheduleJob(job,tigger3);
            /** 2021/02/05 23:37:56 框架注释
             *  延时
             */
            TimeUnit.SECONDS.sleep(60);
            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}