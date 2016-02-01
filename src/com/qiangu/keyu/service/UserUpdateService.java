package com.qiangu.keyu.service;

import java.util.Map;

public interface UserUpdateService {

	public Boolean updateAvatar(Integer userId,byte[] fileContent);
//	
//	public String updateLoveManifesto();
	
	public Boolean updateName(Map<String,String[]> parameters);
	
//	public String updateHometown();
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
