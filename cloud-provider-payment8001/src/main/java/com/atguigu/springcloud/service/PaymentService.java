package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentService {
    public  int create(Payment payment);
    public Payment getPaymentById(@Param("id")Long id);
    public List<Payment> getPaymentList(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize);

    int updateCountry(StringBuilder stringBuilder);

    int updateCertificate(StringBuilder stringBuilder);

    int changeCoin(StringBuilder stringBuilder);

    int sortCountry(StringBuilder stringBuilder);

    int sortCertificate(StringBuilder stringBuilder);

    int changeAndSortExchange(StringBuilder stringBuilder);
}
