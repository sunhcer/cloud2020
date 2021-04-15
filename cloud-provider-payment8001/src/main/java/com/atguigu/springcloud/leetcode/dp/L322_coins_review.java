package com.atguigu.springcloud.leetcode.dp;

/**
 * 扩展原思路到n
 * bfs ---> braedth first search ---> 广度优先遍历 ---> 尝试所以有的路径 ,取得最优解
 * dfs --->depth first search --->深度优先遍历 --->试错找到一条可行的路即可---> 回溯(错误)算法
 * https://zhuanlan.zhihu.com/p/24986203
 * 运行结果
 * 执行用时: 43 ms
 * 内存消耗: 38.5 MB
 * 9% 全排列
 * @author sunhcer.shi
 * @date 2021/04/12 21:12
 **/

public class L322_coins_review {

    public static void main(String[] args) {
        int[] coins=new int[]{2};
        int targetAmount=3;
        //记忆
        int[] memoryArray=new int[targetAmount];

        //最小次数
        int minTimes=coinsExchange(coins,targetAmount,memoryArray);
        System.out.println("最小组合数为:"+minTimes);

    }

    private static int coinsExchange(int[] coins, int targetAmount, int[] memoryArray) {
        //指针比实际值小1
        //递归出口1:
        //错误1: 所需要的凑成的硬币数为0的时候,需要0枚硬币
        if (targetAmount==0){
            return 0;
        }

//        if (targetAmount==0){
//            return Integer.MAX_VALUE;
//        }

        //递归出口2:
        //所需要凑成的硬币数为负数的时候,返回负数
        if (targetAmount<0){
            return -1;
        }

        //递归出口3: targetAmount-1为 当前数组下标对应
        if (memoryArray[targetAmount-1]!=0){
            return memoryArray[targetAmount-1];
        }

        //当前所要组合数值的 最小组合次数
        int currentMinTime=Integer.MAX_VALUE;
        for (int coin:coins){
            int res=coinsExchange(coins,targetAmount-coin,memoryArray);
//            if (res>0&&res<currentMinTime){//错误三 : 在递归到0的时候,弹回0的最小值,但是确没有进入计算步骤,所以永远是Integer.MAX_VALUE
            if (res>=0&&res<currentMinTime){
//                currentMinTime=res;//错误2: 没有加1
                //currentTime代表凑成当前面值的组合数, 而res代表凑成 targetAmount-coin (前一步)的面值需要的组合数, 两者之间应该相差了一枚硬币
                currentMinTime=res+1;
            }
        }
        //这里直接把 currentTime返回去是可以的,但是这样就是普通的递归求解,并且递归出口3就没用上.
        //只有在每一次递归里面记录下当前指针的最小值 , 并且定位到已经记录的最小值时,将递归弹出,才能避免重复递归

        memoryArray[targetAmount-1]=currentMinTime==Integer.MAX_VALUE?-1:currentMinTime;
        return memoryArray[targetAmount-1];//错误4:返回的不是currentTime而是memoryArray[taegetAmount-1]
    }

}
