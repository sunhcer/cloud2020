package com.atguigu.springcloud;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.MyFunction;
import com.atguigu.springcloud.service.impl.MyFunction2;
import com.atguigu.springcloud.vo.Company;
import com.atguigu.springcloud.vo.Department;
import com.atguigu.springcloud.vo.Employee;
import com.google.inject.internal.cglib.core.$LocalVariablesSorter;
import io.swagger.models.auth.In;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.math.random.RandomGenerator;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流测试
 *
 * @author sunhcer.shi
 * @date 2021/04/01 11:20
 **/

public class StreamTest {

    /**
     * 两个集合 匹配替代
     */
    @Test
    public void matchAndReplace() {
        List<Map<String, Object>> hmList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("investProperties" , "02");
        map.put("hmItemName" , "华盟--投资重组类型2");

        Map<String, Object> map1 = new HashMap<>();
        map1.put("investProperties" , "03");
        map1.put("hmItemName" , "华盟--投资重组类型3");

        Map<String, Object> map5 = new HashMap<>();
        map5.put("investProperties" , null);
        map5.put("hmItemName" , "");

        hmList.add(map);
        hmList.add(map1);
        hmList.add(map5);

        List<Map<String, Object>> szList = new ArrayList<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();
        Map<String, Object> map4 = new HashMap<>();

        map2.put("itemNo" , "01");
        map3.put("itemNo" , "02");
        map4.put("itemNo" , "03");

        map2.put("itemName" , "深圳客户端--投资重组类型1");
        map3.put("itemName" , "深圳客户端--投资重组类型2");
        map4.put("itemName" , "深圳客户端--投资重组类型3");

        szList.add(map2);
        szList.add(map3);
        szList.add(map4);
        List<Map<String, Object>> result = szList.stream().map(szMap -> {
            hmList.stream().filter(matchCase -> Objects.equals(matchCase.get("investProperties"), szMap.get("itemNo")))
                    .forEach(hmMap -> hmMap.put("hmItemName" , szMap.get("itemName")));
            return szMap;
        }).collect(Collectors.toList());
        System.out.println(JSONUtil.toJsonStr(result));


        List<Map<String, Object>> result1 = hmList.stream().map(hmMap -> {
            szList.stream().filter(matchCase -> Objects.equals(matchCase.get("itemNo"), hmMap.get("investProperties")))
                    .forEach(szMap ->
                            hmMap.put("hmItemName" , szMap.get("itemName")));
            return hmMap;
        }).collect(Collectors.toList());
        System.out.println(JSONUtil.toJsonStr(result1));

        List<Payment> hmPaymentList = new ArrayList<>();

        Payment payment = new Payment();
        Payment payment1 = new Payment();

        payment.setId(1L);
//        payment.setItemNo("01");
        payment.setSerial("hm--payment1");

        payment1.setId(2L);
//        payment1.setItemNo("02");
        payment1.setSerial("hm--payment");

        hmPaymentList.add(payment);
        hmPaymentList.add(payment1);

//        List<Payment> payments=hmPaymentList.stream().map(hmMap->{
//            szList.stream().filter(matchCase->Objects.equals(matchCase.get("itemNo"),hmMap.getItemNo()))
//            .forEach(szMap-> hmMap.setSerial(szMap.get("itemName").toString()));
//            return hmMap;
//        }).collect(Collectors.toList());
//        System.out.println("实体匹配转换结果为:"+ JSONUtil.toJsonStr(payments));

        System.out.println("---------------------------------");
        int[] ints = new int[10];
        int i = 0;
//        for (int index:ints) {
//
//            System.out.println(index);
//            System.out.println(index==0);
//            index=1;
//            System.out.println(index==1);
//        }
        AtomicInteger indexAtomicInteger = new AtomicInteger(0);
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
        hmPaymentList.stream().forEach(targetMap ->
        {

            int i1 = atomicIntegerArray.get(indexAtomicInteger.intValue());
            ints[indexAtomicInteger.intValue()] = 1;
            Arrays.stream(ints).forEach(item -> System.out.print(" " + item));
            System.out.println();
            System.out.println(indexAtomicInteger.getAndIncrement());
        });
    }

