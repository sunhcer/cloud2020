package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentHystrix8003Service;
import org.springframework.stereotype.Component;

@Component
public class PaymentHystrix8003ServiceFallback implements PaymentHystrix8003Service {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "消费者8182调用生产者8003的paymentInfo_OK方法,对方宕机,消费者熔断";
    }

    @Override
    public String paymentinfo_TimeOut(Integer id) {
        return "消费者8182调用生产者8003的paymentinfo_TimeOut方法,对方宕机,消费者熔断";
    }

//

    @Override
    public String paymentinfo_TimeOut1(Integer id) {
        return "8182调重试超时重试 ------对方宕机 已方熔断";
    }
}
