package com.xyk.mq;

import javax.annotation.Resource;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/*
@Configuration
@EnableRabbit
*/
public class AmqpConfigAnnotation {

	@Value("${spring.rabbitmq.host}")
	private String rabbitmqHost;
	@Value("${spring.rabbitmq.port}")
	private int rabbitmqPort;
	@Value("${spring.rabbitmq.username}")
	private String rabbitmqUser;
	@Value("${spring.rabbitmq.password}")
	private String rabbitmqPwd;

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitmqHost, rabbitmqPort);
		connectionFactory.setUsername(rabbitmqUser);
		connectionFactory.setPassword(rabbitmqPwd);
		connectionFactory.setVirtualHost("/");
		connectionFactory.setPublisherConfirms(true); // 必须要设置,用户消息回调
		return connectionFactory;
	}

	@Bean(name = "myConnectionFactory")
	SimpleRabbitListenerContainerFactory createSimpleRabbitListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		return factory;
	}

	@Bean
	ReceiverAnnotation receiver() {
		return new ReceiverAnnotation();
	}

	@Bean
	MessageListenerAdapter listenerAdapter(ReceiverAnnotation receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
}
