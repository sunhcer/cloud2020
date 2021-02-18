package com.atguigu.springcloud.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 企业对象 company
 * 
 * @author shixiaogang
 * @date 2021-01-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;


    private List<Department> departments;

    private List<Role> roles;

}