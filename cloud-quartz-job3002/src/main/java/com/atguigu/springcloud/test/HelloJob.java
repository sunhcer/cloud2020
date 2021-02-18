package com.atguigu.springcloud.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * job实现类
 * @author sunhcer
 * @date 2021/02/05 22:05
 **/
public class HelloJob implements Job {
    /** 2021/02/05 22:09:38 框架注释
     *  quartz 所有业务实现类都要实现job接口
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        /** 2021/02/05 23:47:28 框架注释
         *  以后时间工具类都直接用hutool----->>谨记
         */
        System.out.println(Thread.currentThread().getName()+"---hello-----------"+ DateUtil.now()+"-----------"+jobExecutionContext.getJobDetail().getKey());

        /** 2021/02/06 12:27:39 框架注释
         *  hutool toprettystr 挺好看的
         */
        System.out.println(
        "jobdetail-map----->>"+ JSONUtil.toJsonPrettyStr(jobExecutionContext.getJobDetail().getJobDataMap())
        );

        System.out.println(
                "triggerdata-map----->>"+JSONUtil.toJsonPrettyStr(jobExecutionContext.getTrigger().getJobDataMap())
        );
    }
}
