package com.atguigu.springcloud.leetcode.dfs;

import cn.hutool.core.collection.CollUtil;
import rx.internal.util.LinkedArrayList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * dfs 深度优先搜索 -- 全排列
 * 失败 没做出来
 *
 * @author sunhcer.shi
 * @date 2021/04/13 08:32
 **/

public class L46_fullArrangement {
    //给定一个 没有重复 数字的序列，返回其所有可能的全排列。
    //输入: [1,2,3]
    //输出:
    //[
    //  [1,2,3],
    //  [1,3,2],
    //  [2,1,3],
    //  [2,3,1],
    //  [3,1,2],
    //  [3,2,1]


    //框架: 核心就是 for 循环里面的递归，在递归调用之前「做选择」，在递归调用之后「撤销选择」
    //result = []
    //def backtrack(路径, 选择列表):
    //    if 满足结束条件:
    //        result.add(路径)
    //        return
    //
    //    for 选择 in 选择列表:
    //        做选择
    //        backtrack(路径, 选择列表)
    //        撤销选择

    //记录所有循环的数组
    private List<ArrayList<Integer>> res= new ArrayList<>();

    public List<ArrayList<Integer>> permute(int[] nums) {
        //记录一次循环的数组
        ArrayList<Integer> path = new ArrayList<>();
//        nums={2,4,6};//这里报错的原因 是 数组的静态初始化只能声明的同时赋值 , 不能两个拆开来写,
//        nums = new int[]{2, 4, 6, 9};//数组几天来初始化的额一般写法 是可以分拆开来的
        //递归
        recursion(nums, path);
//        System.out.println(res);
        return res;
    }

    private void recursion(int[] nums, ArrayList<Integer> path) {
        //递归出口
        //错误二 : 递归出口放在for里面 出不去
        if (nums.length == path.size()) {
            Object clone = path.clone();
            res.add((ArrayList<Integer>)path.clone());
            System.out.println("路径: " + path);
        }

        for (int i = 0; i < nums.length; i++) {
            //进入递归的条件
            if (path.contains(nums[i])) {
                continue;
            }

            //记录路径
            path.add(nums[i]);
            //进入下一次循环
            recursion(nums, path);
            // 递归回归的时候清空路径
            path.remove(path.size() - 1);
        }
    }
}
