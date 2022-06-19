package com.atguigu.springcloud.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.atguigu.springcloud.dao.UserMapper;
import com.atguigu.springcloud.entities.User;
import com.atguigu.springcloud.service.UserService;
import com.atguigu.springcloud.util.ThreadLocalUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.benmanes.caffeine.cache.Cache;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author sunhcer
 * @date 2021/05/17 10:40
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    //本地缓存: 格式: serviceId:lastUpdateTime
    @Autowired
    Cache<String, Object> caffeineCache;

    @Autowired
    private UserMapper userMapper;

    private static final Long DELAY = 5000L;

    //接口检测到的超时次数
    private int delayTime = 0;

    //定时任务检测到的超时次数
//    private int ;

    private ScheduledExecutorService pool = Executors.newScheduledThreadPool(7);

    //todo 心跳需要一张额外的表,每当更改状态时才同步到rpaService
    @Override
    public void heartBeat(String serviceId) {
        //先从本地缓存中拿出来,如果没有,或者间隔时间大于心跳间隔
//        Long lastUpdateTime = caffeineCache.getIfPresent(serviceId);
        User select = userMapper.selectById(Long.valueOf(serviceId));
        String lastUpdateTimeDB = select.getUpdateTime();
        Integer version = select.getVersion();
        long time = DateUtil.parse(lastUpdateTimeDB).getTime();
        select.setUpdateTime(DateUtil.now());

        //驗證參數的map
        Map<String, Object> map = new HashMap<>();
        map.put("serviceId", serviceId);
        map.put("lastUpdateTime", System.currentTimeMillis());

        //判断是否是重连
        String name = select.getName();
        if ("已停用".equals(name)){
            //重连
            //更新数据库时间
            log.info("识别到重连,serviceId:{},",serviceId);
            select.setVersion(0);
            select.setName("空闲中");
            userMapper.updateById(select);
            checkTask(map);
        }else{
            //正常心跳
            if (System.currentTimeMillis() - time > DELAY) {
                System.out.println("识别到超时,次数:" + (version + 1));
                select.setVersion(version + 1);
            } else {
                select.setVersion(0);
            }
            //更新数据库时间
            userMapper.updateById(select);
            checkTask(map);
        }

    }


    private void checkTask(Map<String, Object> map) {
        //如果識別到超時,就啟動一個定時任務,5秒後再查一次該serviceid,確認lastupdatetime是否別修改了
        pool.schedule(() -> {
            log.info("开始执行定时任务");
            System.out.println("开始执行定时任务");
            try {
                ThreadLocalUtil.set(map);
                checkTime();
            } catch (Exception e) {
                log.error("定时任务保活异常,serviceId:{}", ThreadLocalUtil.get("serviceId"), e);

            } finally {
                ThreadLocalUtil.remove();
            }
        }, 30000, TimeUnit.MILLISECONDS);
    }


    private void checkTime() {
        Object serviceIdOb = ThreadLocalUtil.get("serviceId");
        Object lastUpdateTimeOb = ThreadLocalUtil.get("lastUpdateTime");
        if (ObjectUtil.isNull(serviceIdOb) || ObjectUtil.isNull(lastUpdateTimeOb)) {
            log.error("ThreadLocal丢失参数");
            return;
        }
        Integer serviceId = Integer.valueOf((String) serviceIdOb);
        Long lastUpdateTime = (Long) lastUpdateTimeOb;

        User user = userMapper.selectById(serviceId);
        long time = DateUtil.parse(user.getUpdateTime()).getTime();
        if (lastUpdateTime.equals(time)) {
            log.error("serviceId:{},机器人失活,更新为已停用", serviceId);
            user.setName("已停用");
            userMapper.updateById(user);
        } else {
            log.info("心跳回复正常,serviceId:{}", serviceId);
            user.setName("空闲中");
            userMapper.updateById(user);
        }
    }
}
