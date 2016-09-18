package com.xyk.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "testCache")
@Service
public class CacheService {

	private final AtomicInteger counter = new AtomicInteger(0);

	@CachePut(key = "#key")
	public Object setValue(final String key, final String value) {
		System.out.println("更新Cache" + value);
		return value;
	}

	@Cacheable(key = "#key")
	public Object getValue(final String key) {
		int c = counter.getAndIncrement();
		System.out.println("无缓存的时候调用这里: " + c);
		return counter.getAndIncrement();
	}

	@CacheEvict(allEntries = true)
	public void clean() {
		System.out.println("清空Cache");
	}
}