    /**
     * 应用一:简化
     * 匿名内部类 (简化版的策略模式)
     */
    @Test
    public void test2() {
        /** 2021/05/01 11:58:33 源码注释
         * treeset放入的时候自动排排序
         */
        TreeSet<Integer> integers = new TreeSet<>();
        integers.addAll(Arrays.asList(1, 3, 2));
        System.out.println(integers);


        // 匿名内部类 以排序器为例,先重写排序器,然后传递排序器
        TreeSet<Integer> integersResort = new TreeSet<>(
                //重写排序顺序 ,从大到小
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return Integer.compare(o2, o1);
                    }
                }
        );
        integersResort.addAll(Arrays.asList(1, 3, 2));
        integersResort.stream().forEach(System.out::println);
    }

    /**
     * lambda 简化匿名内部类
     */
    @Test
    public void simplify() {
        TreeSet<Integer> lambdaIntegers = new TreeSet<>(
                // 形式上是实现方法 ,本质上是实现了具有该抽象方法的接口的匿名内部类 (觉得该方法的实现必须要简洁)
                (x, y) -> Integer.compare(y, x)
        );
        lambdaIntegers.addAll(Arrays.asList(1, 3, 2));
        lambdaIntegers.stream().forEach(System.out::println);
    }

    /**
     * 应用二: 过滤
     */
    @Test
    public void testfilter() {
        //实体字段: id serial amount
        Payment payment1 = new Payment(1L, "序列号1" , 78L);
        Payment payment2 = new Payment(15L, "序列号2" , 69L);
        Payment payment3 = new Payment(2L, "序列号3" , 50L);
        Payment payment4 = new Payment(5L, "序列号4" , 14L);
        Payment payment5 = new Payment(12L, "序列号5" , 98L);
        List<Payment> payments = Arrays.asList(payment1, payment2, payment3, payment4, payment5);
        // 1. 过滤出amount为50以上的订单 ,任取3个
        payments.stream()
                .filter(source -> source.getAmount() > 50L)
                .limit(3)
                .forEach(System.out::println);


        System.out.println("取最大/小");
        Payment payment6 = payments
                .stream()
                .max(Comparator.comparing(Payment::getAmount)).get();
        System.out.println(payment6.toString());
        System.out.println("取最大/小");

        System.out.println("订单数量大于50的有几个");

        long count = payments.stream()
                .filter(source -> source.getAmount() > 50L)
                .count();
        System.out.println(count);
        System.out.println("订单数量大于50的有几个");


        System.out.println("找到订单中第一个订单数量大于50的,更新其属性为幸运订单");

        Optional<Payment> fun = payments.stream()
                .map(source -> {
                    if (source.getAmount() > 50L) {
                        source.setSerial("幸运订单");
                    }
                    return source;
                })
                .findFirst();

        System.out.println(fun.get().toString());
        System.out.println("找到订单中第一个订单数量大于50的,更新其属性为幸运订单");

        // 2. list按照amount从大到小 排序, 并且取出前三的 serial字段 ,放到map里面 ,然后打印出来
        // 常规做法
        Collections
                .sort(payments, (x, y) -> Long.compare(y.getAmount(), x.getAmount()));

        System.out.println("==================");
        // 流式
        List<Payment> payments2 = Arrays.asList(payment1, payment2, payment3, payment4, payment5);
        payments2.stream()
                //正向排序写法
//                .sorted(Comparator.comparing(payment -> payment.getAmount()))
                //反向排序写法
                .sorted(Comparator.comparing(Payment::getAmount).reversed())
                .limit(3)
                .map(payment -> payment.getSerial())
                .forEach(System.out::println);

        System.out.println("测试============开始 ");
        Stream<Long> longStream = payments2.stream()
                .map(Payment::getAmount); //Payment::getAmount ----->> 实现了一个入参类型为 Payment,返回值类型为Object转Long的 function型函数式接口

         payments2.stream()
                .map(source->source.getAmount()) //和上面等价 ----->> 本质上也是 入参类型为 Payment,返回值类型为Object转Long的 function型函数式接口
        .forEach(System.out::println);

        System.out.println("--------------");

        payments2.stream()
                .flatMap(source->Stream.of(source.getAmount()))
                .forEach(System.out::println);

        System.out.println("测试============结束 ");

        // map + Collect 灵活之处在 可以控制他返回的类型 , 因为如果直接用 foreach, foreach和collect都是 终止操作 且foreach无返回值,无法搭配 , 所以 如果需要批量的转换实体,然后收集 , 是需要声明额外的list的,
        List<Company> collect = payments2.stream()
                .map(source -> {
                    Company company = new Company();
                    company.setId(source.getSerial());
                    return company;
                }).collect(Collectors.toList());

        collect.stream().forEach(System.out::println);

        Company company = new Company();
        company.setId("小黑屋公司");

        Department department = new Department();
        department.setId("it部门");

        Employee employee = new Employee();
        employee.setId("sunhcer");

        Employee employee1 = new Employee();
        employee1.setId("weck");

        department.setEmployees(Arrays.asList(employee,employee1));

        Department department1 = new Department();
        department1.setId("qa部门");

        company.setDepartments(Arrays.asList(department,department1));

        Company company1 = new Company();
        company1.setId("大黑屋公司");
        BeanUtils.copyProperties(company,new Company());

        List<Company> companies = Arrays.asList(company, company1);

//        companies.stream()
//                .map(company2->company2.getDepartments())
//                .map(departments1 -> departments1.get());// map的话到这一级就已经是 list 而不是 department对象

        System.out.println("测试flatMap------------------");
        companies.stream()
                .flatMap(company4->company4.getDepartments().stream())
                .flatMap(d -> d.getEmployees().stream())
                .map(e-> e.getId())
                .forEach(System.out::println);
        System.out.println("测试flatMap------------------");
        // 3.

    }


    /**
     * 应用3 语法格式
     * 参数列表的类型可以不写,jvm可以根据上下文推断
     * lambda表达式需要函数式接口的支持,如果接口中只有一个抽象方法 ,就称为函数式接口----->>可以用@FuctionalInterface修饰
     */
    @Test
    public void testGrammar() {
        //无参
        Runnable r1 = () -> System.out.println("无参方法");
        r1.run();

        //一个参数 有返回值(如果只有一条语句,return可以不写)
//        List<String> list=x-> System.out.println("有一个参数,有返回");

    }

    // 用户处理字符串
    public String strHandler(String str, MyFunction myFunction) {
        return myFunction.getValue(str);
    }

    @Test
    public void test3() {
        String str = " =====uugv===     ===  ";
        // 有参数 用线程调 不用声明函数式接口----->>不行,无法推断出来类型----->>所以需要一个handler方法
//        Runnable r1= (str1)->str.trim();
        String convert = strHandler(str, (x) -> x.toUpperCase());
        System.out.println(convert);
    }


    //定义两个泛型,传参T ,返回R ----->>计算两个Long的结果
    public Object longHandler(Long l1, Long l2, MyFunction2<Long, Object> myFunction2) {
        return myFunction2.getValue(l1, l2);
    }

    @Test
    public void test4() {
        System.out.println(longHandler(9L, 868L, (x, y) -> x + y));
        // 为什么这里判定不出来是object类型?
        System.out.println(longHandler(9L,868L,(x,y)->x+""+y));
    }

    //这里自己写函数式接口还是比较麻烦

    //    		i. Consumer<T> :消费型接口 ----->> 没有返回值
    @Test
    public void test5() {
        consunmerHandler("五一" , x -> System.out.println(x + " 快乐! "));
    }

    public void consunmerHandler(String str, Consumer<String> con) {
        con.accept(str);
    }
    //			ii. Supplier<T> : 供给型接口
    @Test
    public void test6(){
        System.out.println(functionHandler("1957  ==   ",x->x.trim()));
    }
