package com.atguigu.springcloud.springjob;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.QuartzService;
import lombok.SneakyThrows;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

/**
 * 
 * @author sunhcer
 * @date 2021/02/06 16:51
 **/
@Component
public class QuartzJobTest extends QuartzJobBean {

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private QuartzService quartzService1;
    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        StringJoiner stringJoiner = new StringJoiner(" | ")
                .add("HelloJob.executeInternal")
                .add(DateUtil.now())
                .add(Thread.currentThread().getName())
                        .add(scheduler.getSchedulerInstanceId());
        System.out.println(stringJoiner);
        Payment paymentById = quartzService1.getPaymentById(31L);
//        System.out.println(JSONUtil.toJsonPrettyStr(paymentById));
    }
}
