package com.atguigu.springcloud.mode.responsibilitychain;

/**
 * 错误日志记录器
 * @author sunhcer.shi
 * @date 2021/04/15 15:35
 **/

public class ErrorLogger extends Abstractlogger {

    public ErrorLogger(int level){
        this.levle=level;
    }
    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger:"+message);
    }
}
