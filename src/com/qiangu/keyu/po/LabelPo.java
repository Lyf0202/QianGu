package com.qiangu.keyu.po;

/**
 * 标签表
 * @author lyf
 *
 */
public class LabelPo {

	
	public LabelPo() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//标签类型id
	private Integer typeId;
	//标签内容
	private String labelContent;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getLabelContent() {
		return labelContent;
	}
	public void setLabelContent(String labelContent) {
		this.labelContent = labelContent;
	}
	@Override
	public String toString() {
		return "LabelPo [id=" + id + ", typeId=" + typeId + ", labelContent=" + labelContent + "]";
	}
	
	
}
