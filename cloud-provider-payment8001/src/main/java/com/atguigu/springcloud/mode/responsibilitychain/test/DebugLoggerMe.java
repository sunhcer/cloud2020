package com.atguigu.springcloud.mode.responsibilitychain.test;

/**
 * Debug节点
 * @author sunhcer.shi
 * @date 2021/04/16 16:14
 **/

public class DebugLoggerMe  extends AbstractLoggerMe{

    public DebugLoggerMe(int level) {
        this.level=level;
    }

    @Override
    public void write(String message) {
        System.out.println(this.getClass().getName()+" : debug节点启动"+"---消息为:"+message);
    }

}
