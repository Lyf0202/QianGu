package com.qiangu.keyu.po;

import java.util.Date;

/**
 * 聊天信息表
 * @author lyf
 *
 */
public class ChatPo {

	public ChatPo() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//A用户id
	private Integer userAId;
	//B用户id
	private Integer userBId;
	//开始聊天时间
	private Date startTime;
	//是否已经开始聊天
	private Integer isStartChat;
	//结束聊天时间
	private Date endTime;
	//删除聊天的用户id
	private Integer deleteUserId;
	//A聊天页面里显示的亲密度
	private Double intimacyA;
	//B聊天页面里显示的亲密度
	private Double intimacyB;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserAId() {
		return userAId;
	}
	public void setUserAId(Integer userAId) {
		this.userAId = userAId;
	}
	public Integer getUserBId() {
		return userBId;
	}
	public void setUserBId(Integer userBId) {
		this.userBId = userBId;
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
	public Double getIntimacyB() {
		return intimacyB;
	}
	public void setIntimacyB(Double intimacyB) {
		this.intimacyB = intimacyB;
	}
	@Override
	public String toString() {
		return "ChatPo [id=" + id + ", userAId=" + userAId + ", userBId=" + userBId + ", startTime=" + startTime
				+ ", isStartChat=" + isStartChat + ", endTime=" + endTime + ", deleteUserId=" + deleteUserId
				+ ", intimacyA=" + intimacyA + ", intimacyB=" + intimacyB + "]";
	}
	
	
	
}
