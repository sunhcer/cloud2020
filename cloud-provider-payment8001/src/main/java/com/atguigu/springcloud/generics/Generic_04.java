package com.atguigu.springcloud.generics;

import org.springframework.beans.factory.support.ManagedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 泛型通配符, 泛型上下限
 * @author sunhcer
 * @date 2021/05/24 23:28
 **/
public class Generic_04 {
    //泛型方法
    public <T> void showMethod(List<T> t){
        t.stream().forEach(System.out::println);
    }
    //通配符方法
    public void showWildcard(List<?> t){
        t.stream().forEach(System.out::println);
    }

    // 设置通配符上,限定该方法只能操作数字类型 ,通过父类来定上限
    public void showHight(List<? extends Number> list){
        list.forEach(System.out::println);
    }

    // 通配符 直接换成 T 有问题, 泛型符号定不了上下线 ,但是通配符可以
//    public <T> void showHight1(List<T extends Number> list){
//        list.forEach(System.out::println);
//    }
    //设置通配符下限 ,
    public void showLower(List<? super ArrayList> list){
        list.forEach(System.out::println);
    }


    //可以用泛型通配符的 都可用泛型方法替代,泛型符号定不了上下线 ,但是通配符可以
    public static void main(String[] args) {
        List<String> list = Arrays.asList("11", "22");
        Generic_04 generic_04 = new Generic_04();
        generic_04.showWildcard(list);
        System.out.println("-------------------------");
        generic_04.showWildcard(list);
        //不可变对象list,不能添加
//        list.add("1111");
        List<Integer> integers = Arrays.asList(11, 22);
        generic_04.showHight(integers);
        System.out.println("-------------------------");

        // 子类
        ManagedList<Object> objects = new ManagedList<>();
        // 这里没报错??
        generic_04.showLower(objects);
    }

}
