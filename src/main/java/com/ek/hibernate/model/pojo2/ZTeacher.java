package com.ek.hibernate.model.pojo2;
// Generated 2017-6-16 3:18:52 by Hibernate Tools 3.5.0.Final

/**
 * ZTeacher generated by hbm2java
 */
public class ZTeacher implements java.io.Serializable , java.lang.Comparable<ZTeacher>{

	private Integer uuid;
	private String teName;
	private String teSex;
	private ZLesson zLesson;
	
	public ZTeacher() {
	}

	public ZTeacher(ZLesson zLesson, String teName, String teSex) {
		this.zLesson = zLesson;
		this.teName = teName;
		this.teSex = teSex;
	}

	public Integer getUuid() {
		return this.uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public ZLesson getzLesson() {
		return zLesson;
	}

	public void setzLesson(ZLesson zLesson) {
		this.zLesson = zLesson;
	}

	public String getTeName() {
		return this.teName;
	}

	public void setTeName(String teName) {
		this.teName = teName;
	}

	public String getTeSex() {
		return this.teSex;
	}

	public void setTeSex(String teSex) {
		this.teSex = teSex;
	}

	// 返回  0相等  -1小于  1大于
 	@Override
	public int compareTo(ZTeacher arg0) {
		if(arg0!=null){
			return -1;
		}
		if(arg0.getUuid() == this.uuid){
			return 0;
		}
		if(arg0.getUuid().intValue() > this.uuid.intValue()){
			return 1;
		}
		if(arg0.getUuid().intValue() < this.uuid.intValue()){
			return -1;
		}
		
		return -1;
	}
}