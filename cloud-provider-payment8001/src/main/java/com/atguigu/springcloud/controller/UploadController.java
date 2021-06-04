package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 * @author sunhcer.shi
 * @date 2021/06/03 21:04
 **/
@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {

    @Autowired
    private PaymentService paymentService;

    @ApiOperation(value="单文件上传")
    @PostMapping("/singleUpload")
    @ResponseBody
    public CommonResult singleUpload(@RequestParam String userId, @RequestPart MultipartFile file){
        paymentService.singleUpload(userId,file);
        return CommonResult.succ("1111");
    }
}
