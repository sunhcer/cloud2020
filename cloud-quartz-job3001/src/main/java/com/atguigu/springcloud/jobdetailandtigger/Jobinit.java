package com.atguigu.springcloud.jobdetailandtigger;

import com.atguigu.springcloud.springjob.QuartzJobTest;
import com.atguigu.springcloud.test.HelloJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * 
 * @author sunhcer
 * @date 2021/02/06 17:05
 **/
@Component
public class Jobinit {

    @Autowired
    private Scheduler scheduler;




    /** 2021/02/06 17:29:36 框架注释
     *  使用 @PostConstruct 的方式注入 jobdetail和 trigger
     */
//    @PostConstruct
//    public void initjob() throws SchedulerException{
//        JobDetail detail= JobBuilder.newJob(QuartzJobTest.class)
//                .build();
//
//        Trigger trigger=TriggerBuilder.newTrigger()
//                .startNow()
//                .build();
//
//        scheduler.scheduleJob(detail,trigger);
//    }


    /** 2021/02/06 17:30:13 框架注释
     * 使用 @bean的 形式注入
     */
    @Bean
    public JobDetail springJobDetail(){
        return JobBuilder.newJob(QuartzJobTest.class)
                .withIdentity("springJobDetail")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger springJobTrigger(){
        return TriggerBuilder.newTrigger()
                .startNow()
                /** 2021/02/06 17:59:06 框架注释
                 *  配置调度策略 观察数据库的表记录变化
                 */
                .withSchedule(
                        /** 2021/02/06 10:58:29 框架注释
                         *  每秒执行
                         */
//                            CronScheduleBuilder.cronSchedule("5-10 * * * * ? *")
                        /** 2021/02/15 20:45:50 框架注释
                         *  每3秒执行1次
                         */
                        simpleSchedule()
                                .withIntervalInSeconds(3)
                                .repeatForever()
                )
                .forJob("springJobDetail")
                .build();
    }


}
