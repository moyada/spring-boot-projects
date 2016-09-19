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
 * @description ʹ��Cache��ҵ��
 */
// ���ü��й���keys�ı�ʶ
@CacheConfig(cacheNames = "testCache")
@Service
public class CacheService {

	private final AtomicInteger counter = new AtomicInteger(0);

	// ���»���
	@CachePut(key = "#key")
	public Object setValue(final String key, final String value) {
		System.out.println("����Cache" + value);
		return value;
	}

	// ��ѯ����
	@Cacheable(key = "#key")
	public Object getValue(final String key) {
		int c = counter.getAndIncrement();
		System.out.println("�޻����ʱ���������: " + c);
		return counter.getAndIncrement();
	}

	// ��ջ���
	@CacheEvict(allEntries = true)
	public void clean() {
		System.out.println("���Cache");
	}
}
