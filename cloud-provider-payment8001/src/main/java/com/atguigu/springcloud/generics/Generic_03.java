package com.atguigu.springcloud.generics;

import java.util.Arrays;
import java.util.List;

/**
 * 泛型方法
 * @author sunhcer
 * @date 2021/05/24 23:06
 **/
public class Generic_03 {

    private <T> void show(T t){
        System.out.println("t = " + t);
    }


    public static void main(String[] args) {
        Generic_03 generic_03 = new Generic_03();
        List<String> list = Arrays.asList("1", "2");
        generic_03.show(list);
    }
}
