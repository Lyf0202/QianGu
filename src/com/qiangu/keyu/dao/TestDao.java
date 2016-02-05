package com.qiangu.keyu.dao;

import java.util.List;

import com.qiangu.keyu.model.ChatUserModel;
import com.qiangu.keyu.po.AreasCoding;
import com.qiangu.keyu.po.CitiesCoding;
import com.qiangu.keyu.po.ProvinceCoding;
import com.qiangu.keyu.po.SchoolCoding;
import com.qiangu.keyu.po.SchoolTypeCoding;
import com.qiangu.keyu.po.UserPo;

public interface TestDao extends BaseDao<UserPo>{

	public void addProvince(ProvinceCoding provinceCoding);
	
	public ProvinceCoding getProvince(Integer id);
	
	public CitiesCoding getCity(Integer id);
	
	public SchoolCoding getSchool(Integer id);
	
	public SchoolTypeCoding getSchoolType(Integer id);
	
	public void addSchoolType(SchoolTypeCoding schoolTypeCoding);
	
	public void addUser(UserPo userPo);
	
	public List<ChatUserModel> getChatUser();
	
	public AreasCoding getAreaCoding(Integer id);
}
