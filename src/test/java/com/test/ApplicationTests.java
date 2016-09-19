package com.test;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.mchange.v2.lang.ThreadUtils;
import com.xyk.Application;
import com.xyk.mq.Producer;
import com.xyk.redis.RedisTemplateFactory;

/**
 * Created by xueyikang on 2016/9/19.
 * @version 1.0
 * @since  1.8
 * @description Junit≤‚ ‘≥Ã–Ú
 */
@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	Datastore datastore;

	@Test
	public void mongoTest() {
		try {
			System.out.println(datastore.getDB().getName());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Resource(name = "stringRedisTemplate")
	RedisTemplate<String, Object> stringRedisTemplate;

	@Resource(name = "objectRedisTemplate")
	RedisTemplate<String, Object> objectRedisTemplate;
	@Autowired
	RedisTemplateFactory factory;

	@Test
	public void redisTest() {
		try {
			System.out.println(factory == null);
			stringRedisTemplate.opsForValue().set("name", "xyk");
			System.out.println(stringRedisTemplate.opsForValue().get("name"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Autowired
	Producer producer;

	@Test
	public void amqpTest() {
		try {
			producer.produce("haha");
			TimeUnit.SECONDS.sleep(60);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
