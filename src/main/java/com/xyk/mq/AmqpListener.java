package com.xyk.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

/**
 * Created by xueyikang on 2016/9/19.
 * @version 1.0
 * @since  1.7
 * @description 消息监听类
 */
@Component("amqpListener")
public class AmqpListener implements ChannelAwareMessageListener {

	/**
	 * @author xueyikang
	 * @description 根据接受消息的数据及来源作出处理
	 *
	 * @param
	 * @return
	 * @throws
	 */
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(message.getMessageProperties());
		System.out.println("receiver " + new String(message.getBody()) + " from " + channel.getChannelNumber());
		// 确认消息成功处理
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
}
