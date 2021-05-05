package com.atguigu.springcloud.mode.responsibilitychain.test;

/**
 * 测试日志责任链
 * @author sunhcer.shi
 * @date 2021/04/16 16:21
 **/

public class LoggerTestMe {
    public static void main(String[] args) {
        //生成 日志责任链 节点
        AbstractLoggerMe infoLoggerMe = new InfoLoggerMe(AbstractLoggerMe.INFO);
        AbstractLoggerMe debugLoggerMe = new DebugLoggerMe(AbstractLoggerMe.DEBBUG);
        AbstractLoggerMe errorLoggerMe = new ErrorLoggerMe(AbstractLoggerMe.ERROR);

        //生成 日志责任链 节点关联
        infoLoggerMe.setNextLogger(debugLoggerMe);
        debugLoggerMe.setNextLogger(errorLoggerMe);

        //调用
        infoLoggerMe.logMessage(AbstractLoggerMe.INFO,"--->info级别");
        System.out.println("---------------------------");
        infoLoggerMe.logMessage(AbstractLoggerMe.DEBBUG,"--->info级别");
        return;

    }
}
