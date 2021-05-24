package com.atguigu.springcloud.generics;

/**
 * 泛型接口
 * @author sunhcer
 * @date 2021/05/24 22:45
 **/
public interface Generic_01<T> {
    /**
     * 泛型解决的问题: 是将运行期的类型异常 提前到编译期
     * 本质是类型参数化
     */
     void show(T t);

}
