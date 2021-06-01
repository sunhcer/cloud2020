package com.atguigu.springcloud;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.atguigu.springcloud.config.RedisStringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * redis测试类
 * @author sunhcer.shi
 * @date 2021/05/27 09:20
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private RedisStringUtil redisUtil;

    @Test
    public void test(){
        Object kkk1 = redisUtil.get("kkk");
        if (ObjectUtil.isEmpty(kkk1)){
            redisUtil.set("kkk","0");
        }
        Long kkk = redisUtil.incrBy("kkk", 1);
        System.out.println(kkk);

    }
}
