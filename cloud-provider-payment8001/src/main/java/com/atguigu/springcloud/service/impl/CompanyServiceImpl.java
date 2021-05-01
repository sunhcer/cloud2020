package com.atguigu.springcloud.service.impl;

import java.util.List;

import com.atguigu.springcloud.dao.CompanyMapper;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.ICompanyService;
import com.atguigu.springcloud.vo.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 企业Service业务层处理
 * 
 * @author shixiaogang
 * @date 2021-01-23
 */
@Service
public class CompanyServiceImpl implements ICompanyService
{
    @Autowired
    private CompanyMapper companyMapper;

    /**
     * 查询企业
     * 
     * @param id 企业ID
     * @return 企业
     */
    @Override
    public Company selectCompanyById(String id)
    {
        return companyMapper.selectCompanyById(id);
    }

    /**
     * 查询企业列表
     * 
     * @param company 企业
     * @return 企业
     */
    @Override
    public List<Company> selectCompanyList(Company company)
    {
        return companyMapper.selectCompanyList(company);
    }

    /**
     * 新增企业
     * 
     * @param company 企业
     * @return 结果
     */
    @Override
    public int insertCompany(Company company)
    {
        return companyMapper.insertCompany(company);
    }

    /**
     * 修改企业
     * 
     * @param company 企业
     * @return 结果
     */
    @Override
    public int updateCompany(Company company)
    {
        return companyMapper.updateCompany(company);
    }

    /**
     * 批量删除企业
     * 
     * @param ids 需要删除的企业ID
     * @return 结果
     */
    @Override
    public int deleteCompanyByIds(String[] ids)
    {
        return companyMapper.deleteCompanyByIds(ids);
    }

    /**
     * 删除企业信息
     * 
     * @param id 企业ID
     * @return 结果
     */
    @Override
    public int deleteCompanyById(String id)
    {
        return companyMapper.deleteCompanyById(id);
    }

    @Override
    public List<Company> selectTwoLevel(String id) {
        return companyMapper.selectTwoLevel(id);
    }

    @Override
    public List<Company> selectThreeLevel(String id) {
        return companyMapper.selectThreeLevel(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Company> selectNON(String id) {
        return companyMapper.selectNON(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchUpdate(String serino) {
        List<Payment> list= companyMapper.selectBatch(serino);
        //普通批量 -- 更新
        //这里调mybatis的批处理,成功时返回回来的条数是1 ,需要注意
        int updateNum=companyMapper.batchUpdate(list,serino);
        //case when
        System.out.println("更新条数:"+updateNum);
        //switch
        return 0;
    }
}
