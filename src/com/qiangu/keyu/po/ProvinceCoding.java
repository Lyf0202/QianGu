package com.qiangu.keyu.po;

/**
 * 省份编码表
 * @author lyf
 *
 */
public class ProvinceCoding {

	public ProvinceCoding() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//省份id
	private String provinceId;
	//省份名称
	private String province;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Override
	public String toString() {
		return "ProvinceCoding [id=" + id + ", provinceId=" + provinceId + ", province=" + province + "]";
	}
	
	
}
