package com.qiangu.keyu.service;

import java.util.Map;

public interface InformService {

	public void addUserInform(Integer userId,Integer informedUserId,String informMessage,Map<String,byte[]> fileContents);
}
