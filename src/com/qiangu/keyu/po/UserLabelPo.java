package com.qiangu.keyu.po;

/**
 * 用户标签表
 * @author lyf
 *
 */
public class UserLabelPo {

	public UserLabelPo() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//用户id
	private Integer userId;
	//标签id
	private Integer labelId;
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
	public Integer getLabelId() {
		return labelId;
	}
	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}
	public Integer getIsNow() {
		return isNow;
	}
	public void setIsNow(Integer isNow) {
		this.isNow = isNow;
	}
	@Override
	public String toString() {
		return "UserLabelPo [id=" + id + ", userId=" + userId + ", labelId=" + labelId + ", isNow=" + isNow + "]";
	}
	
	
}
