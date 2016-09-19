package com.xyk.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by xueyikang on 2016/9/19.
 * @version 1.0
 * @since  1.7
 * @description 使用Cache的业务
 */
// 设置集中管理keys的标识
@CacheConfig(cacheNames = "testCache")
@Service
public class CacheService {

	private final AtomicInteger counter = new AtomicInteger(0);

	// 更新缓存
	@CachePut(key = "#key")
	public Object setValue(final String key, final String value) {
		System.out.println("更新Cache" + value);
		return value;
	}

	// 查询缓存
	@Cacheable(key = "#key")
	public Object getValue(final String key) {
		int c = counter.getAndIncrement();
		System.out.println("无缓存的时候调用这里: " + c);
		return counter.getAndIncrement();
	}

	// 清空缓存
	@CacheEvict(allEntries = true)
	public void clean() {
		System.out.println("清空Cache");
	}
}
