package com.atguigu.springcloud.config;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * plus 时间处理配置类
 *
 * @author sunhcer
 * @date 2021/05/23 09:20
 **/
@Component
public class TimeMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        String fieldName = "createTime";
        boolean createTime = metaObject.hasSetter("createTime");
        if (createTime) {
            setFieldValByName(fieldName, DateUtil.now(),metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String fieldName ="updateTime";
        boolean b = metaObject.hasSetter(fieldName);
        Object fieldValByName = getFieldValByName(fieldName, metaObject);
        //判断实体是否有了值,有值则不进行自动填充
        if (b){
            setFieldValByName(fieldName,DateUtil.now(),metaObject);
        }
    }
}
