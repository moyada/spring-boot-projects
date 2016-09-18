package com.xyk.redis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisTemplateFactory {
	
	@Autowired
	private RedisConnectionFactory redisConnFactory;

	@SuppressWarnings("rawtypes")
	private Map<String, RedisTemplate> rTplMap = new HashMap<String, RedisTemplate>();

	@SuppressWarnings("unchecked")
	public <T> RedisTemplate<String, T> getJsonRedisTemplate(Class<T> clazz) {
		String className = clazz.getCanonicalName();

		RedisTemplate<String, T> rtpl = rTplMap.get(className);
		if (rtpl == null) {
			rtpl = new RedisTemplate<String, T>();
			rtpl.setConnectionFactory(redisConnFactory);
			rtpl.setKeySerializer(new StringRedisSerializer());
			rtpl.setHashValueSerializer(new Jackson2JsonRedisSerializer<T>(clazz));
			rtpl.setValueSerializer(new Jackson2JsonRedisSerializer<T>(clazz));
			rtpl.afterPropertiesSet();
			rTplMap.put(className, rtpl);
		}

		return rtpl;
	}

	/**
	 * 获取处理Delivery类型的redisTemplate
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public RedisTemplate<String, Map> getDeliveryRedisTemplate() {
		return getJsonRedisTemplate(Map.class);
	}
}
