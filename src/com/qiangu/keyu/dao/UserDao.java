package com.qiangu.keyu.dao;

import com.qiangu.keyu.po.UserPo;

public interface UserDao extends BaseDao<UserPo>{

	public UserPo getUserByTelephone(String telephone);
}
