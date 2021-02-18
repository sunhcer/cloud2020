package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotNullVo {
    private String skuid;

    @NotEmpty(message = "sku名称不能为null")
    private String name;
    @Valid
    private NotNullSonVo notNullSonVo;


}
