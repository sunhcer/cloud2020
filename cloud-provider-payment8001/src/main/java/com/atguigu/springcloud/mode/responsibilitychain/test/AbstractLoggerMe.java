package com.atguigu.springcloud.mode.responsibilitychain.test;

/**
 * 日志责任链接口 抽象类对象
 * @author sunhcer.shi
 * @date 2021/04/16 14:39
 **/

public abstract class AbstractLoggerMe {
    //层级关系
    public static int INFO=1;
    public static int DEBBUG=2;
    public static int ERROR=3;

    protected int level;

    // 链对象
    private AbstractLoggerMe nextLogger;

    public AbstractLoggerMe() {
    }

    public AbstractLoggerMe getNextLogger() {
        return nextLogger;
    }

    public void setNextLogger(AbstractLoggerMe nextLogger) {
        this.nextLogger = nextLogger;
    }

    //职责方法
    public abstract void write(String message);

    //链方法
    public void logMessage(int level,String message){
        if (this.level>=level){
            System.out.println("当前等级为:"+this.level);
            this.write(message);
        }else{
            String name = this.getClass().getName();
            System.out.println(name+" 收到消息:"+message+" 但是级别不够,开始调用上级节点: "+nextLogger.getClass().getName());
            System.out.println("当前等级为:"+ nextLogger.level);
            nextLogger.write(message);
        }
    }

}
