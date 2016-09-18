package com.xyk.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

	@Autowired
	private RedisConnectionFactory redisConnFactory;
	
	
	/**
	 * 获取redis连接工厂
	 * @return
	 */
	public RedisConnectionFactory getRedisConnFactory(){
		return redisConnFactory;
	}
	
	
	
	@Bean(name="objectRedisTemplate")
	public RedisTemplate<String, Object> objectRedisTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(redisConnFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new JdkSerializationRedisSerializer());
		template.setValueSerializer(new JdkSerializationRedisSerializer());
		return template;
	}
	
	@Bean(name="stringRedisTemplate")
	public RedisTemplate<String, Object> longRedisTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(redisConnFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new GenericToStringSerializer<String>(String.class));
		template.setValueSerializer(new GenericToStringSerializer<String>(String.class));
		return template;
	}
}
