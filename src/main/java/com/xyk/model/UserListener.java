package com.xyk.model;

import org.mongodb.morphia.annotations.PreLoad;
import org.mongodb.morphia.annotations.PrePersist;

import com.mongodb.DBObject;

/**
 * Created by xueyikang on 2016/9/19.
 * @version 1.0
 * @since  1.7
 * @description mongo������
 */
public class UserListener {
	// ���ݱ���ǰ����
	@PrePersist
	private void prePersist(final Object entity, final DBObject dbObject) {
		System.out.println("listener...");
	}

	// ���ݶ�ȡǰ����
	@PreLoad
	private void preLoad(final Object entity, final DBObject dbObject) {
		System.out.println(entity);
		System.out.println(dbObject);
	}
}
