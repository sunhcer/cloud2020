package com.atguigu.springcloud;

import com.atguigu.springcloud.dao.UserMapper;
import com.atguigu.springcloud.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusApplicationTests {
    // 继承了BaseMapper，所有的方法都来自己父类
// 我们也可以编写自己的扩展方法！
    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
// 参数是一个 Wrapper ，条件构造器，这里我们先不用 null
// 查询全部用户
        User user = userMapper.selectById(1);
        System.out.println("user = " + user);
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
}