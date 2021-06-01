package com.atguigu.springcloud.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.atguigu.springcloud.config.DemoDataListener;
import com.atguigu.springcloud.constant.CommonConstant;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * excel解析controller
 * @author sunhcer.shi
 * @date 2021/04/23 15:53
 **/
@RestController
@RequestMapping("/excelParse")
@Slf4j
public class ExcelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    private PaymentService paymentService;

    /**
     * 自动生成一级/二级字典
     * @return
     */
    @GetMapping("/generateTwoLevel")
    public CommonResult generateTwoLevel(){
        paymentService.generateTwoLevel();
        return CommonResult.succ("111");
    }


    /**
     * 自动填充指定列到已有字典的指定补充(relative_code)列
     */

}
