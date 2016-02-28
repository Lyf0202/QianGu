package com.qiangu.keyu.service;

import java.util.List;
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
	public List<Object> updateLabel(Integer userId ,String[] oldLabel, String[] officialLabels,String[] userLabels);
	
	public Boolean updateLikeUserOrder(Integer userId,Integer likeUserOrder);
}
