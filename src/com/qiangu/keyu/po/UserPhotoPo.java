package com.qiangu.keyu.po;

import java.util.Date;

/**
 * 用户相册表
 * @author lyf
 *
 */
public class UserPhotoPo {

	public UserPhotoPo() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//用户id
	private Integer userId;
	//照片id
	private Integer pictureId;
	//更新时间
	private Date updateTime;
	//是否是当前使用
	private Integer isNow;
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
	public Integer getPictureId() {
		return pictureId;
	}
	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getIsNow() {
		return isNow;
	}
	public void setIsNow(Integer isNow) {
		this.isNow = isNow;
	}
	@Override
	public String toString() {
		return "UserPhotoPo [id=" + id + ", userId=" + userId + ", pictureId=" + pictureId + ", updateTime="
				+ updateTime + ", isNow=" + isNow + "]";
	}
	
	
}
