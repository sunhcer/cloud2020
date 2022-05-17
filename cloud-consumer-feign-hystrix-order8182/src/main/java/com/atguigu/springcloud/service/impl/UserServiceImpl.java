package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.UserMapper;
import com.atguigu.springcloud.entities.User;
import com.atguigu.springcloud.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 * @author sunhcer
 * @date 2021/05/17 10:40
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
