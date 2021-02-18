package com.atguigu.springcloud.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 部门对象 department
 * 
 * @author lixiankuo
 * @date 2021-01-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 名称 */
    private String name;

    /** 公司ID */
    private String companyId;


    private List<Employee> employees;

}
