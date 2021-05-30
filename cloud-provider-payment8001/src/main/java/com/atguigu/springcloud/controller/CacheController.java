package com.atguigu.springcloud.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.atguigu.springcloud.annotation.MemoryCaculateLog;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.User;
import com.atguigu.springcloud.service.UserService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 缓存controller
 *
 * @author sunhcer
 * @date 2021/05/30 14:08
 **/
@RestController
@RequestMapping("/cache")
@Slf4j
public class CacheController {

    @Autowired
    private UserService userService;

    @Autowired
    Cache<String,Object> caffeineCache;
    /**
     * caffeine 3种填充策略
     */

    //1: 手动
    //每次get key的时候指定一个同步的函数,如果可以不存在就调用这个函数
    public Object manualOperator(String key) {
        Cache<Object, Object> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .maximumSize(10)
                .build();

        //如果 key 不存在, 进入指定的函数生成value
        Object value = cache.get(key, t -> setValue(key).apply(key));
        cache.put("hello",value);

        //判断是否存在,如果不存在返回null
        Object ifPresent = cache.getIfPresent(key);
        //移除一个key
        cache.invalidate(key);
        return value;
    }

    public Function<String, Object> setValue(String key) {
        return t -> key + "value";
    }

    public static void main(String[] args) {
        CacheController cacheController = new CacheController();
        cacheController.manualOperator("");
    }

    /**
     * 先添加一个user,添加的时候放在缓存里面 , 查找的时候先去缓存里面找
     * 如果找到了就用缓存
     * @param id
     * @return
     */
    @GetMapping("/add")
    @MemoryCaculateLog
    public CommonResult addCache(@RequestParam Integer id){
        User user = new User();
        user.setAge(RandomUtil.randomInt(0,99));
        user.setName(RandomUtil.randomString(3));
        user.setEmail(RandomUtil.randomString("@",9));
//        user.setId(UUID.fastUUID().getMostSignificantBits());
        user.setId(Long.valueOf(id));
        userService.save(user);
        //放进缓存
        caffeineCache.put(String.valueOf(user.getId()),user);
        return CommonResult.succ(user);
    }

    @GetMapping("/query")
    @MemoryCaculateLog
    public CommonResult queryCache(@RequestParam Integer id){
        //getIfPresent 该方法如果没有get到,返回null
        //get 该方法需要提供一个额外的方法来设定默认值, 没有get到的时候返回默认值
        Object ifPresent = caffeineCache.getIfPresent(String.valueOf(id));
        if (ObjectUtil.isNotEmpty(ifPresent)){
            log.info("缓存中有值:");
            return CommonResult.succ(ifPresent);
        }else{
            log.info("缓存中没有值,调用get方法设定默认值");
            Object user1 = caffeineCache.get(String.valueOf(id), x -> {
                User user = new User();
                user.setName("get默认user");
                return user;
            });
            return CommonResult.succ(user1);
        }
    }

}
