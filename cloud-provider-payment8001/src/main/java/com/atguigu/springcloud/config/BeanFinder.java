package com.atguigu.springcloud.config;

import java.util.Collection;
import java.util.function.Predicate;

public interface BeanFinder {
    <T> Collection<T> find(Class<T> var1);

    <T> Collection<T> find(Class<T> var1, Predicate<T> var2);

    <T> T findOne(Class<T> var1);

    <T> T findOne(Class<T> var1, Predicate<T> var2);
}