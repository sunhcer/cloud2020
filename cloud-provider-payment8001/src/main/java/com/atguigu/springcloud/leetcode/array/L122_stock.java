package com.atguigu.springcloud.leetcode.array;

import cn.hutool.core.util.ArrayUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 买卖股票的最佳时机2
 * 采取的策略是: 所有会赚的天数都参与交易,然后汇总求和
 * @author sunhcer.shi
 * @date 2021/06/01 08:22
 **/

public class L122_stock {
    public static void main(String[] args) {
//        int i = maxProfit(new int[]{1, 2, 6, 89, 43, 211, 55, 77, 55, 22, 145});
        int i = maxProfit(new int[]{7,1,5,3,6,4});
        System.out.println(i);
    }

    public static int maxProfit(int[] prices) {
//        if (ArrayUtil.isNotEmpty(prices)){
        if (prices!=null&&prices.length>0){
            int[] ints = new int[prices.length - 1];
            for (int i = 0; i <prices.length-1; i++) {
                ints[i]=prices[i+1]-prices[i];
            }
            //取得数组最大值
//            int anInt = Arrays.stream(ints).max().getAsInt();
            //应该用orelse代替get
            int anInt = Arrays.stream(ints).max().orElse(0);
            List<Integer> collect = Arrays.stream(ints).boxed().collect(Collectors.toList());
            collect.forEach(System.out::println);
//            List ints1 = Arrays.asList(ints);
            int i = collect.indexOf(anInt);
            System.out.println(i);
            System.out.println("---最大利润天数为:"+(i+1));
            //利润求和
            int sum = collect.stream().filter(source -> source > 0).mapToInt(source -> source).sum();
            System.out.println("sum = " + sum);
            return sum;
        }
        return -1;
    }
}
