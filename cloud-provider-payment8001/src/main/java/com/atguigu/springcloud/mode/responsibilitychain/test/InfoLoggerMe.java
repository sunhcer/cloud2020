package com.atguigu.springcloud.mode.responsibilitychain.test;

/**
 * info节点
 * @author sunhcer.shi
 * @date 2021/04/16 16:02
 **/

public class InfoLoggerMe extends AbstractLoggerMe {


    //抽象子类默认调用父类的无参构造,在这种情况下,父类必须有无参构造

    public InfoLoggerMe(int level) {
        this.level=level;
    }

    @Override
    public void write(String message) {
        System.out.println(this.getClass().getName()+": info节点输出日志"+"---消息为:"+message);
    }
}
