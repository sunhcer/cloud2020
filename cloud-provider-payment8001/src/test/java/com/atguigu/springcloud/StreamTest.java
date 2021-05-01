package com.atguigu.springcloud;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.atguigu.springcloud.entities.Payment;
import com.google.inject.internal.cglib.core.$LocalVariablesSorter;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.Collectors;

/**
 * 流测试
 * @author sunhcer.shi
 * @date 2021/04/01 11:20
 **/

public class StreamTest {

    /**
     * 两个集合 匹配替代
     */
    @Test
    public void matchAndReplace(){
        List<Map<String,Object>> hmList=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("investProperties","02");
        map.put("hmItemName","华盟--投资重组类型2");

        Map<String,Object> map1=new HashMap<>();
        map1.put("investProperties","03");
        map1.put("hmItemName","华盟--投资重组类型3");

        Map<String,Object> map5=new HashMap<>();
        map5.put("investProperties",null);
        map5.put("hmItemName","");

        hmList.add(map);
        hmList.add(map1);
        hmList.add(map5);

        List<Map<String,Object>> szList=new ArrayList<>();
        Map<String,Object> map2=new HashMap<>();
        Map<String,Object> map3=new HashMap<>();
        Map<String,Object> map4=new HashMap<>();

        map2.put("itemNo","01");
        map3.put("itemNo","02");
        map4.put("itemNo","03");

        map2.put("itemName","深圳客户端--投资重组类型1");
        map3.put("itemName","深圳客户端--投资重组类型2");
        map4.put("itemName","深圳客户端--投资重组类型3");

        szList.add(map2);
        szList.add(map3);
        szList.add(map4);
        List<Map<String,Object>> result= szList.stream().map(szMap->{
            hmList.stream().filter(matchCase->Objects.equals(matchCase.get("investProperties"),szMap.get("itemNo")))
                    .forEach(hmMap->hmMap.put("hmItemName",szMap.get("itemName")));
            return szMap;
        }).collect(Collectors.toList());
        System.out.println(JSONUtil.toJsonStr(result));


        List<Map<String,Object>> result1= hmList.stream().map(hmMap->{
            szList.stream().filter(matchCase->Objects.equals(matchCase.get("itemNo"),hmMap.get("investProperties")))
                    .forEach(szMap->
                            hmMap.put("hmItemName",szMap.get("itemName")));
            return hmMap;
        }).collect(Collectors.toList());
        System.out.println(JSONUtil.toJsonStr(result1));

        List<Payment> hmPaymentList=new ArrayList<>();

        Payment payment = new Payment();
        Payment payment1 = new Payment();

        payment.setId(1L);
        payment.setItemNo("01");
        payment.setSerial("hm--payment1");

        payment1.setId(2L);
        payment1.setItemNo("02");
        payment1.setSerial("hm--payment");

        hmPaymentList.add(payment);
        hmPaymentList.add(payment1);

        List<Payment> payments=hmPaymentList.stream().map(hmMap->{
            szList.stream().filter(matchCase->Objects.equals(matchCase.get("itemNo"),hmMap.getItemNo()))
            .forEach(szMap-> hmMap.setSerial(szMap.get("itemName").toString()));
            return hmMap;
        }).collect(Collectors.toList());
        System.out.println("实体匹配转换结果为:"+ JSONUtil.toJsonStr(payments));

        System.out.println("---------------------------------");
        int[] ints = new int[10];
        int i=0;
//        for (int index:ints) {
//
//            System.out.println(index);
//            System.out.println(index==0);
//            index=1;
//            System.out.println(index==1);
//        }
        AtomicInteger indexAtomicInteger=new AtomicInteger(0);
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
        hmPaymentList.stream().forEach(targetMap->
        {

            int i1 = atomicIntegerArray.get(indexAtomicInteger.intValue());
            ints[indexAtomicInteger.intValue()]=1;
            Arrays.stream(ints).forEach(item-> System.out.print(" "+item));
            System.out.println();
            System.out.println(indexAtomicInteger.getAndIncrement());
        });


    }
}
