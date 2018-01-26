package com.ek.hibernate.struts.form;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class UserInfoForm extends ActionForm {

	private String userid;
	private String username;
	private String userpassword;
	private String sex;
	private String age;
	private String postalcode;
	private String address;
	private String schoolage;
	private String personlike;
	private String registerdate;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSchoolage() {
		return schoolage;
	}
	public void setSchoolage(String schoolage) {
		this.schoolage = schoolage;
	}
	public String getPersonlike() {
		return personlike;
	}
	public void setPersonlike(String personlike) {
		this.personlike = personlike;
	}
	public String getRegisterdate() {
		return registerdate;
	}
	public void setRegisterdate(String registerdate) {
		this.registerdate = registerdate;
	}
}
