package com.atguigu.springcloud.mode.responsibilitychain.test;

/**
 * error节点
 * @author sunhcer.shi
 * @date 2021/04/16 16:19
 **/

public class ErrorLoggerMe extends AbstractLoggerMe {
    public ErrorLoggerMe(int level) {
        this.level=level;
    }

    @Override
    public void write(String message) {
        System.out.println(this.getClass().getName()+" :error节点开始运作"+"---消息为:"+message);
    }

}
