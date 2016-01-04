package com.qiangu.keyu.po;

/**
 * 举报图片表
 * @author lyf
 *
 */
public class InformPicturePo {

	public InformPicturePo() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//举报信息id
	private Integer informId;
	//举报图片id
	private Integer pictureId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getInformId() {
		return informId;
	}
	public void setInformId(Integer informId) {
		this.informId = informId;
	}
	public Integer getPictureId() {
		return pictureId;
	}
	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}
	@Override
	public String toString() {
		return "InformPicturePo [id=" + id + ", informId=" + informId + ", pictureId=" + pictureId + "]";
	}
	
	
}
