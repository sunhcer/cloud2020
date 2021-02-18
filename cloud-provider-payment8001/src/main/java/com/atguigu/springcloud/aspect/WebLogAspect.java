package com.atguigu.springcloud.aspect;


import com.atguigu.springcloud.annotation.ControllerWebLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * controller层日志
 * @author sunhcer
 * @date 2021/01/17 01:09
 **/
@Aspect
@Component
@Order(100)
public class WebLogAspect {
    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class.getName());

    private final static String START_TIME="startTime";
    private final static String REQUEST_PARAMS="requestParams";
    private ThreadLocal threadLocal=new ThreadLocal();

    @Pointcut("execution(* com.atguigu.springcloud.controller..*(..))")
    public void webLog() {}

    /**
     * @Description
     * @author sunhcer
     * @date 2021/01/17 01:14
     * @param joinPoint
     * @param controllerWebLog
     * @return void
     */
    @Before(value = "webLog()&& @annotation(controllerWebLog)")
    public void doBefore(JoinPoint joinPoint, ControllerWebLog controllerWebLog) {
        // 开始时间。
        long startTime = System.currentTimeMillis();
        Map<String, Object> threadInfo = new HashMap<>();
        threadInfo.put(START_TIME, startTime);
        // 请求参数。
        StringBuilder requestStr = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        String[] argumentNames = methodSignature.getParameterNames();
        if (args != null && args.length > 0) {
            for (int i = 0; i <args.length ; i++) {
                Object arg = args[i];
                requestStr.append(argumentNames[i] + "->");
                requestStr.append(arg!=null?arg.toString():"null").append(" , ");
            }
        }
        threadInfo.put(REQUEST_PARAMS, requestStr.toString());
        threadLocal.set(threadInfo);
        logger.info("{}接口开始调用:requestData={}", controllerWebLog.name(), threadInfo.get(REQUEST_PARAMS));
    }


    @AfterReturning(value = "webLog()&& @annotation(controllerWebLog)", returning = "res")
    public void doAfterReturning(ControllerWebLog controllerWebLog, Object res) {
        Map<String, Object> threadInfo = (Map<String, Object>) threadLocal.get();
        long takeTime = System.currentTimeMillis() - (long) threadInfo.getOrDefault(START_TIME, System.currentTimeMillis());
//        if (controllerWebLog.intoDb()) {
//            insertResult(controllerWebLog.name(), (String) threadInfo.getOrDefault(REQUEST_PARAMS, ""),
//                    JSON.toJSONString(res), takeTime);
//        }
        threadLocal.remove();
        logger.info("{}接口结束调用:耗时={}ms,result={}", controllerWebLog.name(),
                takeTime, res);
    }

    @AfterThrowing(value = "webLog()&& @annotation(controllerWebLog)", throwing = "throwable")
    public void doAfterThrowing(ControllerWebLog controllerWebLog, Throwable throwable) {
        Map< String, Object> threadInfo = (Map<String, Object>) threadLocal.get();
//        if (controllerWebLog.intoDb()) {
//            insertError(controllerWebLog.name(), (String)threadInfo.getOrDefault(REQUEST_PARAMS, ""),
//                    throwable);
//        }
        threadLocal.remove();
        logger.error("{}接口调用异常，异常信息{}",controllerWebLog.name(), throwable);
    }
}
