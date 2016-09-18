package com.xyk.mongo;

import javax.annotation.PreDestroy;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@ConditionalOnClass(Mongo.class)
public class MorphiaFactory {

	@Value("${spring.mongodb.host}")
	private String host;
	@Value("${spring.mongodb.port}")
	private int port;
	@Value("${spring.mongodb.database}")
	private String database;

	private Mongo mongo;

	@PreDestroy
	public void close() {
		if (this.mongo != null) {
			this.mongo.close();
		}
	}

	@Bean
	@Qualifier("Datastore")
	@ConditionalOnMissingBean
	@Scope
	public Datastore getDatastore() {
		mongo = new MongoClient(host, port);
		Morphia morphia = new Morphia();
		System.out.println("new mongo");
		Datastore datastore = morphia.createDatastore((MongoClient) mongo, database);
		datastore.ensureIndexes();
		return datastore;
	}
}
