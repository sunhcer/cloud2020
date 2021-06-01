package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.vo.Company;

import java.util.List;


/**
 * 企业Service接口
 * 
 * @author shixiaogang
 * @date 2021-01-23
 */
public interface ICompanyService 
{
    /**
     * 查询企业
     * 
     * @param id 企业ID
     * @return 企业
     */
    public Company selectCompanyById(String id);

    /**
     * 查询企业列表
     * 
     * @param company 企业
     * @return 企业集合
     */
    public List<Company> selectCompanyList(Company company);

    /**
     * 新增企业
     * 
     * @param company 企业
     * @return 结果
     */
    public int insertCompany(Company company);

    /**
     * 修改企业
     * 
     * @param company 企业
     * @return 结果
     */
    public int updateCompany(Company company);

    /**
     * 批量删除企业
     * 
     * @param ids 需要删除的企业ID
     * @return 结果
     */
    public int deleteCompanyByIds(String[] ids);

    /**
     * 删除企业信息
     * 
     * @param id 企业ID
     * @return 结果
     */
    public int deleteCompanyById(String id);

    List<Company> selectTwoLevel(String id);

    List<Company> selectThreeLevel(String id);

    List<Company> selectNON(String id);

    int batchUpdate(String serino);

    CommonResult selectCache();

}
