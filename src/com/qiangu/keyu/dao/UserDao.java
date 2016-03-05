package com.qiangu.keyu.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.qiangu.keyu.po.UserPo;

public interface UserDao extends BaseDao<UserPo>{

	public UserPo getUserByTelephone(String telephone);
	
	public List<UserPo> getChatUsersByIds(List<Integer> list);
	
	public UserPo getUserByUserId(Integer userId);
	
	/**
	 * 根据距离和喜欢获取用户列表
	 * @param distanceId
	 * @param likeUserId
	 * @return
	 */
	public List<UserPo> getUserByDistance(List<Integer> distanceId,List<Integer> likeUserId,long minOnlineTime,long maxOnlineTime);
	
	public List<UserPo> getUserByDistance(List<Integer> distanceId,long minOnlineTime,long maxOnlineTime,Integer userId ,Integer sex,Integer selectNum,Integer firstSelectNum);
	
	public List<UserPo> getUserByLikeUserId(Integer userId,Long liketime,Integer sex,Integer selectNum,Integer firstSelectNum);
	
	/**
	 * 根据学校和喜欢获取用户列表
	 */
	public List<UserPo> getUserBySchool(Integer userId,Integer schoolId,long maxLastOnlineTime,Integer sex,Integer selectNum,Integer firstSelectNum);
	
	public List<UserPo> getUserBySchool(Integer schoolId,List<Integer> userIds,long maxLastOnlineTime,Integer userId ,Integer sex,Integer selectNum,Integer firstSelectNum);
}
