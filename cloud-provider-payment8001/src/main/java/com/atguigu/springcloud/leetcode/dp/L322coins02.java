package com.atguigu.springcloud.Leetcode.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 推广
 * @author sunhcer
 * @date 2021/04/07 19:11
 **/
public class L322coins02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入不同面值的硬币，逗号隔开：");
        String next = scanner.next();
        String[] coinStr = next.split(",");

        int[] coins = Arrays.asList(coinStr).stream().mapToInt(Integer::parseInt).toArray();

        System.out.print("请输入需要兑换的金额：");
        int amount = scanner.nextInt();
        coinChange(coins,amount);
    }

    public static int coinChange(int[] coins, int amount) {

        return 1;
    }
}
