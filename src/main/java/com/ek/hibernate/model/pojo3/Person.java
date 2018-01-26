package com.ek.hibernate.model.pojo3;
// Generated 2017-6-22 18:24:22 by Hibernate Tools 3.5.0.Final

import java.util.Date;

/**
 * Person generated by hbm2java
 */
public class Person implements java.io.Serializable {

	private Integer uuid;
//	private String inital;        //这3个放在Name中去了
//	private String firstname;
//	private String lastname;
	private Name name;
	private Integer sex;
	private Date birthday;

	public Person() {
	}

	public Person( Name name, Integer sex, Date birthday) {
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
	}

	public Integer getUuid() {
		return this.uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
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