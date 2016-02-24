package com.qiangu.keyu.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.qiangu.keyu.po.UserPo;

public interface UserService {

	public UserPo getLoginOrRegisterUserInfo(Map<String,String[]> parameters);
	
	public List<UserPo> getChatUserInfo(List<Map> chatList);
	
	public UserPo getUserByUserId(Integer userId);
	
	public Serializable addUser(UserPo user);
	
	/**
	 * 获取主页匹配的用户信息
	 * @param lng
	 * @param lat
	 * @param userId
	 * @return
	 */
	public List<UserPo> getMainUser(Double lng,Double lat,Integer userId,Integer maxDistance,long minOnlineTime,long maxOnlineTime);
	
	public UserPo getOpenAppUser(Integer userId,Double lng,Double lat);
	
	//测试用
	public Object addUserLoc(Integer userId,Double lng,Double lat,Integer type);
}
