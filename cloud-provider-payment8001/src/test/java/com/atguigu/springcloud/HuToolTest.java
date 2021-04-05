package com.atguigu.springcloud;


import cn.hutool.core.collection.CollUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuToolTest {
    @Test
    public void CollUtilTest(){
        String[] strs = new String[] {"a","b","c","d"};
        //string数组转list
        ArrayList<String> list = CollUtil.newArrayList(strs);
        System.out.println(list);

        //join 拼接list为 str
        String join = CollUtil.join(list, "#");
        String join1 = CollUtil.join(list, "");
        System.out.println("join:"+join +" join1: "+join1);

        // newArrayList 新建arraylist并 填充元素
        ArrayList<Object> objects = CollUtil.newArrayList();
        List<String> listStr = CollUtil.newArrayList();
        System.out.println();

        //sortPageAll 多个集合放进一个列表中，根据指定的顺序排序，可以选择其指定页数（指定页面条数）的结果
        List<Integer> list1 = CollUtil.newArrayList(1, 45, 5);
        List<Integer> list2 = CollUtil.newArrayList(9, 2, 3);
        List<Integer> list3 = CollUtil.newArrayList(8, 6);
        List<Integer> listSort=CollUtil.sortPageAll(0,5,
        (Integer o1,Integer o2)->{
            return o1.compareTo(o2);
        },list1,list2,list3);

        System.out.println("hutool的多list排序分页结果:"+listSort);

        //newHashMap
        Map map = CollUtil.newHashMap();
    }


    //测试泛型数组
    @Test
    public void generictyTest(){
//        HashMap<Integer> map=new HashMap<Integer>();
//        List<String> list=new ArrayList<String>();
        List list=new ArrayList<String>();
        list.add(1);
        System.out.println(list);
    }

}
