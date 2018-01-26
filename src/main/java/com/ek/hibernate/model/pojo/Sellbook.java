package com.ek.hibernate.model.pojo;
// Generated 2017-6-12 23:17:22 by Hibernate Tools 3.5.0.Final

/**
 * Sellbook generated by hbm2java
 */
@SuppressWarnings("serial")
public class Sellbook implements java.io.Serializable {
 
	private Integer uuid;
	private Bookinfo bookinfo;
	private Userinfo userinfo;
	private Integer booknumber;
	private Double summoney;
	private String adddate;
	private String remark;
	private String posted;

	public Sellbook() {
	}

	public Sellbook(Integer booknumber, Double summoney, String adddate) {
		this.booknumber = booknumber;
		this.summoney = summoney;
		this.adddate = adddate;
	}

	public Sellbook(Bookinfo bookinfo, Userinfo userinfo, Integer booknumber, Double summoney, String adddate, String remark,
			String posted) {
		this.bookinfo = bookinfo;
		this.userinfo = userinfo;
		this.booknumber = booknumber;
		this.summoney = summoney;
		this.adddate = adddate;
		this.remark = remark;
		this.posted = posted;
	}

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public Bookinfo getBookinfo() {
		return bookinfo;
	}

	public void setBookinfo(Bookinfo bookinfo) {
		this.bookinfo = bookinfo;
	}

	public Userinfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public Integer getBooknumber() {
		return booknumber;
	}

	public void setBooknumber(Integer booknumber) {
		this.booknumber = booknumber;
	}

	public Double getSummoney() {
		return summoney;
	}

	public void setSummoney(Double summoney) {
		this.summoney = summoney;
	}

	public String getAdddate() {
		return adddate;
	}

	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPosted() {
		return posted;
	}

	public void setPosted(String posted) {
		this.posted = posted;
	}
}
