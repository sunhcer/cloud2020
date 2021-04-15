package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.vo.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 企业Mapper接口
 * 
 * @author shixiaogang
 * @date 2021-01-23
 */
@Mapper
public interface CompanyMapper 
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
     * 删除企业
     * 
     * @param id 企业ID
     * @return 结果
     */
    public int deleteCompanyById(String id);

    /**
     * 批量删除企业
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCompanyByIds(String[] ids);

    /**
     * @Description
     * @author sunhcer
     * @date 2021/01/23 11:50
     * @param id
     * @return java.util.List<com.atguigu.springcloud.vo.Company>
     */
    List<Company> selectTwoLevel(String id);

    List<Company> selectThreeLevel(String id);

    List<Company> selectNON(String id);

    List<Payment> selectBatch(String serino);

    int batchUpdate(@Param("list") List<Payment> list,@Param("serino") String serino);
}
