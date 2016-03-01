package com.qiangu.keyu.service;

import java.io.Serializable;
import java.util.Date;
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
	
	public Map<String,Object> getMainUserByDistance(Integer userId,Double lng, Double lat,Integer distance,
			long onlineTime,Integer sex,long openTime);
	
	public Map<String,Object> getMainUserByLike(Integer userId,Long likeTime,Integer sex);
	
	public UserPo getOpenAppUser(Integer userId,Double lng,Double lat);
	
	public List<UserPo> getMainUserBySchool(Integer userId,Integer schoolId,long minOnlineTime, long maxOnlineTime);
	
	public Map<Integer,Map<String,Object>> getUserLoc(Integer userId);
	
	public void addUserLoc(Integer userId,Double lng,Double lat);
	
	//测试用
	public Object addUserLoc(Integer userId,Double lng,Double lat,Integer type);
}
