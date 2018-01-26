package com.ek.hibernate.model.pojo4;
// Generated 2017-6-26 1:47:00 by Hibernate Tools 3.5.0.Final

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "test_annotation")
public class TestAnnotation implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //自增 
	private Integer uuid;
	private String username;
	//@Column 这个可以用来对列的映射
	private Integer sex;
	private Date birthday;

	public TestAnnotation() {
	}

	public TestAnnotation(String username, Integer sex, Date birthday) {
		this.username = username;
		this.sex = sex;
		this.birthday = birthday;
	}

	public Integer getUuid() {
		return this.uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}