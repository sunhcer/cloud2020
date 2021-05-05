package com.atguigu.springcloud.mode.responsibilitychain.test2;

import com.atguigu.springcloud.entities.CommonConstant;

/**
 * 测试责任链
 * @author sunhcer.shi
 * @date 2021/04/16 17:27
 **/

public class ChainTest2 {

    public static void main(String[] args) {
        //初始化节点
        AbstractLogger2 info=new InfoLogger2(CommonConstant.logMap2.get("info"));
        AbstractLogger2 debug=new DebugLogger2(CommonConstant.logMap2.get("debug"));
        AbstractLogger2 error=new ErrorLogger2(CommonConstant.logMap2.get("error"));

        //初始化链
        info.setNextLogger(debug);
        debug.setNextLogger(error);


        //测试层级
        Integer info1 = CommonConstant.logMap2.get("info");
        System.out.println("map里面的值: "+info1);
        info.logMessage(CommonConstant.logMap2.get("info"),"info级别");
        System.out.println("----------------------------------------------------------------");
        info.logMessage(CommonConstant.logMap2.get("debug"),"debug级别");
    }

}
