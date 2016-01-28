package com.qiangu.keyu.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qiangu.keyu.dao.UserDao;
import com.qiangu.keyu.po.UserPo;

@Repository
public class UserDaoImpl extends BaseDaoImpl<UserPo> implements UserDao{

	String getUserByTelephoneHql = "from UserPo as U where U.telephone = :telephone";
	@Override
	public UserPo getUserByTelephone(String telephone) {
		Map<String , Object> params =  new HashMap<>();
		params.put("telephone", telephone);
		return getT(getUserByTelephoneHql, params);
	}
	
	String getChatUsersByIdsHql = "select UserPo from userPo where UserPo.id = :userId1 || UserPo.id = :userId2 ||UserPo.id = :userId3";
	@Override
	public List<UserPo> getChatUsersByIds(List<Integer> list) {
		Map<String ,Object> params = new HashMap<>();
		for(int i = 0 ; i < list.size() ; i++){
			int num = i + 1;
			params.put("userId" + num , list.get(i));
		}
		
		return findTs(getChatUsersByIdsHql, params);
	}
	
	@Override
	public UserPo getUserByUserId(Integer userId) {
		
		return getT(UserPo.class, userId);
	}

}
