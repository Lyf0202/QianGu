package com.qiangu.keyu.dao;

import com.qiangu.keyu.po.UserPo;

public interface UserUpdateDao extends BaseDao<UserPo> {

	/**
	 * 更新用户名
	 * @param name
	 * @param id
	 * @return
	 */
	public Integer updateName(String name,Integer id);
	
	
	public Integer updateHeight(Double height,Integer userId);
	
	public Integer updateWeight(Double weight,Integer userId);
	
	public Integer updateBirthday(String birthday,Integer userId);
}
