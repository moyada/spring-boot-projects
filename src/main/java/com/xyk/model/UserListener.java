package com.xyk.model;

import org.mongodb.morphia.annotations.PreLoad;
import org.mongodb.morphia.annotations.PrePersist;

import com.mongodb.DBObject;

public class UserListener {
	@PrePersist
	private void prePersist(final Object entity, final DBObject dbObject) {
		System.out.println("listener...");
	}

	@PreLoad
	private void preLoad(final Object entity, final DBObject dbObject) {
		System.out.println(entity);
		System.out.println(dbObject);
	}
}
