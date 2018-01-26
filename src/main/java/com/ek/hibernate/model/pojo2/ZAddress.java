package com.ek.hibernate.model.pojo2;
// Generated 2017-6-16 3:18:52 by Hibernate Tools 3.5.0.Final

/**
 * ZAddress generated by hbm2java
 */
@SuppressWarnings("serial")
public class ZAddress implements java.io.Serializable {

	private Integer uuid;
	private String street;
	private String postcode;
	//private Set ZStudents = new HashSet(0); 这是地址对应多个学生
	private ZStudent zStudent; //这样才是一个地址对应一个学生
	
	public ZAddress() {
	}

	public ZAddress(String street, String postcode, ZStudent zStudent) {
		this.street = street;
		this.postcode = postcode;
		this.zStudent = zStudent;
	}

	public Integer getUuid() {
		return this.uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public ZStudent getzStudent() {
		return zStudent;
	}

	public void setzStudent(ZStudent zStudent) {
		this.zStudent = zStudent;
	}
}