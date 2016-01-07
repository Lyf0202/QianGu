package com.qiangu.keyu.dao;

import com.qiangu.keyu.po.CitiesCoding;
import com.qiangu.keyu.po.ProvinceCoding;
import com.qiangu.keyu.po.SchoolCoding;
import com.qiangu.keyu.po.SchoolTypeCoding;

public interface TestDao {

	public void addProvince(ProvinceCoding provinceCoding);
	
	public ProvinceCoding getProvince(Integer id);
	
	public CitiesCoding getCity(Integer id);
	
	public SchoolCoding getSchool(Integer id);
	
	public SchoolTypeCoding getSchoolType(Integer id);
	
	public void addSchoolType(SchoolTypeCoding schoolTypeCoding);
}
