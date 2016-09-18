package com.xyk.mq;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Producer implements ConfirmCallback {

	@Value("${spring.rabbitmq.myExchange}")
	private String myExchange;

	@Value("${spring.rabbitmq.myRoutingKey}")
	private String myRoutingKey;

	private RabbitTemplate rabbitTemplate;

	@Autowired
	public Producer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		rabbitTemplate.setConfirmCallback(this);
	}

	/**
	 * @param msg
	 *            数据
	 */
	public void produce(Object msg) {
		CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
		rabbitTemplate.convertAndSend(myExchange, myRoutingKey, msg, correlationData);
	}

	/**
	 * 确认发送到队列中
	 *
	 * @param correlationData
	 *            这个可以用来传递一些数据 然后就知道现在成功收到的是哪个数据
	 * @param ack
	 * @param cause
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		// TODO Auto-generated method stub
		if (ack) {
			System.out.println("消息队列处理成功 " + correlationData.getId());
		} else {
			System.out.println("消息队列处理失败: " + correlationData.getId() + cause);
		}
	}
}
