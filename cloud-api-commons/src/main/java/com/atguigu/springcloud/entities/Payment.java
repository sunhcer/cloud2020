package com.atguigu.springcloud.entities;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.beans.Transient;
import java.io.Serializable;
import java.math.BigDecimal;


//lombook
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("订单实体")
public class Payment
//        implements Serializable
{

    public Payment(Long id, BigDecimal amount, BigDecimal amount1, BigDecimal amount2, BigDecimal amount3, BigDecimal count1, BigDecimal count2, BigDecimal count3) {
        this.id = id;
        this.amount = amount;
        this.amount1 = amount1;
        this.amount2 = amount2;
        this.amount3 = amount3;
        this.count1 = count1;
        this.count2 = count2;
        this.count3 = count3;
    }

    @ApiModelProperty("订单号")
    private Long id;
    @ApiModelProperty("订单号")
    private BigDecimal amount;
    @ApiModelProperty("订单号")
    private BigDecimal amount1;
    @ApiModelProperty("订单号")
    private BigDecimal amount2;
    @ApiModelProperty("订单号")
    private BigDecimal amount3;

    @ApiModelProperty("订单号")
    private BigDecimal count1;
    @ApiModelProperty("订单号")
    private BigDecimal count2;
    @ApiModelProperty("订单号")
    private BigDecimal count3;
    @ApiModelProperty("序列")
    private String serial;
//    @Transient
//    private String se;
    @ApiModelProperty("编码")
    private String itemNo;

    public Payment(Long id, String serial, String itemNo) {
        this.id = id;
        this.serial = serial;
        this.itemNo = itemNo;
    }
}
