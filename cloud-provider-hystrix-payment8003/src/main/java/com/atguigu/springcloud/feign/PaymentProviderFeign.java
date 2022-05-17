package com.atguigu.springcloud.feign;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("cloud-payment-service")
public interface PaymentProviderFeign {

    @GetMapping("payment/getSync/{id}")
    public CommonResult getSyncPaymentById(@PathVariable("id")Long id);
}
