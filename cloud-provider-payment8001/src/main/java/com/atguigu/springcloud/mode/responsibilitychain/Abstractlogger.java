package com.atguigu.springcloud.mode.responsibilitychain;

/**
 * 抽象的记录器
 * 责任链: servlet和dubbo的 filter , (servlet是总控(记录所有节点的信息),dubbo是前一个节点记录下一个节点的信息), mybatis的plugins , 日志插件
 * @author sunhcer.shi
 * @date 2021/04/15 09:33
 **/

public abstract class Abstractlogger {
    public static int INFO=1;
    public static int DEBUG=1;
    public static int ERROR=1;

    protected int levle;

    //责任链的下一个元素
    protected Abstractlogger nextLogger;

    public Abstractlogger getNextLogger() {
        return nextLogger;
    }

    public void setNextLogger(Abstractlogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level,String message){
        if (this.levle<=level){
            write(message);
        }
        if (nextLogger!=null){
            nextLogger.logMessage(levle,message);
        }
    }

    abstract protected void write(String message);
}
