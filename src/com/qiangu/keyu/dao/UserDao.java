package com.qiangu.keyu.dao;

import java.util.List;

import com.qiangu.keyu.po.UserPo;

public interface UserDao extends BaseDao<UserPo>{

	public UserPo getUserByTelephone(String telephone);
	
	public List<UserPo> getChatUsersByIds(List<Integer> list);
	
	public UserPo getUserByUserId(Integer userId);
}
