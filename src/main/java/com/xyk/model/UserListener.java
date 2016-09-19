package com.xyk.model;

import org.mongodb.morphia.annotations.PreLoad;
import org.mongodb.morphia.annotations.PrePersist;

import com.mongodb.DBObject;

/**
 * Created by xueyikang on 2016/9/19.
 * @version 1.0
 * @since  1.7
 * @description mongo监听类
 */
public class UserListener {
	// 数据保存前操作
	@PrePersist
	private void prePersist(final Object entity, final DBObject dbObject) {
		System.out.println("listener...");
	}

	// 数据读取前操作
	@PreLoad
	private void preLoad(final Object entity, final DBObject dbObject) {
		System.out.println(entity);
		System.out.println(dbObject);
	}
}
