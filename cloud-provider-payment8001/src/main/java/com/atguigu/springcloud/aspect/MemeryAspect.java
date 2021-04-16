package com.atguigu.springcloud.aspect;

import com.atguigu.springcloud.annotation.MemoryCaculateLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * 内存分析切面
 *
 * @author sunhcer
 * @date 2021/01/17 01:09
 **/
@Component
@Aspect
@Order(150)
//@EnableAspectJAutoProxy
public class MemeryAspect {
    private final static Logger logger = LoggerFactory.getLogger(MemeryAspect.class);

    private final static String START_MEMORY = "startMemory";
    private final static String START_TIME = "startTime";
    private ThreadLocal threadLocal = new ThreadLocal();

    @Pointcut("execution(* com.atguigu.springcloud..*(..))")
    public void memoryCaculate() {
    }

    @Before(value = "memoryCaculate()&& @annotation(memoryCaculateLog)")
    public void doBefore(JoinPoint joinPoint, MemoryCaculateLog memoryCaculateLog) {
        // 开始
        /*计算某一段程序消耗的内存和时间*/
        Map<String, Object> threadInfo = new HashMap<>(16);
        Runtime r = Runtime.getRuntime();
        //计算内存前先垃圾回收一次
        r.gc();
        //开始Time
        long start = System.currentTimeMillis();
        // 开始Memory
        long startMem = r.freeMemory();
        //获取请求参数和方法名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("target method:" + methodSignature.getName());
        System.out.println("目标方法的简单类名:" + methodSignature.getDeclaringType());
        System.out.println("目标方法的所属类的类名:" + methodSignature.getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString((joinPoint.getSignature().getModifiers())));

        Object[] args = joinPoint.getArgs();
        String[] argumentNames = methodSignature.getParameterNames();
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                System.out.println("第" + (i+1 )+ "个参数为:" + args[i]);
            }
        }
        threadInfo.put(START_MEMORY, startMem);
        threadInfo.put(START_TIME, System.currentTimeMillis());
        threadLocal.set(threadInfo);
    }

    @AfterReturning(value = "memoryCaculate()&& @annotation(memoryCaculateLog)", returning = "res")
    public void doAfterReturning(MemoryCaculateLog memoryCaculateLog, Object res) {
        Map<String, Object> threadInfo = (Map<String, Object>) threadLocal.get();
        long startMem = (long) threadInfo.get(START_MEMORY);
        long startTime = (long) threadInfo.get(START_TIME);
        long endTime = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        Long endMem = runtime.freeMemory();
        System.out.println("消耗内存为:" + String.valueOf((startMem - endMem) / (1024 * 1024)) + "mb");
        System.out.println("消耗时间为:" + String.valueOf(endTime - startTime) + "ms");
        System.out.println("开始清空threadLocal 避免内存泄漏:");
        threadLocal.remove();
    }
}
