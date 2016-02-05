package com.qiangu.keyu.po;

/**
 * 城市编码表
 * 
 * @author lyf
 *
 */
public class CitiesCoding {

	public CitiesCoding() {
		// TODO Auto-generated constructor stub
	}

	// id
	private Integer id;
	// 城市名
	private String city;
	// 城市id
	private String cityId;
	// 所属省份id
	private String provinceId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	@Override
	public String toString() {
		return "CitiesCoding [id=" + id + ", city=" + city + ", cityId=" + cityId + ", provinceId=" + provinceId + "]";
	}

}
