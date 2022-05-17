package com.atguigu.springcloud;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.atguigu.springcloud.annotation.MemoryCaculateLog;
import com.atguigu.springcloud.aspect.MemeryAspect;
import com.atguigu.springcloud.controller.MybtisController;
import com.atguigu.springcloud.dao.PopupsMapper;
import com.atguigu.springcloud.entities.User;
import com.atguigu.springcloud.vo.Popups;
import com.atguigu.springcloud.vo.PopupsVo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void test111() throws InterruptedException {
        String isExpireKey = "isExpireKey";
        String isExpireValue = "isExpireValue";
        if (redisTemplate.hasKey(isExpireKey)){
            redisTemplate.delete(isExpireKey);
        }
        redisTemplate.opsForValue().set(isExpireKey,isExpireValue);
        redisTemplate.expire(isExpireKey,10, TimeUnit.SECONDS);
//      // ttl指令对应的java方法
        System.out.println("redisTemplate.opsForValue().getOperations().getExpire(isExpireKey) = " + redisTemplate.opsForValue().getOperations().getExpire(isExpireKey));
        Thread.sleep(14000);
//        System.out.println("redisTemplate.opsForValue().getOperations().getExpire(isExpireKey) = " + redisTemplate.opsForValue().getOperations().getExpire(isExpireKey));
/**/
//
        //截取value
        String trimKey ="trimKey";
        String trimValue = "trimValue";
        if (redisTemplate.hasKey(trimKey)){
            redisTemplate.delete(trimKey);
        }
        redisTemplate.opsForValue().set(trimKey,trimValue,120,TimeUnit.SECONDS);
        String s = redisTemplate.opsForValue().get(trimKey, 0, 1);
        System.out.println("trimValue = " + s);
        Object o = redisTemplate.opsForValue().get(trimKey);
        System.out.println("trimValue = " + o);

        redisTemplate.setKeySerializer(RedisSerializer.json());
        redisTemplate.setValueSerializer(RedisSerializer.json());
        if (redisTemplate.hasKey(trimKey)){
            redisTemplate.delete(trimKey);
        }
        redisTemplate.opsForValue().set(trimKey,trimValue,120,TimeUnit.SECONDS);
        String s1 = redisTemplate.opsForValue().get(trimKey, 0, 1);
        System.out.println("trimValue = " + s1);
        Object o1 = redisTemplate.opsForValue().get(trimKey);
        System.out.println("trimValue = " + o1);

    }

    @Test
    public void test1111(){
    
    }

    private <T> void getdy(List<T> list){
        System.out.println("1111");
    }




    @Autowired
    PopupsMapper popupsMapper;

    @Test
    public void test333(){
//        File file=new File("D:\\软件\\弹幕\\黑色四叶草第18集-番剧-全集-高清正版在线观看-bilibili-哔哩哔哩 - 18.json");
        File file = new File("D:\\软件\\弹幕");
        File[] tempList = file.listFiles();
        for (File file1 : tempList) {
            if (file1.isFile()){
                System.out.println("正在处理文件：" + file1);
                String content= FileUtil.readString(file1,"UTF-8");
                String name1 = file1.getName();
                int i1 = name1.indexOf("第");
                int i2 = name1.indexOf("集");
                List<PopupsVo> popupsVo = JSONUtil.toList(JSONUtil.parseArray(content), PopupsVo.class);
                Long episode_num =  Long.valueOf(name1.substring(i1+1, i2));
                String name = "黑色四叶草";

                if (episode_num==18||episode_num==101){
                    continue;
                }
                List<Popups>  popups = convert2Popups(popupsVo,episode_num,name);

                int i = popupsMapper.insertBatch(popups);


            }
        }

    }

    private List<Popups> convert2Popups(List<PopupsVo> popupsVo, Long episode_num, String name) {
        return popupsVo.stream().map(source->{
            Popups popups = new Popups();
            popups.setId(RandomUtil.randomLong());
            popups.setContent(source.getContent());
            popups.setEpisode_num(Integer.valueOf(episode_num.toString()));
            popups.setName(name);
            popups.setCreate_time(DateUtil.now());
            popups.setUpdate_time(DateUtil.now());
            return popups;
        }).collect(Collectors.toList());

    }


}
