package com.atguigu.springcloud.mode.responsibilitychain.test2;

/**
 * 日志责任链 总控抽象类
 * @author sunhcer.shi
 * @date 2021/04/16 16:38
 **/

public abstract class AbstractLogger2  {


    //层级元素
    protected int level;

    //链对象
    private AbstractLogger2 nextLogger;
    public AbstractLogger2() {
    }
    public AbstractLogger2 getNextLogger() {
        return nextLogger;
    }

    public void setNextLogger(AbstractLogger2 nextLogger) {
        this.nextLogger = nextLogger;
    }

    //责任方法
    public abstract void write(String message);
//        System.out.println(this.getClass().getName()+" 消费消息: "+message);


    //链方法 = 层级元素 + 消息元素
    public void logMessage(int level,String message){
        System.out.println("当前等级为:"+this.level);
        if (this.level>=level){
            write(message);
        }else{
            System.out.println(this.getClass().getName()+" 收到消息:"+message+" 无权限,调用上级:"+nextLogger.getClass().getName());
            System.out.println("上级等级为:"+nextLogger.level);
            nextLogger.write(message);
        }
    }
}
