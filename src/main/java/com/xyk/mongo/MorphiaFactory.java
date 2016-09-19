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

/**
 * Created by xueyikang on 2016/9/19.
 * @version 1.0
 * @since  1.7
 * @description mongodb框架Morphia的配置工厂
 */
@Configuration
// 必须拥有此文件
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

	/**
	 * @author xueyikang
	 * @description 配置morphia实例，获得DataStore
	 *
	 * @param
	 * @return
	 * @throws
	 */
	@Bean
	@Qualifier("Datastore")
	@ConditionalOnMissingBean
	@Scope // 配置单例
	public Datastore getDatastore() {
		mongo = new MongoClient(host, port);
		Morphia morphia = new Morphia();
		System.out.println("new mongo");
		Datastore datastore = morphia.createDatastore((MongoClient) mongo, database);
		datastore.ensureIndexes();
		return datastore;
	}
}
