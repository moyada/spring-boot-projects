package com.xyk.mq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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

	@RabbitHandler
	public void receiveMessage(String str) {
		System.out.println("Receiver String" + str);
	}
}
