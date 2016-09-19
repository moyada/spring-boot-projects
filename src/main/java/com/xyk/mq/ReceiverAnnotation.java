package com.xyk.mq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by xueyikang on 2016/9/19.
 * @version 1.0
 * @since  1.7
 * @description 基于注解的接受消息后的处理，配置连接主机名、队列名、路由名、路由方式、关键字
 */
/*
@RabbitListener(
        containerFactory = "myConnectionFactory",
        bindings = @QueueBinding(
                value = @Queue(value = "myQueue",durable = "true"),
                exchange = @Exchange(value = "myExchange",type = ExchangeTypes.DIRECT),
                key = "myRoutingKey")
)
@Component
*/
public class ReceiverAnnotation {

	// 处理接受消息
	@RabbitHandler
	public void receiveMessage(String str) {
		System.out.println("Receiver String" + str);
	}
}
