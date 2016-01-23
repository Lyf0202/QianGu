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
	
	/**
	 * 更新用户头像id
	 * @param avatarId
	 * @param id
	 * @return
	 */
	public Integer updateAvatarId(Integer avatarId,Integer id);
}
