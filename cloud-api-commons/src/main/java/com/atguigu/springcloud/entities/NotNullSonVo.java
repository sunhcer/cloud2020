package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotNullSonVo {
    private String skuid;

    @NotEmpty(message = "sku-son名称不能为null")
    private String name;

    @Valid
//    @NotEmpty
    private NotNullGrandSonVo notNullGrandSonVo;

}
