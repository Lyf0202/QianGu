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
}
