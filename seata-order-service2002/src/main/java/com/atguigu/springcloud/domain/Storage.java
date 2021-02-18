package com.atguigu.springcloud.domain;

/**
 * 哇塞
 * @author sunhcer
 * @date 2021/01/18 20:35
 **/
import lombok.Data;

@Data
public class Storage {

    private Long id;

    // 产品id
    private Long productId;

    //总库存
    private Integer total;


    //已用库存
    private Integer used;


    //剩余库存
    private Integer residue;
}
