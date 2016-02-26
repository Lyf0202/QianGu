package com.qiangu.keyu.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
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

	String getUserByDistanceHql = "select U from UserPo as U where U.id in (:distanceId) and U.id not in (:likeUserId) and U.lastOnlineTime > :minOnlineTime and U.lastOnlineTime < :maxOnlineTime";
	@Override
	public List<UserPo> getUserByDistance(List<Integer> distanceId, List<Integer> likeUserId,long minOnlineTime,long maxOnlineTime) {
		Query query = getSession().createQuery(getUserByDistanceHql);
		query.setParameterList("distanceId", distanceId);
		query.setParameterList("likeUserId", likeUserId);
		query.setParameter("minOnlineTime", minOnlineTime);
		query.setParameter("maxOnlineTime", maxOnlineTime);
		List<UserPo> l = query.list();
		return l;
	}

	String getUserByDistanceHql2 = "select distinct U from UserPo as U ,LikePo as L where U.id in (:distanceId) and U.lastOnlineTime > :minOnlineTime and U.lastOnlineTime < :maxOnlineTime and U.id != L.userId and L.likeUserId = :userId";
	@Override
	public List<UserPo> getUserByDistance(List<Integer> distanceId, long minOnlineTime, long maxOnlineTime,
			Integer userId) {
		Query query = getSession().createQuery(getUserByDistanceHql2);
		query.setParameterList("distanceId",distanceId);
		query.setParameter("minOnlineTime", minOnlineTime);
		query.setParameter("maxOnlineTime", maxOnlineTime);
		query.setParameter("userId",userId);
		return query.list();
	}

}
