package com.qiangu.keyu.service;

import com.qiangu.keyu.po.CitiesCoding;
import com.qiangu.keyu.po.ProvinceCoding;
import com.qiangu.keyu.po.SchoolCoding;
import com.qiangu.keyu.po.SchoolTypeCoding;
import com.qiangu.keyu.po.UserPo;

public interface TestService {

	public void addProvince(ProvinceCoding provinceCoding);
	
	public ProvinceCoding getProvince(Integer id);
	
	public CitiesCoding getCity(Integer id);
	
	public SchoolCoding getSchool(Integer id);
	
	public SchoolTypeCoding getSchoolType(Integer id);
	
	public void addSchoolType(SchoolTypeCoding schoolTypeCoding);
	
	public void addUser(UserPo userPo);
	
	public void testXml();
	
	public void addTestBaseDao();
	
	public void getChatUserModel();
	

}
