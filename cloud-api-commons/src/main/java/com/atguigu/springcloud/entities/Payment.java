package com.atguigu.springcloud.entities;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.beans.Transient;
import java.io.Serializable;


//lombook
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("订单实体")
public class Payment
//        implements Serializable
{
    @ApiModelProperty("订单号")
    private Long id;
    @ApiModelProperty("序列")
    private String serial;
//    @Transient
//    private String se;
    @ApiModelProperty("订单数量")
    private Long amount;
}
