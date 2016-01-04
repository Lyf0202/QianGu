package com.qiangu.keyu.po;

/**
 *标签类型表 
 * @author lyf
 *
 */
public class LabelTypePo {
	
	public LabelTypePo() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//标签类型
	private String type;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "LabelTypePo [id=" + id + ", type=" + type + "]";
	}
	
	
}
