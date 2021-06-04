package com.atguigu.springcloud;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.entities.PaymentSon;
import com.atguigu.springcloud.entities.User;
import com.atguigu.springcloud.vo.Company;
import com.atguigu.springcloud.vo.Department;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 阿达
 *
 * @author sunhcer.shi
 * @date 2021/03/16 18:52
 **/

public class TestNote {

    @Test
    public void test1() {
        String not1 = "0111";
        String not2 = "3111";
        System.out.println(not1.startsWith("0"));
        System.out.println(not1.substring(1));
        System.out.println(not2.startsWith("0"));
    }

    @Test
    public void regex() {
        String pattern = "^([\u4e00-\u9fa5]|[0-9_a-zA-Z]){0,150}$";
        boolean matches = Pattern.matches(pattern, null);
        System.out.println(matches);
    }

    @Test
    public void test2() {
        BigDecimal bigDecimal = new BigDecimal("0.00");
        BigDecimal bigDecimal1 = new BigDecimal("55");
        int i = bigDecimal.compareTo(bigDecimal1);
        System.out.println(i);

    }

//    @Test
//    public <T extends Payment> void get(ArrayList<?> list,T t){
////        list.add(t);
////        list.add(new Payment());
//    }

    @Test
    public void test3() {
        HashMap<String, Object> map = CollUtil.newHashMap();
        map.put("T_1", "01");
        map.put("T_0", "00");
        map.put("T_3", "333");
        ArrayList<Map.Entry<String, Object>> entries = new ArrayList<>(map.entrySet());
//        System.out.println("map.entrySet().toString() = " + map.entrySet().toString());
//        List<Map.Entry<String, Object>> collect = entries.stream().sorted().collect(Collectors.toList());

        Map<String, Object> map1 = new TreeMap<String, Object>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        // 降序排序
                        return obj1.compareTo(obj2);
                    }
                });
        map1.putAll(map);
        map1.forEach((key, value) -> System.out.println(key + "  " + value));
    }


    @Test
    public void testInt() {
        System.out.println("Integer.parseInt(\"01\") = " + Integer.parseInt("01"));
        System.out.println((BigDecimal) null);
        System.out.println("BigDecimal.ZERO.setScale(8) = " + BigDecimal.ZERO.setScale(4));
    }


    @Test
    public void testPoint() {
        Number number = NumberUtil.parseNumber("56.02%");
//        System.out.println("NumberUtil.parseNumber(\"56.02%\") = " + NumberUtil.parseNumber("56.02%"));
        BigDecimal bigDecimal = NumberUtil.toBigDecimal(NumberUtil.parseNumber("56.02%"))
                .divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);
        System.out.println(bigDecimal);

    }

    @Test
    public void testMap() {
        Map<BigDecimal, String> map = ImmutableMap.of(
                BigDecimal.ONE, "value1",
                new BigDecimal("2"), "v2",
                new BigDecimal("3"), "v3"
        );


        for (Map.Entry<BigDecimal, String> entry : map.entrySet()) {
            BigDecimal key = entry.getKey();
            String value = entry.getValue();
            if ("k2".equals(key)) {
                break;
            }
            System.out.println("value = " + value);
        }
    }

    @Test
    public void test() {
        List<String> list = Arrays.asList("1");
        List<String> list2 = null;
        List<String> union = ListUtils.union(list, CollUtil.defaultIfEmpty(list2, CollUtil.newArrayList()));

        System.out.println("list2 = " + list2);
    }

    @Test
    public void test10() {
        System.out.println("DateUtil.year(new Date()) = " + DateUtil.year(new Date()));
        int year = DateUtil.year(new Date());
        System.out.println("String.valueOf(year) = " + String.valueOf(year));
    }

    @Test
    public void testReflact() throws IllegalAccessException, InstantiationException {
        Class<User> userClass = User.class;
        Class<?>[] interfaces = User.class.getInterfaces();
        User user = User.class.newInstance();
        user.setEmail("ppppp");
        List<User> ncopies = ncopies(5, user);
    }

    private <T> List<T> ncopies(int n, T t) throws IllegalAccessException, InstantiationException {
        ArrayList<T> ts = new ArrayList<>();
        ts.add(t);
        for (int i = 0; i < n; i++) {
            Object o = t.getClass().newInstance();
            BeanUtil.copyProperties(t, o);
            ts.add((T) o);
        }
        return ts;
    }

    @Test
    public void testJoin(){
// 创建 IdGeneratorOptions 对象，请在构造函数中输入 WorkerId：
        IdGeneratorOptions options = new IdGeneratorOptions((short) 2);
// options.WorkerIdBitLength = 10; // WorkerIdBitLength 默认值6，支持的 WorkerId 最大值为2^6-1，若 WorkerId 超过64，可设置更大的 WorkerIdBitLength
// ...... 其它参数设置参考 IdGeneratorOptions 定义，一般来说，只要再设置 WorkerIdBitLength （决定 WorkerId 的最大值）。

// 保存参数（必须的操作，否则以上设置都不能生效）：
        YitIdHelper.setIdGenerator(options);
// 以上初始化过程只需全局一次，且必须在第2步之前设置。

        // 初始化以后，即可在任何需要生成ID的地方，调用以下方法：
        long newId = YitIdHelper.nextId();
        for (int i = 0; i < 10; i++) {
            System.out.println(YitIdHelper.nextId());
        }

        System.out.println("YitIdHelper.getIdGenInstance().newLong() = " + YitIdHelper.getIdGenInstance().newLong());
    }

    @Test
    public void test123(){
        ArrayList<Object> objects = CollUtil.newArrayList();
        boolean b = objects.addAll(new ArrayList<>());
        System.out.println(b);
    }

    @Test
    public void test114(){
        HashMap<String, String> map = CollUtil.newHashMap();
        map.put("1","joc");
        map.put("2","asda");
        for (Map.Entry<String, String> entry : map.entrySet()) {

        }
    }

    @Test
    public void test115(){
        // 左连接空数组不用判空
        Company company = new Company();
        company.setDepartments(new ArrayList<>());
        if (ObjectUtil.isNotEmpty(company)){
            for (Department department : company.getDepartments()) {
                System.out.println("111111111");
            }
        }
    }

    @Test
    public void test45(){
        Company company = new Company();
        company.setId("4555");
        System.out.println("copyAndReturn(company,new Department()) = " + copyAndReturn(company, new Department()));
    }

    private <T,V> V copyAndReturn(T source,V target){
        BeanUtil.copyProperties(source,target);
        return target;
    }

    @Test
    public void test333(){
        HashMap<String, Object> map = CollUtil.newHashMap();
        map.put("111",11);

        //如果不存在,新建一个 key,并且调用行数put值 并且返回该值,如果存在就直接返回value---> 不存在即更改为函数值
        Object o = map.computeIfAbsent("222", s -> 22);
        System.out.println("o = " + o);
        System.out.println("map.get(\"222\") = " + map.get("222"));

        //如果不存在,新建一个 key,并且调用行数put值 并且返回该值,如果存在就直接返回value---> 存在即更改为函数值
        Object o1 = map.computeIfPresent("111", (s, v) -> 4336);
        System.out.println("o1 = " + o1);

        map.compute("111", (k, v) -> 3543);

    }

    @Test
    public void test77(){

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.removeAll(null);
    }

    @Test
    public void test771(){
        int[] ints = {1, 2, 3, 3};
        int asInt = Arrays.stream(ints).max().orElse(0);
        System.out.println("asInt = " + asInt);
    }

    @Test
    public void test7(){
        String s1="3";

        String s2="9,8";

        System.out.println("s1.split(\",\") = " + Arrays.toString(s1.split(",")));

        System.out.println("s2.split(\",\") = " + Arrays.toString(s2.split(",")));

        System.out.printf("new Object[]{s2.split(\",\")} = %s%n", JSONUtil.toJsonStr(new Object[]{s2.split(",")}));
        System.out.println("(Object[])s2.split(\",\") = " +JSONUtil.toJsonStr((Object[]) s2.split(",")));
//        List<Object> objects = Arrays.asList(null);

        String join = StrUtil.join(",", s2.split(","));
        System.out.println("join = " + join);
        System.out.println("DateUtil.now() = " + DateUtil.now());
    }

}
