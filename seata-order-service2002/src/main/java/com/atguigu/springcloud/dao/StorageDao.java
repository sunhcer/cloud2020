package com.atguigu.springcloud.dao;

/**
 * 哇塞
 * @author sunhcer
 * @date 2021/01/18 20:37
 **/
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {

    //扣减库存信息
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}