//			iii. Function<T,R> 函数型接口----->>T 为入参类型, R返回类型
    public String functionHandler(String str, Function<String,String> function){
        return function.apply(str);
    }

//          Predicate<T> 断言型接口 ----->>自定义过滤条件
    public List predicateHandler(){
        return null;
    }

    //			ii. 三种语法格式:
    //				1) 对象::实例方法名
    @Test
    public void test(){
        Consumer<String> consumer=x-> System.out.println(x);
        consumer.accept("456");
        Payment payment = new Payment();
        payment.setSerial("pppp");
        Supplier<String> supplier= payment::getSerial;
        System.out.println(supplier.get());

    }
    //				2) 类::静态方法名
    //类::实例方法名

    //构造器引用
    Supplier<Employee> sup2=Employee::new;

    //数组引用


    @Test
    public void testFindAnyAndFind(){
        List<String> list = Arrays.asList("sunhcer" , "weck" , "nick" , "time");

        String findAny = list.stream()
                .filter(source -> source.contains("n"))
                .findAny().get();

        String findFirst = list.stream()
                .filter(source -> source.contains("n"))
                .findFirst().get();

        System.out.println("单线程------fiadAny:"+findAny+" === findFirst:"+findFirst);

        String parallelfindAny = list.parallelStream()
                .filter(source -> source.contains("n"))
                .findAny().get();

        String parallelfindFirst = list.parallelStream()
                .filter(source -> source.contains("n"))
                .findFirst().get();

        System.out.println("多线程------findAny:"+parallelfindAny+" === findFirst:"+parallelfindFirst);


    }

    @Test
    public void testMatch(){
        List<String> list = Arrays.asList("sunhcer" , "weckn" , "nick" , "timen");

        boolean n = list.stream()
                .anyMatch(source -> source.contains("n"));
//        System.out.println(n);


        boolean q = list.stream()
                .allMatch(source -> source.contains("n"));
//        System.out.println(q);

        boolean r = list.stream()
                .noneMatch(source -> source.contains("z"));
        System.out.println(r);
    }


    @Test
    public void testReduce(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 8);

        Integer reduce = integers.stream()
