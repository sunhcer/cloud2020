package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;


public interface UserService extends IService<User> {
    /**
     * rpa心跳检测
     * @Description
     * @author sunhcer
     * @date 2022/06/19 18:54
     * @param serviceId
     * @return void
     */
    void heartBeat(String serviceId);
}
