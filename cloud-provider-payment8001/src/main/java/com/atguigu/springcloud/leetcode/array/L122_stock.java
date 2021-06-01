package com.atguigu.springcloud.leetcode.array;

import cn.hutool.core.util.ArrayUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 买卖股票的最佳时机2
 * @author sunhcer.shi
 * @date 2021/06/01 08:22
 **/

public class L122_stock {
    public static void main(String[] args) {
        int i = maxProfit(new int[]{1, 2, 6, 89, 43, 211, 55, 77, 55, 22, 145});
        System.out.println(i);
    }

    public static int maxProfit(int[] prices) {
        if (ArrayUtil.isNotEmpty(prices)){
            int[] ints = new int[prices.length - 1];
            for (int i = 0; i <prices.length-1; i++) {
                ints[i]=prices[i+1]-prices[i];
            }
            //取得数组最大值
            int anInt = Arrays.stream(ints).max().getAsInt();
            List<Integer> collect = Arrays.stream(ints).boxed().collect(Collectors.toList());
            collect.forEach(System.out::println);
//            List ints1 = Arrays.asList(ints);
            int i = collect.indexOf(anInt);
            System.out.println(i);
            System.out.println("---------------------");
            return i+1;
        }
        return -1;
    }
}
