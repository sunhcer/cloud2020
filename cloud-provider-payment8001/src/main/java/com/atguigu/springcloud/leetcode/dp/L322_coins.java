package com.atguigu.springcloud.leetcode.dp;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * 硬币兑换
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * @author sunhcer.shi
 * @date 2021/04/07 09:35
 **/
@Slf4j
public class L322_coins {

    public static void main(String[] args) {
        //a1={[1]} ---> 1  ---> map { a1=[1] }
        //a2=min{[2][1,1]}---> min{[a1+a1],[a2]} --->1   ---> map { a1=[1] , a2=[2] }
        //a3 = min{[1,1,1],[1,2]} ---> min{ [a1+a1+a1],[a1+a2],[a3] } ---> 2 ---> map { a1=[1] , a2=[2] , a3=[1,2] }
        //a4 = min {[1,1,1,1],[1,1,2],[2,2]} ---> 2 ---> map { a1=[1] , a2=[2] , a3=[1,2] , a4=[2,2] }
        //a5 = min {[1,1,1,1,1],[1,1,1,2],[2,2,1]}

        //1.没有的币值默认为正无穷
        //2. 是否需要每一项都列举 所有可能的组合 , 如果是这个思路 就要先求n的加法组合(时间复杂度和空间复杂度都比较高)--->从低至上
        //3. 换一种思路 减少 穷举加法的次数  , 对于一个可以拼凑出来的币值(大于5时) 他最多有三种状态 min {a[n-1]+a1,a[n-2]+a2,a[n-5]+a5}
             //也就是说 , 除去最后一次拼的币值 , 他的组成次数 依赖于前一个状态的次数 min{ value[n-1] ,value[n-2], value[n-5] }

        int coin1=1;
        int coin2=2;
        int coin3=5;
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("输入:");
            int amountTarget=scanner.nextInt();
//        Integer[] valueArray = new Integer[amountTarget];
            //1: 从底而上 穷举(复杂度太高)
            // 开始穷举
//        for (int i = 0; i < amount; i++) {
//            if (i==0){
//                valueArray[i]=Integer.MAX_VALUE;
//            }
//            if (i!=1||i!=2||i!=5){
//                //没有币值的默认正无穷
//                valueArray[i]=Integer.MAX_VALUE;
//            }
//            //穷举他的币值组合
//            valueArray[i]=min{};
//        }

            //2: 从上而下穷举
            int[] amount = new int[100];
            Integer[] time= new  Integer[100];
            amount[0]=Integer.MAX_VALUE;
            amount[1]=1;
            amount[2]=1;
            amount[3]=2;
            amount[4]=2;
            amount[5]=1;
            if (amountTarget<6){
                if (amount[amountTarget]!=Integer.MAX_VALUE){
                    System.out.println("组合数:"+amount[amountTarget]);
                }else{
                    System.out.println("组合数: -1");
                }
                return;
            }else{
                //递归
                recursion(amount,amountTarget);

                System.out.println("组合数:"+amount[amountTarget]);
            }
        }


    }

    private static void recursion(int[] amount, int amountTarget) {
        if (amountTarget<6){
//            log.info("定位");
            return;
        }


        recursion(amount,amountTarget-1);

        recursion(amount,amountTarget-2);

        recursion(amount,amountTarget-5);

        amount[amountTarget]=Math.min(Math.min(amount[amountTarget-1]+amount[1],amount[amountTarget-2]+amount[2]),amount[amountTarget-5]+amount[5]);

    }
}