//                .reduce(0, (x, y) -> x + y);
                //或者使用方法引用
                .reduce(0, Integer::sum);
        System.out.println(reduce);
    }

    @Test
    public void testMapReduce(){
        //实体字段: id serial amount
        Payment payment1 = new Payment(1L, "序列号1" , 10L);
        Payment payment2 = new Payment(15L, "序列号2" , 10L);
        Payment payment3 = new Payment(2L, "序列号3" , 10L);
        Payment payment4 = new Payment(5L, "序列号4" , 10L);
        Payment payment5 = new Payment(12L, "序列号5" , 10L);
        List<Payment> payments = Arrays.asList(payment1, payment2, payment3, payment4, payment5);

        Long reduce = payments.stream()
                .map(Payment::getAmount)
                .reduce(0L, Long::sum);
        System.out.println(reduce);
    }

    @Test
    public  void testBatchMapReduce(){

        List<Payment> payments=new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            Payment payment = new Payment(1L, "序列号1" , RandomUtil.randomLong(1L,10L));
            payments.add(payment);
        }

        Long reduce = payments.stream()
                .map(Payment::getAmount)
                .reduce(0L, Long::sum);

        Long reduceParallel = payments
                .parallelStream()
                .map(Payment::getAmount)
                .reduce(0L, Long::sum);

        System.out.println("单线程计算结果:"+reduce+" 并行流计算结果:"+reduceParallel);
    }

    @Test
    public void testIterator(){
        Payment payment1 = new Payment(1L, "序列号1" , 10L);
        Payment payment2 = new Payment(15L, "序列号2" , 10L);
        Payment payment3 = new Payment(2L, "序列号3" , 10L);
        Payment payment4 = new Payment(5L, "序列号4" , 10L);
        Payment payment5 = new Payment(12L, "序列号5" , 10L);
        List<Payment> payments = Arrays.asList(payment1, payment2, payment3, payment4, payment5);

        payments.stream()
                .filter(source->source.getId().equals(1L))
                .iterator().remove();

        System.out.println(payments.toString());
    }

    @Test
    public void parallelForeach(){
        Stream.of("a","b","c").parallel().forEach(System.out::println);
        System.out.println("-----------");
        Stream.of("a","b","c").parallel().forEachOrdered(System.out::println);
    }

    @Test
    public void arrayTest(){
        Object[] objects = Stream.of("a" , "b" , "c").toArray();
//        Arrays.stream(objects).forEach(System.out::println);

        String[] strings = Stream.of("a" , "b" , "c").toArray(String[]::new);
        Arrays.stream(strings).forEach(System.out::println);
    }

}

