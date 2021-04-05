package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper//等价于@Reposity但是mybatis里面用这个注解会有问题
public interface PaymentDao {
    public  int create(Payment payment);
    public Payment getPaymentById(@Param("id")Long id);

    List<Payment> findAllPayment();

    void updateCountry(@Param("itemNo") String itemNo, @Param("countryNameNew") String countryNameNew, @Param("countrynameOld") String countrynameOld);

    int updateCertificate(@Param("itemNoNeed") String itemNoNeed,@Param("certificateNameNeed") String certificateNameNeed,@Param("type") String type);

    int updateCoin(@Param("itemNoNeed") String itemNoNeed, @Param("coinNameNeed") String coinNameNeed, @Param("type") String type,@Param("sortNo") Integer sortNo);

    int sortCountry(@Param("itemNo") String itemNo,@Param("countryNameNew") String countryNameNew,@Param("sortNo") Integer sortNo);

    int sortCertificate(@Param("itemNoNeed") String itemNoNeed,@Param("certificateNameNeed") String certificateNameNeed, @Param("sortNo")Integer sortNo);

    int updateExchange(@Param("itemNoNeed") String itemNoNeed,@Param("coinNameNeed") String coinNameNeed,@Param("type") String type,@Param("sortNo") Integer sortNo);
}
