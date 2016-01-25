package com.qiangu.keyu.service;

import java.util.Map;

public interface PictureService {

	public String updateUserAvatar(Map<String,String> parameters,Map<String,byte[]> fileContents);
}
