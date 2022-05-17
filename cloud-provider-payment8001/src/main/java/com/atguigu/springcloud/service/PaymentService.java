package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentService {
    public  int create(Payment payment);
    public Payment getPaymentById(@Param("id")Long id);
    public List<Payment> getPaymentList(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize);

    String collect();

//    int updateCountry(StringBuilder stringBuilder);
//
//    int updateCertificate(StringBuilder stringBuilder);
//
//    int changeCoin(StringBuilder stringBuilder);
//
//    int sortCountry(StringBuilder stringBuilder);
//
//    int sortCertificate(StringBuilder stringBuilder);
//
//    int changeAndSortExchange(StringBuilder stringBuilder);
//
//    int changeContryForMapping(StringBuilder stringBuilder);
//
//    int changeCodeNoForMapping(String codeNo);
//
//    int addCountryEng(StringBuilder stringBuilder);
//
//    int changeIndustryForMapping(StringBuilder stringBuilder);
//
//    void addNameForMapping();
//
//    int supplementContryForMapping(StringBuilder stringBuilder);
//
//    int addGDCountryForMapping(StringBuilder stringBuilder);
//
//    int addGDCITCountryForMapping(StringBuilder stringBuilder);
//
//    int addGDCITCertifyForMapping(StringBuilder stringBuilder);
//
//    int changeGDCITIndustryForMapping(StringBuilder stringBuilder);
//
//    int addGDCITCountryDiffForMapping(StringBuilder stringBuilder);
//
//    int addGDTPcoinsForMapping(StringBuilder stringBuilder);
//
//    int addGDTPIndustryForMapping(StringBuilder stringBuilder);
}
