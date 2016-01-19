package com.qiangu.keyu.dao;

import java.util.List;

import com.qiangu.keyu.po.SchoolCoding;

public interface SchoolDao {

	public List<String> getSchool(String province);
	
	public List<SchoolCoding> getSchoolByProvinceId(Integer provinceId);
	
	public SchoolCoding getSchoolById(Integer id);
}
