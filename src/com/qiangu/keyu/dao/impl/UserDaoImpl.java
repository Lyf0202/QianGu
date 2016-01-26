package com.qiangu.keyu.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qiangu.keyu.dao.UserDao;
import com.qiangu.keyu.po.UserPo;

@Repository
public class UserDaoImpl extends BaseDaoImpl<UserPo> implements UserDao{

	String getUserByTelephoneHql = "select UserPo from UserPo where UserPo.telephone = :telephone";
	@Override
	public UserPo getUserByTelephone(String telephone) {
		Map<String , Object> params =  new HashMap<>();
		params.put("telephone", telephone);
		return getT(getUserByTelephoneHql, params);
	}

}
