package com.atguigu.springcloud.util;

import org.quartz.CronExpression;

/**
 * 工具类集合
 * @author sunhcer
 * @date 2021/02/06 11:33
 **/
public class OneUtil {


    public static Boolean isValidCron(String cron){
        return CronExpression.isValidExpression(cron);
    }

}
