package com.atguigu.springcloud.generics;

/**
 * 泛型子类, 明确类型
 * @author sunhcer
 * @date 2021/05/24 23:11
 **/
public class Generic_01_01 implements Generic_01<Integer> {
    @Override
    public void show(Integer integer) {
        System.out.println("integer = " + integer);
    }


    public static void main(String[] args) {
        Generic_01_01 generic_01_01 = new Generic_01_01();
        generic_01_01.show(44);
    }
}
