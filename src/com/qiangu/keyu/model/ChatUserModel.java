package com.qiangu.keyu.model;

import java.util.Date;

public class ChatUserModel {

	private Integer id;
	private Integer userId;
	private Date startTime;
	private Integer isStartChat;
	private Date endTime;
	private Integer deleteUserId;
	private Double intimacyA; 
	
	public ChatUserModel() {
		// TODO Auto-generated constructor stub
	}

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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getIsStartChat() {
		return isStartChat;
	}

	public void setIsStartChat(Integer isStartChat) {
		this.isStartChat = isStartChat;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getDeleteUserId() {
		return deleteUserId;
	}

	public void setDeleteUserId(Integer deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	public Double getIntimacyA() {
		return intimacyA;
	}

	public void setIntimacyA(Double intimacyA) {
		this.intimacyA = intimacyA;
	}

	public ChatUserModel(Integer id, Integer userId, Date startTime, Integer isStartChat, Date endTime,
			Integer deleteUserId, Double intimacyA) {
		super();
		this.id = id;
		this.userId = userId;
		this.startTime = startTime;
		this.isStartChat = isStartChat;
		this.endTime = endTime;
		this.deleteUserId = deleteUserId;
		this.intimacyA = intimacyA;
	}

	@Override
	public String toString() {
		return "ChatUserModel [id=" + id + ", userId=" + userId + ", startTime=" + startTime + ", isStartChat="
				+ isStartChat + ", endTime=" + endTime + ", deleteUserId=" + deleteUserId + ", intimacyA=" + intimacyA
				+ "]";
	}

	
	
	
}
