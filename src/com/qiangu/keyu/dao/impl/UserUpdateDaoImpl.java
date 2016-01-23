package com.qiangu.keyu.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qiangu.keyu.dao.UserUpdateDao;
import com.qiangu.keyu.po.UserPo;

@Repository
public class UserUpdateDaoImpl extends BaseDaoImpl<UserPo> implements UserUpdateDao{

	String hqlUpdateName = "update UserPo set name = :name where id = :id";
	@Override
	public Integer updateName(String name, Integer id) {
		Map<String,Object> params = new HashMap<>();
		params.put("name", name);
		params.put("id", id);
		return update(hqlUpdateName, params);
	}
	
	String hqlUpdateAvatarId = "update UserPo set avatarId = :avatarId where id = :id";
	@Override
	public Integer updateAvatarId(Integer avatarId, Integer id) {
		Map<String,Object> params = new HashMap<>();
		params.put("avatarId", avatarId);
		params.put("id", id);
		return update(hqlUpdateAvatarId, params);
	}
	
}
