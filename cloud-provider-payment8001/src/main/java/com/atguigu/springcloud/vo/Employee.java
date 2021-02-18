package com.atguigu.springcloud.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;

/**
 * 员工对象 employee
 * 
 * @author lixiankuo
 * @date 2021-01-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 部门ID */
    private String departmentId;

    /** 账号 */
    private String username;

}
