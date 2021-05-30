package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.CodeLibrary;
//import com.atguigu.springcloud.entities.EntCodeMappingEntity;
import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper//等价于@Reposity但是mybatis里面用这个注解会有问题
public interface PaymentDao {
    public  int create(Payment payment);
    public Payment getPaymentById(@Param("id")Long id);

    List<Payment> findAllPayment();

//    void updateCountry(@Param("itemNo") String itemNo, @Param("countryNameNew") String countryNameNew, @Param("countrynameOld") String countrynameOld);
//
//    int updateCertificate(@Param("itemNoNeed") String itemNoNeed,@Param("certificateNameNeed") String certificateNameNeed,@Param("type") String type);
//
//    int updateCoin(@Param("itemNoNeed") String itemNoNeed, @Param("coinNameNeed") String coinNameNeed, @Param("type") String type,@Param("sortNo") Integer sortNo);
//
//    int sortCountry(@Param("itemNo") String itemNo,@Param("countryNameNew") String countryNameNew,@Param("sortNo") Integer sortNo);
//
//    int sortCertificate(@Param("itemNoNeed") String itemNoNeed,@Param("certificateNameNeed") String certificateNameNeed, @Param("sortNo")Integer sortNo);
//
//    int updateExchange(@Param("itemNoNeed") String itemNoNeed,@Param("coinNameNeed") String coinNameNeed,@Param("type") String type,@Param("sortNo") Integer sortNo);
//
//
//    CodeLibrary queryCodeLibrary(@Param("hmCodeNo") String hmCodeNo,@Param("hmItemName") String hmItemName);
//
////    int insertEntCodeMapping(EntCodeMappingEntity entCodeMappingEntity);
////
////    List<CodeLibrary> queryCodeLibraryList(String codeNo);
//
//    int addCountryEng(@Param("itemNo") String itemNo,@Param("codeNo") String codeNo, @Param("code1") String code1);
//
//    CodeLibrary queryCodeLibraryForIndustry(@Param("hmCodeNo") String hmCodeNo,@Param("hmItemName") String hmItemName,@Param("itemNo") String itemNo);
//
////    List<EntCodeMappingEntity> queryCodeLibraryListAll();
////
////    int addNameForMapping(@Param("entCodeMappingEntity") EntCodeMappingEntity entCodeMappingEntity);
//
//    CodeLibrary queryLikeNameCodeLibrary(@Param("hmCodeNo") String hmCodeNo,@Param("hmItemName") String hmItemName);
//
//    CodeLibrary queryCodeLibraryByCodeAndItemNo(@Param("hmCodeNo") String hmCodeNo,@Param("itemNo") String itemNo);
//
//    CodeLibrary queryCodeLibrary2(@Param("hmCodeNo") String hmCodeNo,@Param("likeItemNo") String likeItemNo,@Param("itemName") String itemName);
}
