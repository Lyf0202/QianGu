package com.qiangu.keyu.po;

/**
 * 学院编码表
 * @author lyf
 *
 */
public class InstituteCoding {

	public InstituteCoding() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//学院名
	private String name;
	//所属学校id
	private Integer schoolId;
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
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	@Override
	public String toString() {
		return "InstituteCoding [id=" + id + ", name=" + name + ", schoolId=" + schoolId + "]";
	}
	
	
}
