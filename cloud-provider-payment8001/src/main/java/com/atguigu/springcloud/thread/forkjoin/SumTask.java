package com.atguigu.springcloud.thread.forkjoin;

import cn.hutool.core.util.RandomUtil;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * forkjoin并行计算
 * @author sunhcer.shi
 * @date 2021/06/01 19:41
 **/
public class SumTask extends RecursiveTask<Long> {

    static final int THRESHOLD =500;
    long[] array;
    int start;
    int end;

    public SumTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int batchCount = end - start;
        if (batchCount<=THRESHOLD){
            //如果 任务足够小 ,直接计算
            long sum =0 ;
            for (int i = start; i < end; i++) {
                sum += this.array[i];
                //延迟计算速度
                try {
                    Thread.sleep(1);
                }catch (Exception e){
                    System.out.println("异常:{}"+e.getMessage());
                }
            }
            return sum;
        }
        int middle = (end+start)/2;
        System.out.println(String.format("将 %d-%d 切分成 %d-%d 和 %d-%d ",start,end,start,middle,middle,end));



        //递归分治
        SumTask sumTask = new SumTask(this.array, start, middle);
        SumTask sumTask1 = new SumTask(this.array, middle, end);
        invokeAll(sumTask,sumTask1);
        //主线程等待所有子线程完毕
        Long join = sumTask.join();
        Long join1 = sumTask1.join();



        long result = join + join1;
        System.out.println("result= "+join+" + "+join1+"= "+result);
        return result;
    }


    //测试
    public static void main(String[] args) {
        //创建2000个随机数组
        long[] array = new long[2000];
        long expectedSum = 0;
        for (int i = 0; i <array.length ; i++) {
            array[i] = RandomUtil.randomLong(1L,50L);
            expectedSum += array[i];
        }
        System.out.println("单线程计算结果:expectedSum : "+expectedSum);
        // fork/join:
        SumTask sumTask = new SumTask(array, 0, array.length);
        long startTime = System.currentTimeMillis();
        Long forkResult = ForkJoinPool.commonPool().invoke(sumTask);
        long endTime = System.currentTimeMillis();
        System.out.println("fork/join计算结果:"+forkResult + " 消耗时间:"+(endTime-startTime)+" :ms");

    }

}
