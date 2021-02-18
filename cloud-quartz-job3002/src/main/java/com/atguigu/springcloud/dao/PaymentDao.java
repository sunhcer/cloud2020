package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper//等价于@Reposity但是mybatis里面用这个注解会有问题
public interface PaymentDao {
    public  int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);

    List<Payment> findAllPayment();
}
