package com.atguigu.springcloud.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
@Aspect // 切面
public class LogAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(LogAspect.class.getName());
    // 切入点，表达式是指com.remcarpediem.test.aop.service
    // 包下的所有类的所有方法
    @Pointcut("execution(* com.atguigu.springcloud.service..*(..))")
    public void aspect() {}
    
    /**
     * @Description
     * @author sunhcer
     * @date 2021/01/17 01:06
     * @param joinPoint 
     * @return void 
     */
    // 通知，在符合aspect切入点的方法前插入如下代码，并且将连接点作为参数传递
    @Before("aspect()")
    public void log(JoinPoint joinPoint) { //连接点作为参数传入
        if (LOGGER.isInfoEnabled()) {
            // 获得类名，方法名，参数和参数名称。
            Signature signature = joinPoint.getSignature();
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

            String[] argumentNames = methodSignature.getParameterNames();

            StringBuilder sb = new StringBuilder(className + "." + methodName + "(");

            for (int i = 0; i< arguments.length; i++) {
                Object argument = arguments[i];
                sb.append(argumentNames[i] + "->");
                sb.append(argument != null ? argument.toString() : "null ");
            }
            sb.append(")");
            LOGGER.info(sb.toString());
        }
    }
}
