package com.atguigu.springcloud.mode.responsibilitychain;

/**
 * 文件日志
 * @author sunhcer.shi
 * @date 2021/04/15 15:38
 **/

public class FileLogger extends Abstractlogger {
    public FileLogger(int level){
        this.levle=level;
    }
    @Override
    protected void write(String message) {
        System.out.println("file::Logger: "+message);
    }
}
