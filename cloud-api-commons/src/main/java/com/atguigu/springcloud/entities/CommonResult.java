package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;//http编码
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;

    }

//    public CommonResult(Integer code, String message, T data) {
//        this.code = code;
//        this.message = message;
//        this.data = data;
//    }

    public static CommonResult succ(String message){
        return new CommonResult<>(400,message);
    }

    public static CommonResult succ(Object data){
        return new CommonResult(400,"操作成功",data);
    }
}
