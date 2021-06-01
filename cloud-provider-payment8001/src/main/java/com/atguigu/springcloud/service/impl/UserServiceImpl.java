package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.UserMapper;
import com.atguigu.springcloud.entities.User;
import com.atguigu.springcloud.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 * @author sunhcer.shi
 * @date 2021/05/19 14:05
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
