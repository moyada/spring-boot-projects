package com.xyk.mq;

import java.util.concurrent.atomic.LongAdder;

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

@Configuration
@EnableRabbit
public class AmqpConfig {

	@Value("${spring.rabbitmq.host}")
	private String rabbitmqHost;
	@Value("${spring.rabbitmq.port}")
	private int rabbitmqPort;
	@Value("${spring.rabbitmq.username}")
	private String rabbitmqUser;
	@Value("${spring.rabbitmq.password}")
	private String rabbitmqPwd;

	@Value("${spring.rabbitmq.myExchange}")
	private String myExchange;
	@Value("${spring.rabbitmq.myQueue}")
	private String myQueue;
	@Value("${spring.rabbitmq.myRoutingKey}")
	private String myRoutingKey;
	@Value("${spring.rabbitmq.myConsumer}")
	private boolean myConsumer;

	// 定义一个路由
	@Bean
	public DirectExchange myExchange() {
		return new DirectExchange(myExchange);
	}

	// 定义一个队列
	@Bean
	public Queue myQueue() {
		return new Queue(myQueue, true);
	}

	// 绑定 用这个key可以通过本路由，并且传递到响应的队列
	@Bean
	public Binding bindingCalculateSortingFreight() {
		return BindingBuilder.bind(myQueue()).to(myExchange()).with(myRoutingKey);
	}

	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // 必须是prototype类型
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		return template;
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitmqHost, rabbitmqPort);
		connectionFactory.setUsername(rabbitmqUser);
		connectionFactory.setPassword(rabbitmqPwd);
		connectionFactory.setVirtualHost("/");
		connectionFactory.setPublisherConfirms(true); // 必须要设置,用户消息回调
		return connectionFactory;
	}

	@Resource(name = "amqpListener")
	private ChannelAwareMessageListener amqpListener;

	@Bean
	public SimpleMessageListenerContainer myContainer(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		if (myConsumer) {
			SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
			container.setQueues(myQueue());// 创建队列
			container.setExposeListenerChannel(true);
			container.setMaxConcurrentConsumers(5);
			container.setConcurrentConsumers(1);
			container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置确认模式手工确认
			// container.setMessageListener(listenerAdapter);
			container.setMessageListener(amqpListener);
			return container;
		}
		return null;
	}

	@Bean(name = "myConnectionFactory")
	SimpleRabbitListenerContainerFactory createSimpleRabbitListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		return factory;
	}

	@Bean
	Receiver receiver() {
		return new Receiver();
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiverMessage");
	}
}
