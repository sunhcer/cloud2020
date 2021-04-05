package com.atguigu.springcloud.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 
 * @author sunhcer
 * @date 2021/02/17 16:53
 **/
@Slf4j
@Component
public class MqReceiver {

    /**
     * 接收消息并打印
     *
     * @param message message
     */
    @RabbitListener(queuesToDeclare = @Queue("emsmsg"))
    public void process(String message) {
        // @RabbitListener注解用于监听RabbitMQ，queues指定监听哪个队列
        log.info(message);
    }

    /**  22:38:28 框架注释
     *  直连模式
     *  持久化等属性都在@Queue注解里面
     */
    @RabbitListener(queuesToDeclare = @Queue(value = "hello",durable = "true"))
    public void process1(String message) {
        // @RabbitListener注解用于监听RabbitMQ，queues指定监听哪个队列
        log.info("直连模式---接收 "+message);
    }

    /** 2021/02/17 22:48:10 框架注释
     *  work模式 消费(接受消息)者1
     */
    @RabbitListener(queuesToDeclare = @Queue(value = "work",durable = "true"))
    public void process2(String message) {
        // @RabbitListener注解用于监听RabbitMQ，queues指定监听哪个队列
        log.info("work---消费者1"+message);
    }

    /** 2021/02/17 22:48:10 框架注释
     *  work模式 消费(接受消息)者2
     */
    @RabbitListener(queuesToDeclare = @Queue(value = "work",durable = "true"))
    public void process3(String message) {
        // @RabbitListener注解用于监听RabbitMQ，queues指定监听哪个队列
        log.info("work---消费者2"+message);
    }

    /** 2021/02/17 22:58:27 框架注释
     *  发布/订阅 (广播)fanout 模型  消费者者1
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    value=@Queue,//什么都不写表示创建临时队列
                    exchange = @Exchange(value="fanout",type = "fanout")) //绑定交换机,如果交换机不存在就创建该交换机)
    })
    public void process4(String message) {
        // @RabbitListener注解用于监听RabbitMQ，queues指定监听哪个队列
        log.info("fanout---消费者1"+message);
    }

    /** 2021/02/17 22:58:27 框架注释
     *  发布/订阅 (广播)fanout 模型  消费者2
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    value=@Queue,//什么都不写表示创建临时队列
                    exchange = @Exchange(value="fanout",type = "fanout")) //绑定交换机,如果交换机不存在就创建该交换机)
    })
    public void process5(String message) {
        // @RabbitListener注解用于监听RabbitMQ，queues指定监听哪个队列
        log.info("fanout---消费者2"+message);
    }

    /** 2021/02/17 23:51:13 框架注释
     * 路由模式 (使得一个队列能做多件事)
     * 消费者1
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    value=@Queue,//什么都不写表示创建临时队列
                    exchange = @Exchange(value="route",type = "direct")//绑定交换机,如果交换机不存在就创建该交换机)
                    ,key = {"error","info"}
            )
    })
    public void process6(String message) {
        // @RabbitListener注解用于监听RabbitMQ，queues指定监听哪个队列
        log.info("route---消费者1"+message);
    }

    /** 2021/02/17 23:51:13 框架注释
     * 路由模式 (使得一个队列能做多件事)
     * 消费者2
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    value=@Queue,//什么都不写表示创建临时队列
                    exchange = @Exchange(value="route",type = "direct")//绑定交换机,如果交换机不存在就创建该交换机)
                    ,key = {"error"}
            )
    })
    public void process7(String message) {
        // @RabbitListener注解用于监听RabbitMQ，queues指定监听哪个队列
        log.info("route---消费者2"+message);
    }

    /** 2021/02/18 00:10:15 框架注释
     *订阅模式
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    value=@Queue,//什么都不写表示创建临时队列
                    exchange = @Exchange(name="topics",type = "topic")//绑定交换机,如果交换机不存在就创建该交换机)
                    ,key = {"user.save","user.*"}
            )
    })
    public void process8(String message) {
        // @RabbitListener注解用于监听RabbitMQ，queues指定监听哪个队列
        log.info("topic---消费者1"+message);
    }

    /** 2021/02/18 00:10:15 框架注释
     *订阅模式
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    value=@Queue,//什么都不写表示创建临时队列
                    exchange = @Exchange(name="topics",type = "topic")//绑定交换机,如果交换机不存在就创建该交换机)
                    ,key = {"order.#","user.*"}
            )
    })
    public void process9(String message) {
        // @RabbitListener注解用于监听RabbitMQ，queues指定监听哪个队列
        log.info("topic---消费者2"+message);
    }


}