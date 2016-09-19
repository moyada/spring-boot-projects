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
 * @description mongoʵ��ӳ���ļ�
 */
@Entity(value = "users", noClassnameStored = true) // �������ϣ���ָ��������(Ĭ��Ϊ����)
// ָ��������valueΪ������,fieldΪ�����ֶ�($**ƥ�����г�Ա)
@Indexes({ @Index(fields = @Field(value = "age", type = IndexType.ASC)),
		// ����ȫ�ļ���
		@Index(fields = @Field(value = "name", type = IndexType.TEXT)) })
// �����ⲿ������
@EntityListeners(UserListener.class)
public class User {
	@Id
	private ObjectId id;
	@Indexed
	private String name;
	@Property("birthday") // �����ĵ����ֶ�����Ĭ��Ϊ������
	private Date birthday;
	private Integer age;

	@Transient // ���Դ��ֶ�
	private Long serivableId;

	@Reference // ��������
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