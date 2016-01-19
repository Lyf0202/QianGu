package com.qiangu.keyu.po;

/**
 * 学校编码表
 * @author lyf
 *
 */
public class SchoolCoding {

	public SchoolCoding() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//学校名称
	private String school_name;
	//学校所在省份id
	private Integer school_pro_id;
	//学校类型id
	private Integer school_schooltype_id;
	//
	private Double lng;
	//
	private Double lat;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public Integer getSchool_pro_id() {
		return school_pro_id;
	}
	public void setSchool_pro_id(Integer school_pro_id) {
		this.school_pro_id = school_pro_id;
	}
	public Integer getSchool_schooltype_id() {
		return school_schooltype_id;
	}
	public void setSchool_schooltype_id(Integer school_schooltype_id) {
		this.school_schooltype_id = school_schooltype_id;
	}
	
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	@Override
	public String toString() {
		return "SchoolCoding [id=" + id + ", school_name=" + school_name + ", school_pro_id=" + school_pro_id
				+ ", school_schooltype_id=" + school_schooltype_id + ", lng=" + lng + ", lat=" + lat + "]";
	}
	
	
	
}
