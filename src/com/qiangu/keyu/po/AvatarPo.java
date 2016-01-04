package com.qiangu.keyu.po;

/**
 * 用户头像表
 * @author lyf
 *
 */
public class AvatarPo {

	public AvatarPo() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//用户id
	private Integer userId;
	//头像图片id
	private Integer pictureId;
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
	@Override
	public String toString() {
		return "AvatarPo [id=" + id + ", userId=" + userId + ", pictureId=" + pictureId + "]";
	}
	
	
}
