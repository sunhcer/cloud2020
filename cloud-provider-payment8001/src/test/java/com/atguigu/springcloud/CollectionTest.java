package com.atguigu.springcloud;

import cn.hutool.core.collection.CollUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 集合测试类
 *
 * @author sunhcer.shi
 * @date 2021/04/30 09:18
 **/

public class CollectionTest {
    //测试类配置切面
    public static long concurrentTime1, concurrentTime2, concurrentMemory1, concurrentMemory2;

    @Before
    public void before() {
        //得到出个必须开始的系统时间(纳秒级, 最终转换为毫秒级,保留两位小数)
        concurrentTime1 = System.nanoTime();
        //得到虚拟机运行,程序开始运行时jvm所占用的内存
        Runtime runtime = Runtime.getRuntime();
        concurrentMemory1 = runtime.totalMemory() - runtime.freeMemory();
    }

    @After
    public void after() {
        //得到程序执行完毕的执行时间(纳秒级)
        concurrentTime2 = System.nanoTime();
        //得到执行完毕时jvm所占用的内存(byte)
        Runtime runTime = Runtime.getRuntime();
        concurrentMemory2 = runTime.totalMemory() - runTime.freeMemory();

        /**
         *计算start和end之间的代码执行期间所消耗时间与内存
         * 1ms=1000us=1000000ns
         * 1MB=1024 kb = 1024*1024 byte
         */
        String costTime = String.valueOf((concurrentTime2 - concurrentTime1) / 1000000);
        String costMemory = String.valueOf((concurrentMemory2 - concurrentMemory1) / (1024 * 1024));
        System.out.println("执行时间:" + costTime + " ms");
        System.out.println("内存消耗:" + costMemory + " mb");
    }


    @Test
    public void arrayAddAll() {
        List list = CollUtil.newArrayList();
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        List listtemp = CollUtil.newArrayList();
        listtemp.addAll(list);
    }

    @Test
    public void collectionAddAll() {
        List list = CollUtil.newArrayList();
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        List listtemp = CollUtil.newArrayList();
         Collections.addAll(list,listtemp);
    }

    /**
     * 数据量  array        collect
     * 1w       5ms         8ms
     * 5w       9ms         9ms
     * 10w      9ms         8ms
     * 100w     25ms        20ms
     * 500w     153ms       137ms
     * 1000w    4128ms      2427ms
      */

}
