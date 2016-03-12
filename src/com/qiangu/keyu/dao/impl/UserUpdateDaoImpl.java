package com.qiangu.keyu.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
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
	
	String hqlUpdateHeight = "update UserPo set height = :height where id = :userId";
	@Override
	public Integer updateHeight(Double height, Integer userId) {
		Map<String , Object> params = new HashMap<>();
		params.put("height", height);
		params.put("userId", userId);
		return update(hqlUpdateHeight, params);
	}
	
	String hqlUpdateWeight = "update UserPo set weight = :weight where id = :userId";
	@Override
	public Integer updateWeight(Double weight, Integer userId) {
		Map<String , Object> params = new HashMap<>();
		params.put("weight", weight);
		params.put("userId", userId);
		return update(hqlUpdateWeight, params);
	}
	
	String hqlUpdateBirthday = "update UserPo set birthday = :birthday where id = :userId";
	@Override
	public Integer updateBirthday(String birthday, Integer userId) {
		Map<String , Object> params = new HashMap<>();
		params.put("birthday", birthday);
		params.put("userId", userId);
		return update(hqlUpdateBirthday, params);
	}
	
	String hqlUpdateHometown = "update UserPo set countyId = :countyId where id = :userId";
	@Override
	public Integer updateHometown(Integer areaId, Integer userId) {
		Map<String , Object> params = new HashMap<>();
		params.put("countyId", areaId);
		params.put("userId", userId);
		return update(hqlUpdateHometown, params);
	}
	
	String updateLikeUserOrderHql = "update UserPo set likeUserOrder = :likeUserOrder where id = :userId";
	@Override
	public Integer updateLikeUserOrder(Integer likeUserOrder, Integer userId) {
		Map<String,Object> params = new HashMap<>();
		params.put("likeUserOrder", likeUserOrder);
		params.put("userId", userId);
		return update(updateLikeUserOrderHql, params);
	}
	
	String updateUserStateHql = "update UserPo "
							+ "set isLogin = :state "
							+ "where id = :userId";
	@Override
	public Integer updateUserState(Integer userId, Integer state) {
		Query query = getSession().createQuery(updateUserStateHql);
		query.setParameter("state", state);
		query.setParameter("userId", userId);
		return query.executeUpdate();
	}
	
	
	
}
