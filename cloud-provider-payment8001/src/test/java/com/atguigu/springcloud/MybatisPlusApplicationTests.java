package com.atguigu.springcloud;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.atguigu.springcloud.dao.UserMapper;
import com.atguigu.springcloud.entities.User;
import com.atguigu.springcloud.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.ImmutableMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusApplicationTests {
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

    // 继承了BaseMapper，所有的方法都来自己父类
// 我们也可以编写自己的扩展方法！
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    /**
     * mapper层方法测试
     */
    @Test
    public void testMapper() {
// 参数是一个 Wrapper ，条件构造器，这里我们先不用 null
// 查询全部用户
        User user = userMapper.selectById(1);
        System.out.println("user = " + user);
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

        User byId = userService.getById(1);
        System.out.println("byId = " + byId);
    }


    @Test
    public void testService() {
        QueryWrapper<UserService> userWrapper = new QueryWrapper<>();
        // .lambda相当于启用了lambda链式编程
        LambdaQueryWrapper<UserService> lambda = userWrapper.lambda();
        LambdaQueryWrapper<User> userLambda = new LambdaQueryWrapper<>();
//        lambda.
        User user = new User();
        user.setId(6L);
        user.setName("sunhcer");
        user.setAge(26);
        user.setEmail("11070500");


//        System.out.println("userService.save(user) = " + userService.save(user));
        user.setName("小刚");
        // saveOrUpdate 自动update
//        System.out.println("userService.saveOrUpdate(user) = " + userService.saveOrUpdate(user));
        user.setId(7L);
        // saveOrUpdate 自动save
        System.out.println("userService.saveOrUpdate(user) = " + userService.saveOrUpdate(user));

        List<User> users = Collections.nCopies(5, user);

    }

    @Test
    public void testinserttime() {
//        System.out.println("userService.list(null) = " + userService.list(null));
//        User user = userMapper.selectById(1);
        User user = new User();
        user.setId(14L);
        user.setName("matt");
        user.setAge(30);
        //没有加时间处理器 报错: Type handler was null on parameter mapping for property 'createTime'.
        //添加 元数据处理配置类
        userMapper.insert(user);
    }

    @Test
    public void testUpdateTime() {
        User user = userMapper.selectById(13);
        userMapper.updateById(user);
    }

    @Test
    public void testLogicDelete() {
        User user = userMapper.selectById(14);
        System.out.println("userMapper.selectById(13) = " + user);
        System.out.println("userService.getById(13) = " + userService.getById(14));
//        System.out.println("userService.list(null) = " + userService.list(null));
//        System.out.println("userMapper.deleteById(user.getId()) = " + userMapper.deleteById(user.getId()));

        userService.removeById(14);
        System.out.println("userService.getById(13) = " + userService.getById(14));
    }

    @Test
    //基础
    public void testBase() {
        //alleq 不能结合lambda语法,但是可以结合map来使用,其实也很鸡肋,因为要写睡醒字段
//        userService.list(new QueryWrapper<User>().lambda()
//        .allEq(User::getName,"Jone",User::getAge,18))
        Map<String, Object> of = ImmutableMap.of(
                "name", "Jone",
                "age", 18
        );
        HashMap<String, Object> map = CollUtil.newHashMap(2);
        map.put("name", "Jone");
        map.put("age", 18);
        //alleq 使用lambda语法的时候会报错
//        List<User> list = userService.list(new QueryWrapper<User>()
//                .allEq(map));
        List<User> list = userService.list(new QueryWrapper<User>()
                .allEq(of));
        System.out.println("list = " + list);

    }

    @Test
    public void test() {
        // 用不等于会忽略null的情况,如果要找为null的需要加另外的条件
//        userService.list(new QueryWrapper<User>().lambda()
//                .ne(User::getVersion, "3366")).stream().forEach(System.out::println);

        userService.list(new QueryWrapper<User>().lambda()
                .ne(User::getVersion, "3366")
                .or().isNull(User::getVersion))
                .stream()
                .forEach(System.out::println);
    }


}