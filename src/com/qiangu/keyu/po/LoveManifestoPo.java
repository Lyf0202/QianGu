package com.qiangu.keyu.po;

/**
 * 爱情宣言表
 * @author lyf
 *
 */
public class LoveManifestoPo {

	public LoveManifestoPo() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//用户id
	private Integer userId;
	//爱情宣言
	private String loveManifesto;
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
	public String getLoveManifesto() {
		return loveManifesto;
	}
	public void setLoveManifesto(String loveManifesto) {
		this.loveManifesto = loveManifesto;
	}
	@Override
	public String toString() {
		return "LoveManifestoPo [id=" + id + ", userId=" + userId + ", loveManifesto=" + loveManifesto + "]";
	}
	
	
}
