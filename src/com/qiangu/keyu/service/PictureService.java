package com.qiangu.keyu.service;

import java.util.Map;

public interface PictureService {

	public String updateUserAvatar(Map<String,String> parameters,Map<String,byte[]> fileContents);
	
	public String addAvatar(Integer userId ,String avatarName,byte[] avatarContent);
}
