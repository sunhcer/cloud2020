package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * skywalking 配置类
 * @author sunhcer
 * @date 2021/09/06 01:34
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
public class AlarmMessage {
    private int scopeId;
    private String name;
    private String id0;
    private String id1;
    private String alarmMessage;
    private Long startTime;
}
