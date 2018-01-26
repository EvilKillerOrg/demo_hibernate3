package com.ek.hibernate.model.pojo1;
// Generated 2017-6-12 23:42:05 by Hibernate Tools 3.5.0.Final

import java.util.Date;

/**
 * TestUserInfo generated by hbm2java
 */
@SuppressWarnings("serial")
public class TestUserInfo implements java.io.Serializable {

	private Integer uuid;
	private String username;
	private Boolean sex;
	private Date birthday;
	
	private Integer age; //加一个表中没有的字段 ,值可以用sql计算得到 并在映射文件里配置

	public TestUserInfo() {
	}

	public TestUserInfo(String username , Date birthday) {
		this.username = username;
		this.birthday = birthday;
	}
	
	public TestUserInfo(String username, Boolean sex, Date birthday) {
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

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}