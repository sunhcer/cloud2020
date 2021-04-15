package com.atguigu.springcloud.leetcode.dp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 标准答案
 * 执行用时：
 * 20 ms, 在所有 Java 提交中击败5.51%的用户
 * 内存消耗：38.9 MB, 在所有 Java 提交中击败了17.19%
 * 的用户
 *
 * @author sunhcer.shi
 * @date 2021/04/15 07:37
 **/

public class L46_standard_fullArrangment {
    public static void main(String[] args) {

        /*计算某一段程序消耗的内存和时间*/
        Runtime r = Runtime.getRuntime();
        r.gc();//计算内存前先垃圾回收一次
        long start = System.currentTimeMillis();//开始Time
        long startMem = r.freeMemory(); // 开始Memory

        permute(new int[]{1, 2, 5, 7, 9});//被执行的程序

        long endMem =r.freeMemory(); // 末尾Memory
        long end = System.currentTimeMillis();//末尾Time
        //输出
        System.out.println("TimeCost: "+String.valueOf(end - start)+"ms");
        System.out.println("MemoryCost: "+String.valueOf((startMem- endMem)/1024)+"KB");


    }

    private static List<LinkedList<Integer>> res = new ArrayList<>();//错误二:声明没有初始化 报空指针

    public static List<List<Integer>> permute(int[] nums) {


        //初始化一个LinkedList (用来添加每一步的路径)
        LinkedList<Integer> path = new LinkedList<>();
        fullArrangment(nums, path);

        return null;
    }

    private static void fullArrangment(int[] nums, LinkedList<Integer> path) {
        //递归出口1
        if (path.size() == nums.length) {
            res.add(new LinkedList<>(path));
            System.out.println(path);
            return;
        }


        for (int i = 0; i < nums.length; i++) {
            //执行策略的条件
            if (path.contains(nums[i])) {
                //过滤条件
                continue;
            }
            //执行策略
            path.add(nums[i]);
            //下一轮循环
            fullArrangment(nums, path);// 错误一: 进入递归
            //执行回滚, 清空数组, 这里其实没必要清空, 只是为了套用这个框架
            path.removeLast();
        }


    }

}
