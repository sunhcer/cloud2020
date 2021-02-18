package com.atguigu.springcloud.entities;

public class StaticVo {

    private  static StaticVo staticVo=new StaticVo();


    public static int count1;
    public static int count2=0;
    public int count3=1;
    
    private StaticVo(){
        /* 无参构造做了什么事,给予所有成员变量一个默认值*/

//        count1=0;//1:类加载时就被赋予0
        count1++;

//        count2=0;//1:类加载赋予0
        count2=2;

//        count3=1;
        count3=3;

//        count2=0;//2:初始化赋予0----->>静态变量显式初始化在最后进行

    }

    public static StaticVo getStaticVo() {
        return staticVo;
    }



}
