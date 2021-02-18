package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * quartz实现层
 * @author sunhcer
 * @date 2021/02/06 12:59
 **/

public interface QuartzService {
    public  int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
    public List<Payment> getPaymentList(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

}
