package com.qiangu.keyu.po;

/**
 * 学校类型编码表
 * @author lyf
 *
 */
public class SchoolTypeCoding {

	public SchoolTypeCoding() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//学校类型
	private String schoolTypeName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSchoolTypeName() {
		return schoolTypeName;
	}
	public void setSchoolTypeName(String schoolTypeName) {
		this.schoolTypeName = schoolTypeName;
	}
	@Override
	public String toString() {
		return "SchoolTypeCoding [id=" + id + ", schoolTypeName=" + schoolTypeName + "]";
	}
	
	
}
