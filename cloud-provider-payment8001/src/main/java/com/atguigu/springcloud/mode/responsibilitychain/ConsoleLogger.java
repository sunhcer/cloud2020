package com.atguigu.springcloud.mode.responsibilitychain;

/**
 * 控制台输出记录器
 * @author sunhcer.shi
 * @date 2021/04/15 11:11
 **/

public class ConsoleLogger extends Abstractlogger{

    public ConsoleLogger(int level){
        this.levle=level;
    }
    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger:"+message);
    }
}
