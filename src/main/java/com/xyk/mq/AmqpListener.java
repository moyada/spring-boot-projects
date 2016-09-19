package com.xyk.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

/**
 * Created by xueyikang on 2016/9/19.
 * @version 1.0
 * @since  1.7
 * @description ��Ϣ������
 */
@Component("amqpListener")
public class AmqpListener implements ChannelAwareMessageListener {

	/**
	 * @author xueyikang
	 * @description ���ݽ�����Ϣ�����ݼ���Դ��������
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
		// ȷ����Ϣ�ɹ�����
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
}
