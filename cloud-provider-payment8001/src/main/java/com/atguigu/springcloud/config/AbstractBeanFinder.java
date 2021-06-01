package com.atguigu.springcloud.config;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractBeanFinder implements BeanFinder {
    public AbstractBeanFinder() {
    }

    public <T> Collection<T> find(Class<T> targetClass) {
        return this.find(targetClass, null);
    }

    public <T> Collection<T> find(Class<T> targetClass, Predicate<T> predicate) {
        Collection<T> beans = this.findBeans(targetClass);
        if (beans != null && !beans.isEmpty()) {
            Stream<T> stream = this.findAsStream(targetClass, predicate);
            return stream != null ? stream.collect(Collectors.toList()) : null;
        } else {
            return null;
        }
    }

    private <T> Stream<T> findAsStream(Class<T> targetClass, Predicate<T> predicate) {
        Collection<T> beans = this.findBeans(targetClass);
        if (beans != null && !beans.isEmpty()) {
            Stream<T> stream = beans.stream();
            return predicate != null ? stream.filter(predicate) : stream;
        } else {
            return null;
        }
    }

    public <T> T findOne(Class<T> targetClass, Predicate<T> predicate) {
        Collection<T> beans = this.findBeans(targetClass);
        if (beans != null && !beans.isEmpty()) {
            Stream<T> stream = this.findAsStream(targetClass, predicate);
            if (stream != null) {
                Optional<T> optional = stream.findAny();
                if (optional.isPresent()) {
                    return optional.get();
                }
            }
        }
        return null;
    }

    public <T> T findOne(Class<T> targetClass) {
        return this.findOne(targetClass, null);
    }

    protected abstract <T> Collection<T> findBeans(Class<T> var1);
}