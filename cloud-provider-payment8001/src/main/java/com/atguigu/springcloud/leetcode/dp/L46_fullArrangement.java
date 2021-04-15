package com.atguigu.springcloud.leetcode.dp;

import cn.hutool.core.collection.CollUtil;
import rx.internal.util.LinkedArrayList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * dfs 深度优先搜索 -- 全排列
 * 失败 没做出来
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


    public List<List<Integer>> permute(int[] nums) {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        //以其中一条路径展开

        LinkedArrayList linkedArrayList = new LinkedArrayList(nums.length);
        //int数组 转 list[Integer]
        List<Integer> ints = IntStream.of(nums)
                .boxed().collect(Collectors.toList());

        //递归
        recursion(nums,linkedArrayList);

        return null;
    }

    private void recursion(int[] nums, LinkedArrayList linkedArrayList) {

        for (int i = 0; i <nums.length; i++) {
            //取数数组 判断还有哪些路径可以走
            List<Integer> collect = IntStream.of(nums).boxed().collect(Collectors.toList());

//            if (){
//
//            }
//            Integer integer = collect.get(i);
//            if (linkedArrayList.indexInTail(collect.get(i))){
//
//            }
//            linkedArrayList.add(integer);

        }
    }

}
