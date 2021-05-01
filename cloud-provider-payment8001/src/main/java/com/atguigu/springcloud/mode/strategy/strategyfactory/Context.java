package com.atguigu.springcloud.mode.strategy.strategyfactory;

import com.atguigu.springcloud.annotation.StrategyTypeAnnotation;
import com.atguigu.springcloud.mode.strategy.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 策略工厂模式
 * @author sunhcer.shi
 * @date 2021/04/13 14:22
 **/
@Configuration
public class Context {
    //抽象策略集合
    private static Map<String, Strategy> STRATEGIES_CACHE=new ConcurrentHashMap<>();

    /**
     * 初始化策略列表:方式一, 手动注册
     */
    public static void register(String type,Strategy strategy){
        STRATEGIES_CACHE.put(type,strategy);
    }
    /**
     * 初始化策略列表的方式二, 使用Spring的ApplicationContext , 反射获取所有实现类,实现自动注册
     */
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    private void init(){
        Map<String,Strategy> beansOfType = applicationContext.getBeansOfType(Strategy.class);
        beansOfType.entrySet().forEach(source->{
            StrategyTypeAnnotation type=source.getValue().getClass().getDeclaredAnnotation(StrategyTypeAnnotation.class);
            STRATEGIES_CACHE.put(type.value(), source.getValue());
        });
    }


    //封装后的策略方法
    public void doAnything(String type){
        Strategy strategy=STRATEGIES_CACHE.get(type);
        if (strategy!=null){
            strategy.doSomething();
        }else{
            throw new RuntimeException("");
        }
    }
}
