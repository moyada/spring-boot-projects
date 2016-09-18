package com.xyk.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component("amqpListener")
public class AmqpListener implements ChannelAwareMessageListener {

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(message.getMessageProperties());
		System.out.println("receiver " + new String(message.getBody()) + " from " + channel.getChannelNumber());
		// 确认消息成功处理
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
}
