package com.atguigu.springcloud.entities;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Ordering;

import java.util.Map;

/**
 * 常量工厂
 * @author sunhcer.shi
 * @date 2021/04/16 16:44
 **/

public class CommonConstant {

    //创建不可变map , 改变会报错
    public static Map<String,Integer> logMap2= ImmutableMap.of(
            "info",1,
            "debug",2,
            "error",3
    );

    //创建sortMap
     static ImmutableSortedMap<String,Integer> logMap22=new ImmutableSortedMap
            .Builder<String, Integer>(Ordering.natural())
            .put("info",1)
            .put("debug",2)
            .put("error",3)
            .build();
}
