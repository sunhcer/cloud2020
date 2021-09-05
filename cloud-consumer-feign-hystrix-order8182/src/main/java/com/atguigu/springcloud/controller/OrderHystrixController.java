package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrix8003Service;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrix8003Service paymentHystrix8003Service;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String PamentInfo_OK(@PathVariable("id") Integer id){
        String result=paymentHystrix8003Service.paymentInfo_OK(id);
        log.info(result);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "timeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    public String paymentInfo_TimeOut(@PathVariable("id")Integer id){
        /*获取链路追踪id 以在rocketBot中查询*/
        String traceId = TraceContext.traceId();
        System.out.println("traceId = " + traceId);
        int spanId = TraceContext.spanId();
        System.out.println("spanId = " + spanId);

        //使得当前链路打印日志信息
        ActiveSpan.error("报错------------------");

        String result=paymentHystrix8003Service.paymentinfo_TimeOut(id);
        log.info(result);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout2/{id}")
    public String paymentInfo_TimeOut2(@PathVariable("id")Integer id){
        String result=paymentHystrix8003Service.paymentinfo_TimeOut1(id);
        log.info(result);
        return result;
    }

    public String timeOutFallbackMethod(@PathVariable("id")Integer id){
        return "消费者8182调用生产者8003超过了自身的1.5秒时间限制,o(╥﹏╥)o";
    }

    public String payment_Global_FallbackMethod(){
        return "统一熔断返回o(╥﹏╥)o";
    }
}
