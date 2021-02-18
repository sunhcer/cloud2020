package com.atguigu.springcloud.service;

import com.atguigu.springcloud.service.impl.PaymentHystrix8003ServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-provider-hystrix-payment",fallback = PaymentHystrix8003ServiceFallback.class )
//为什么要在这里做熔断呢?
// 因为如果生产者宕机了,那生产者方是无法处理此场景的,而消费者controller里面的熔断可能不止调用了这一个方法:1,这个方法熔断了,去备用方法拿需要的信息;或者就是为了不让这个调不通的方法,持续占着资源直到controller熔断
public interface PaymentHystrix8003Service {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id")Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentinfo_TimeOut(@PathVariable("id")Integer id);

//    @GetMapping("/payment/hystrix/circuitBreaker/{id}")
//    public String paymentCircuitBreaker(@PathVariable("id")Integer id);

    @GetMapping("/payment/hystrix/timeoutRetry/{id}")
    public String paymentinfo_TimeOut1(@PathVariable("id")Integer id);
}
