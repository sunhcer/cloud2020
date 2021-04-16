package com.atguigu.springcloud;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.SendingContext.RunTime;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * mq发送测试类
 *
 * @author sunhcer
 * @date 16:56
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class MqSenderTest {

    //测试类配置切面
    public static long concurrentTime1, concurrentTime2, concurrentMemory1, concurrentMemory2;

    @Before
    public void before() {
        //得到出个必须开始的系统时间(纳秒级, 最终转换为毫秒级,保留两位小数)
        concurrentTime1 = System.nanoTime();
        //得到虚拟机运行,程序开始运行时jvm所占用的内存
        Runtime runtime = Runtime.getRuntime();
        concurrentMemory1 = runtime.totalMemory() - runtime.freeMemory();
    }

    @After
    public void after() {
        //得到程序执行完毕的执行时间(纳秒级)
        concurrentTime2 = System.nanoTime();
        //得到执行完毕时jvm所占用的内存(byte)
        Runtime runTime = Runtime.getRuntime();
        concurrentMemory2 = runTime.totalMemory() - runTime.freeMemory();

        /**
         *计算start和end之间的代码执行期间所消耗时间与内存
         * 1ms=1000us=1000000ns
         * 1MB=1024 kb = 1024*1024 byte
         */
        String costTime=String.valueOf((concurrentTime2-concurrentTime1)/1000000);
        String costMemory=String.valueOf(concurrentMemory2-concurrentMemory1/(1024*1024));
        System.out.println("执行时间:"+costTime);
        System.out.println("内存消耗:"+costMemory);
        System.out.println("---------------执行时间为：" + costTime.substring(0,costTime.equals("0.0") ? 1 : (costTime.indexOf(".")+3)) + " ms, 消耗内存：" + costMemory.substring(0,costMemory.equals("0.0") ? 1 : (costMemory.indexOf(".")+3)) + " M + !---------------");
    }

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 22:43:59 框架注释
     * 直连队列模式
     */
    @Test
    public void send() {
        for (int i = 0; i < 100; i++) {
//            amqpTemplate.convertAndSend("emsmsg", "第" + i + "条消息");
            amqpTemplate.convertAndSend("hello", "第" + i + "条消息");
        }
    }


    /**
     * 22:44:18 框架注释
     * 工作队列模式 生产者
     * 指定队列,但是消息可以轮询给不同的实例(消费者),每个消息只能被消费一次
     */
    @Test
    public void work() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "工作模式---" + i);
        }
    }

    /**
     * 23:00:03 框架注释
     * 发布订阅 fanout 广播模式 生产者
     * 指定交换机,广播给每一个消费者,每条消息会被多次消费
     */
    @Test
    public void fanout() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("fanout", "", "fanout--发布订阅--广播模式----" + i);
        }
    }

    /**
     * 23:45:49 框架注释
     * 路由模式 route
     * 在广播模式的基础上多了一个routingkey
     */
    @Test
    public void route() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("route", "info", "路由模式----" + i);
        }

        rabbitTemplate.convertAndSend("route", "error", "路由模式----" + 10);
    }

    /**
     * 2021/02/18 00:10:15 框架注释
     * topic 订阅模式(消费者可以决定自己要订阅哪些 路由key的信息)
     * 生产者
     */
    @Test
    public void topic() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("topics", "user.save", "分组订阅模式----" + i);
        }
        rabbitTemplate.convertAndSend("topics", "order.save", "分组订阅模式----" + 9999);
    }


    @Test
    public void testAOP(){
        for (int i = 0; i <1000; i++) {
            System.out.println(i);
        }
    }

}
