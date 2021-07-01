package com.atguigu.springcloud;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.atguigu.springcloud.annotation.MemoryCaculateLog;
import com.atguigu.springcloud.aspect.MemeryAspect;
import com.atguigu.springcloud.controller.MybtisController;
import com.atguigu.springcloud.entities.User;
import org.aspectj.lang.annotation.AfterReturning;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("com.atguigu.springcloud")
public class HuToolTest {
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
        String costMemory = String.valueOf(concurrentMemory2 - concurrentMemory1 / (1024 * 1024));
        System.out.println("执行时间:" + costTime);
        System.out.println("内存消耗:" + costMemory);
        System.out.println("---------------执行时间为：" + costTime.substring(0, costTime.equals("0.0") ? 1 : (costTime.indexOf(".") + 3)) + " ms, 消耗内存：" + costMemory.substring(0, costMemory.equals("0.0") ? 1 : (costMemory.indexOf(".") + 3)) + " M + !---------------");
    }

    @Autowired
    MybtisController mybtisController;

    @Test
    public void CollUtilTest() {
        String[] strs = new String[]{"a", "b", "c", "d"};
        //string数组转list
        ArrayList<String> list = CollUtil.newArrayList(strs);
        System.out.println(list);

        //join 拼接list为 str
        String join = CollUtil.join(list, "#");
        String join1 = CollUtil.join(list, "");
        System.out.println("join:" + join + " join1: " + join1);

        // newArrayList 新建arraylist并 填充元素
        ArrayList<Object> objects = CollUtil.newArrayList();
        List<String> listStr = CollUtil.newArrayList();
        System.out.println();

        //sortPageAll 多个集合放进一个列表中，根据指定的顺序排序，可以选择其指定页数（指定页面条数）的结果
        List<Integer> list1 = CollUtil.newArrayList(1, 45, 5);
        List<Integer> list2 = CollUtil.newArrayList(9, 2, 3);
        List<Integer> list3 = CollUtil.newArrayList(8, 6);
        List<Integer> listSort = CollUtil.sortPageAll(0, 5,
                (Integer o1, Integer o2) -> {
                    return o1.compareTo(o2);
                }, list1, list2, list3);

        System.out.println("hutool的多list排序分页结果:" + listSort);

        //newHashMap
        Map map = CollUtil.newHashMap();
    }

    //测试泛型数组
    @Test
    public void generictyTest() {
//        HashMap<Integer> map=new HashMap<Integer>();
//        List<String> list=new ArrayList<String>();
        List list = new ArrayList<String>();
        list.add(1);
        System.out.println(list);
    }

    @Test
    public void hutoolStrUtil() {
        StringBuilder builder = StrUtil.builder();
        StringBuilder append = builder.append("hutool").append("StrUtil").append(".builder");
        System.out.println(append);
        mybtisController.memoryCost(100);
    }
    @Test
    public void test(){
        for (int i = 0; i < 5; i++) {
            System.out.println("RandomUtil.randomString(3) = " + RandomUtil.randomString(3));
        }
    }
    @Test
    public void test12(){
        User user = new User();
        User ds = user.setId(11L).
                setName("ds");
        System.out.println("ds = " + ds);
    }


}
