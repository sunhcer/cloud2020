package com.atguigu.springcloud.generics;

import lombok.Data;

/**
 * 泛型类
 * @author sunhcer
 * @date 2021/05/24 22:52
 **/
@Data
public class Generic_02<T> {
    // 如返回的result类 ,data就是一个泛型,泛型 同时使得通用性更好
    private T data;

    /** 2021/05/24 23:04:43 框架注释
     *  泛型方法
     */
    private <T> void show(T t){
        System.out.println(t);
    }




    public static void main(String[] args) {
        Generic_02<String> stringGeneric_02 = new Generic_02<>();

        stringGeneric_02.setData("data");

        System.out.println("stringGeneric_02 = " + stringGeneric_02);

        /** 2021/05/24 23:00:46 框架注释
         *  可以使用时再指定类型
         */
        Generic_02<Integer> integerGeneric_02 = new Generic_02<>();

        integerGeneric_02.setData(111);

        System.out.println("integerGeneric_02 = " + integerGeneric_02);

        /** 2021/05/24 23:05:53 框架注释
         *  调用泛型方法
         */
        Generic_02<Object> objectGeneric_02 = new Generic_02<>();



    }




}
