package com.qiangu.keyu.po;

import java.util.Date;

/**
 * 用户举报表
 * 
 * @author lyf
 *
 */
public class InformPo {

	public InformPo() {
		// TODO Auto-generated constructor stub
	}

	// id
	private Integer id;
	// 举报用户id
	private Integer informUserId;
	// 被举报用户id
	private Integer informedUserId;
	// 举报信息
	private String message;
	// 举报时间
	private Date informTime;
	// 举报结果
	private Integer informResult;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInformUserId() {
		return informUserId;
	}

	public void setInformUserId(Integer informUserId) {
		this.informUserId = informUserId;
	}

	public Integer getInformedUserId() {
		return informedUserId;
	}

	public void setInformedUserId(Integer informedUserId) {
		this.informedUserId = informedUserId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getInformTime() {
		return informTime;
	}

	public void setInformTime(Date informTime) {
		this.informTime = informTime;
	}

	public Integer getInformResult() {
		return informResult;
	}

	public void setInformResult(Integer informResult) {
		this.informResult = informResult;
	}

	@Override
	public String toString() {
		return "InformPo [id=" + id + ", informUserId=" + informUserId + ", informedUserId=" + informedUserId
				+ ", message=" + message + ", informTime=" + informTime + ", informResult=" + informResult + "]";
	}

}
