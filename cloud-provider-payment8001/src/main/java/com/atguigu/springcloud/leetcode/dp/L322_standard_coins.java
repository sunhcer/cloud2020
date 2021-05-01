package com.atguigu.springcloud.leetcode.dp;

/**
 * 记忆化搜索
 * @author sunhcer.shi
 * @date 2021/04/09 07:20
 **/

public class L322_standard_coins {

    public static void main(String[] args) {
        int[] coins =new int[]{1,2,5};
        System.out.println(coinChange(coins,11));
    }

    public static int coinChange(int[] coins,int amount){
        //临界条件
        if (amount<1){
            return 0;
        }
        //币值最小为1, 最大数组长度为amount
        return coinChange(coins, amount,new int[amount]);
    }

    /**
     * 递归方法
     * @param coins 面值数组
     * @param amount 和
     * @param count 记录数组
     * @return
     */
    private static int coinChange(int[] coins, int amount, int[] count) {

        //递归出口 1:无法组合返回0
        if (amount<0){
            return -1;
        }

        //递归出口 2:可组合的情况
        if (amount==0){
            return 0;
        }

        //循环条件 前一次的组合数大于0
        // TODO: 2021/4/9 不等于和大于 比如第一次进入循环是怎么判断 count[10]!=0的?
        //数组初始化的时候 count[amount] = count[0,0,...,0] 一直循环到 数组的第一个值有值,逆推回来 count[1,0,...,0]--->count[1,1,...,0]--->...--->count[1,1,...,3]
        if (count[amount-1]!=0){
            return count[amount-1];
        }

        //
        int min=Integer.MAX_VALUE;
        for(int coin:coins){
//            int res=coinChange(coins, amount-1, count);//递归式错误
            int res=coinChange(coins, amount-coin, count);
//            if (res>0&&res<min){ 合并条件错误
            if (res>=0&&res<min){
                min=1+res;
            }
        }
        count[amount-1]=(min==Integer.MAX_VALUE)?-1:min;
        return count[amount-1];
    }


}
