package com.atguigu.springcloud.mulitthread.disruptor.quickstart;

import com.lmax.disruptor.EventHandler;

public class OrderEventHandler implements EventHandler<OrderEvent>{

	@Override
	public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
		Thread.sleep(Integer.MAX_VALUE);
		System.err.println("消费者: " + event.getValue());
	}

}
