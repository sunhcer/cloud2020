package com.atguigu.springcloud.service.impl;

import cn.hutool.json.JSONUtil;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.User;
import com.atguigu.springcloud.feign.PaymentProviderFeign;
import com.atguigu.springcloud.service.PaymentHystrix8003Service;
import com.atguigu.springcloud.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
public class PaymentHystrix8003ServiceFallback implements PaymentHystrix8003Service {

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentProviderFeign paymentProviderFeign;

    @Override
    public String paymentInfo_OK(Integer id) {
        List<User> list = userService.list(new LambdaQueryWrapper<User>()
                .eq(User::getId, 1L));
        System.out.println("JSONUtil.toJsonPrettyStr(list) = " + JSONUtil.toJsonPrettyStr(list));
//        CommonResult syncPaymentById = paymentProviderFeign.getSyncPaymentById(1L);
//        System.out.println("syncPaymentById.toString() = " + syncPaymentById.toString());
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
