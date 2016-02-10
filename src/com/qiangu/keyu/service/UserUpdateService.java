package com.qiangu.keyu.service;

import java.util.Map;

public interface UserUpdateService {

	public Boolean updateAvatar(Integer userId,byte[] fileContent);
//	
	public Boolean updateLoveManifesto(Integer userId ,String loveManifesto);
	
	public Boolean updateName(Map<String,String[]> parameters);
	
	public Boolean updateHometown(Integer userId,Integer areaId);
//	
	public Boolean updateBirthday(Integer userId,String newBirthday);
//	
//	public String updateInstitute();
//	
	public Boolean updateWeight(Integer userId,Double newWeight);
//	
	public Boolean updateHeight(Integer userId,Double newHeight);
//	
//	public String updateLabel();
}
