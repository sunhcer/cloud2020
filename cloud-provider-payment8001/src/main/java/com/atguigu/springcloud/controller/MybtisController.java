package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.annotation.ControllerWebLog;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.ICompanyService;
import com.atguigu.springcloud.vo.Company;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * mybatis测试
 * @author sunhcer
 * @date 2021/01/23 11:40
 **/

@RestController
@Slf4j
@Api(tags = "mybatis测试相关接口")
public class MybtisController {


    @Autowired
    private ICompanyService iCompanyService;


    @GetMapping("payment/selectTwoLevel/{id}")
    @ControllerWebLog(name="测试2层嵌套")
    @ApiOperation("测试2层嵌套")
    public CommonResult selectTwoLevel(@ApiParam(value = "公司id") @PathVariable String id){
        List<Company> companies=iCompanyService.selectTwoLevel(id);
        return CommonResult.succ(companies);
    }


    @GetMapping("payment/selectThreeLevel/{id}")
    @ControllerWebLog(name="测试3层嵌套")
    public CommonResult selectThreeLevel(@PathVariable String id){
        List<Company> companies=iCompanyService.selectThreeLevel(id);
        return CommonResult.succ(companies);
    }

    @GetMapping("payment/selectNON/{id}")
    @ControllerWebLog(name="测试 n-1-n 的封装")
    public CommonResult selectNON(@PathVariable String id){
        List<Company> companies=iCompanyService.selectNON(id);
        return CommonResult.succ(companies);
    }



}
