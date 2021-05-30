package com.atguigu.springcloud;

import com.atguigu.springcloud.entities.User;
import com.atguigu.springcloud.service.UserService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * xxl-job
 * @author sunhcer
 * @date 2021/05/30 13:07
 **/
@Component
@Slf4j
public class jobhandler {

    @Autowired
    private UserService userService;

    @XxlJob("selectUserJobHandler")
    public void selectUserJobHandler(){
        List<User> list = userService.list(null);
        list.forEach(System.out::println);
    }
}
