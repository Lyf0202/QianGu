package com.qiangu.keyu.po;

import java.util.Date;

public class LikePo {

	public LikePo() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//点击用户的id
	private Integer userId;
	//喜欢对象的用户id
	private Integer likeUserId;
	//点击时间
	private Date likeTime;
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
	public Integer getLikeUserId() {
		return likeUserId;
	}
	public void setLikeUserId(Integer likeUserId) {
		this.likeUserId = likeUserId;
	}
	public Date getLikeTime() {
		return likeTime;
	}
	public void setLikeTime(Date likeTime) {
		this.likeTime = likeTime;
	}
	public LikePo(Integer userId, Integer likeUserId, Date likeTime) {
		super();
		this.userId = userId;
		this.likeUserId = likeUserId;
		this.likeTime = likeTime;
	}
	@Override
	public String toString() {
		return "LikePo [id=" + id + ", userId=" + userId + ", likeUserId=" + likeUserId + ", likeTime=" + likeTime
				+ "]";
	}
	
	
	
}
