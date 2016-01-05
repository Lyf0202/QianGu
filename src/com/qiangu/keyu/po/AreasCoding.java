package com.qiangu.keyu.po;


/**
 * 区县编码表
 * @author lyf
 *
 */
public class AreasCoding {

	public AreasCoding() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//区县名
	private String area;
	//区县id
	private String areaId;
	//所属城市id
	private String cityId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	@Override
	public String toString() {
		return "AreasCoding [id=" + id + ", area=" + area + ", areaId=" + areaId + ", cityId=" + cityId + "]";
	}
	
	
	
}
