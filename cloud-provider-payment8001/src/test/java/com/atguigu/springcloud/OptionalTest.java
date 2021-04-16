package com.atguigu.springcloud;

import cn.hutool.json.JSONUtil;
import com.atguigu.springcloud.entities.NotNullGrandSonVo;
import com.atguigu.springcloud.entities.NotNullSonVo;
import com.atguigu.springcloud.entities.NotNullVo;
import com.atguigu.springcloud.entities.Payment;
import org.junit.Test;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Optional工具
 * @author sunhcer.shi
 * @date 2021/04/13 10:54
 **/

public class OptionalTest {

    /**
     * 引入
     */
    @Test
    public void introduce(){
        //1. 在浅层判null时, optional和三目的思想一致,但是三目因为有自动拆箱和装箱的特性, 会导致空指针
        Payment payment = new Payment();
//        Long id= payment==null?0L:payment.getId();//空指针异常 payment.getId()=null ---> 然后拿null 向下转型成 long(0L为long的默认值) 触发空指针
        // 换用过Optional来判断和代替
        Long id= Optional.ofNullable(payment.getId()).orElse(0L);
        System.out.println(id);
        System.out.println(Optional.ofNullable(payment));
        System.out.println(Optional.ofNullable(payment).get());
        System.out.println("3-- "+Optional.ofNullable(payment).filter(payment1 -> payment1.getId()!=null)
        .isPresent());

        Optional.ofNullable(payment).filter(payment1 -> payment1.getId()==null)
                .ifPresent(payment1 -> {
                    payment1.setId(45L);
                    System.out.println("4-- "+JSONUtil.toJsonStr(payment));//JSONUtil 会比payment
                    System.out.println("5-- "+payment.toString());
                });
//        System.out.println(Optional.ofNullable(payment.getId()));
//        System.out.println(Optional.of(payment));
//        System.out.println(Optional.of(payment.getId()));

        //2: map 深层调用,不用每一级的判断null
        NotNullVo notNullVo = new NotNullVo();
        NotNullSonVo notNullSonVo = new NotNullSonVo();
        NotNullGrandSonVo notNullGrandSonVo = new NotNullGrandSonVo();
        notNullVo.setNotNullSonVo(notNullSonVo);
        notNullSonVo.setNotNullGrandSonVo(notNullGrandSonVo);

        //在Optional里面连续get还是会发生空指针,但是每一级都用 map的话
        String s = Optional.ofNullable(notNullVo)
                .map(vo -> vo.getNotNullSonVo())
                .map(sonVo -> sonVo.getNotNullGrandSonVo())
                .map(grandSon -> grandSon.getName())
                .map(name->name.toUpperCase())
                .orElse(null);
        System.out.println(s);
    }

    @Test
    public void testlinkedList(){
        LinkedList<Integer> list=new LinkedList<>();
        int [] ints={1,2,5};
        //流将int数组转换成 list[int]
        List<Integer> collect = IntStream.of(ints).boxed().collect(Collectors.toList());
        System.out.println(collect);
        //
        //构造方法 将list[int] 转换成 linkedlist[integer]
        LinkedList<Integer> list1 = new LinkedList<>(collect);
        System.out.println(list1);

        Integer integer = list1.removeLast();
        System.out.println(integer);
        System.out.println(list1);

        LinkedList<Object> objects = new LinkedList<>();
        System.out.println(objects.size());
    }

    @Test
    public void swap(){
        List<Integer> collect = IntStream.of(new int[]{1, 2, 5}).boxed().collect(Collectors.toList());
        System.out.println(collect);
        Collections.swap(collect,0,2);
        System.out.println(collect);

    }

}
