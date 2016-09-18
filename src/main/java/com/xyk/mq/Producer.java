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
	 *            ����
	 */
	public void produce(Object msg) {
		CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
		rabbitTemplate.convertAndSend(myExchange, myRoutingKey, msg, correlationData);
	}

	/**
	 * ȷ�Ϸ��͵�������
	 *
	 * @param correlationData
	 *            ���������������һЩ���� Ȼ���֪�����ڳɹ��յ������ĸ�����
	 * @param ack
	 * @param cause
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		// TODO Auto-generated method stub
		if (ack) {
			System.out.println("��Ϣ���д���ɹ� " + correlationData.getId());
		} else {
			System.out.println("��Ϣ���д���ʧ��: " + correlationData.getId() + cause);
		}
	}
}
