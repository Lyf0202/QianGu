package com.qiangu.keyu.po;

/**
 * 用户设备表
 * @author lyf
 *
 */
public class AppPo {

	public AppPo() {
		// TODO Auto-generated constructor stub
	}
	//id
	private Integer id;
	//用户id
	private Integer userId;
	//用户当前app版本号
	private Integer appVersion;
	//用户当前登录设备号
	private String phone;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(Integer appVersion) {
		this.appVersion = appVersion;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "AppPo [id=" + id + ", userId=" + userId + ", appVersion=" + appVersion + ", phone=" + phone + "]";
	}
	
	
}
