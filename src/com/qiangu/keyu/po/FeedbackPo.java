package com.qiangu.keyu.po;

import java.util.Date;

/**
 * 用户反馈表
 * @author lyf
 *
 */
public class FeedbackPo {

	public FeedbackPo() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//反馈用户id
	private Integer userId;
	//反馈内容
	private String content;
	//反馈时间
	private Date feedbackTime;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getFeedbackTime() {
		return feedbackTime;
	}
	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
	@Override
	public String toString() {
		return "FeedbackPo [id=" + id + ", userId=" + userId + ", content=" + content + ", feedbackTime=" + feedbackTime
				+ "]";
	}
	
	
}
