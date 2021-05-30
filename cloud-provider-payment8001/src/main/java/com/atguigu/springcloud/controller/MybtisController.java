package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.annotation.ControllerWebLog;
import com.atguigu.springcloud.annotation.MemoryCaculateLog;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.User;
import com.atguigu.springcloud.service.ICompanyService;
import com.atguigu.springcloud.service.UserService;
import com.atguigu.springcloud.vo.Company;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * mybatis测试
 * @author sunhcer
 * @date 2021/01/23 11:40
 **/

@RestController
@Slf4j
@Api(tags = "mybatis测试相关接口")
public class MybtisController {


    @Qualifier
    private ICompanyService iCompanyService;

    @Autowired
    private UserService userService;

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

    @GetMapping("payment/batchUpdate")
    public CommonResult batchUpdate(@RequestParam String serino){
        int updateNum=iCompanyService.batchUpdate(serino);
        return CommonResult.succ(updateNum);
    }

    @GetMapping("payment/memoryCost")
    @MemoryCaculateLog(name="内存计算")
    public CommonResult memoryCost(@RequestParam int num){
        Integer integer = Optional.ofNullable(num).orElse(1);
        for (int i = 0; i <integer ; i++) {
            System.out.println(i);
        }
        return CommonResult.succ("成功");
    }


    /**
     * 和mybatisplus冲突,需要加配置类paginationInterceptor
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("payment/page")
    @MemoryCaculateLog
    public CommonResult getpage(@RequestParam int pageSize,@RequestParam int pageNum){
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userService.list(new QueryWrapper<User>().lambda().ne(User::getId,1111L));
        PageInfo<User> userPageInfo = new PageInfo<>(list);
        return CommonResult.succ(userPageInfo);
    }

}
