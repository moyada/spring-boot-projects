package com.xyk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.EntityListeners;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.PostLoad;
import org.mongodb.morphia.annotations.PostPersist;
import org.mongodb.morphia.annotations.PreLoad;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.PreSave;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Transient;
import org.mongodb.morphia.utils.IndexType;

/**
 * Created by xueyikang on 2016/9/19.
 * @version 1.0
 * @since  1.7
 * @description mongo实体映射文件
 */
@Entity(value = "users", noClassnameStored = true) // 声明集合，可指定集合名(默认为类名)
// 指定索引，value为索引名,field为索引字段($**匹配所有成员)
@Indexes({ @Index(fields = @Field(value = "age", type = IndexType.ASC)),
		// 设置全文检索
		@Index(fields = @Field(value = "name", type = IndexType.TEXT)) })
// 设置外部监听类
@EntityListeners(UserListener.class)
public class User {
	@Id
	private ObjectId id;
	@Indexed
	private String name;
	@Property("birthday") // 更改文档中字段名，默认为属性名
	private Date birthday;
	private Integer age;

	@Transient // 忽略此字段
	private Long serivableId;

	@Reference // 设置引用
	private User introducer;

	@Reference
	private List<User> directReports = new ArrayList<>();

	@PrePersist
	public void prePersist() {
		System.out.println("PrePersist...");
		if (birthday == null)
			birthday = new Date();
	}

	@PreSave
	public void preSave() {
		System.out.println("PreSave...");
	}

	@PostPersist
	public void postPersist() {
		System.out.println("PostPersist...");
	}

	@PreLoad
	public void preLoad() {
		System.out.println("PreLoad...");
	}

	@PostLoad
	public void postLoad() {
		System.out.println("PostLoad...");
	}

	public User() {
	}

	public User(String name, Date birthday, int age) {
		this.name = name;
		this.birthday = birthday;
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public List<User> getDirectReports() {
		return directReports;
	}

	public ObjectId getId() {
		return id;
	}

	public User getIntroducer() {
		return introducer;
	}

	public String getName() {
		return name;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setDirectReports(List<User> directReports) {
		this.directReports = directReports;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public void setIntroducer(User introducer) {
		this.introducer = introducer;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthday=" + birthday + ", age=" + age + ", introducer="
				+ introducer + ", directReports=" + directReports + "]";
	}

}