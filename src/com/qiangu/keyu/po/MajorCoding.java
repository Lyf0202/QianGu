package com.qiangu.keyu.po;

/**
 * 专业编码表
 * @author lyf
 *
 */
public class MajorCoding {

	public MajorCoding() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//专业名称
	private String name;
	//所属学院id
	private Integer instituteId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(Integer instituteId) {
		this.instituteId = instituteId;
	}
	@Override
	public String toString() {
		return "MajorCoding [id=" + id + ", name=" + name + ", instituteId=" + instituteId + "]";
	}
	
	
}
