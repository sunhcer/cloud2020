package com.atguigu.springcloud.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 企业的角色
 * @author sunhcer
 * @date 2021/01/25 20:58
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private String name;

    private String companyId;

}
