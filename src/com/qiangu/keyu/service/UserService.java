package com.qiangu.keyu.service;

import java.util.List;
import java.util.Map;

import com.qiangu.keyu.po.UserPo;

public interface UserService {

	public UserPo getLoginOrRegisterUserInfo(Map<String,String[]> parameters);
	
	public List<UserPo> getChatUserInfo(List<Map> chatList);
}
