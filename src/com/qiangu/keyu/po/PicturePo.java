package com.qiangu.keyu.po;

/**
 * 图片信息表
 * @author lyf
 *
 */
public class PicturePo {

	public PicturePo() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//图片名称
	private String pictureName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	@Override
	public String toString() {
		return "PicturePo [id=" + id + ", pictureName=" + pictureName + "]";
	}
	
	
}
