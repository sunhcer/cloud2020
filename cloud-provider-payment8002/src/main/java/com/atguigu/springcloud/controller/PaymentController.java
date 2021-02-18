package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    //---------------
    @Autowired
    private PaymentService paymentService;

    //用端口号来区分集群中相同服务名的机器
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        log.info("---------------新增订单---------------");
        return CommonResult.succ("操作成功"+result+serverPort);
    }

    @GetMapping("payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id")Long id){
        Payment payment=paymentService.getPaymentById(id);
        return CommonResult.succ("听说你很帅哦"+payment+serverPort);
    }

}
