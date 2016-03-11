package com.qiangu.keyu.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qiangu.keyu.controller.Values;
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

	
	String getUserByDistanceHql2 = 
			"select distinct U "
			+ "from UserPo as U "
			+ "where U.id in (:distanceId) "
			+ "and U.lastOnlineTime > :minOnlineTime "
			+ "and U.lastOnlineTime < :maxOnlineTime "
			+ "and U.sex = :sex "
			+ "and U.id not in "
			+ "(select L.userId from LikePo as L where L.likeUserId = :userId) "
			+ "order by U.lastOnlineTime desc";
	@Override
	public List<UserPo> getUserByDistance(List<Integer> distanceId, long minOnlineTime, long maxOnlineTime,
			Integer userId,Integer sex,Integer selectNum,Integer firstSelectNum) {
		Query query = getSession().createQuery(getUserByDistanceHql2);
		query.setParameterList("distanceId",distanceId);
		query.setParameter("minOnlineTime", minOnlineTime);
		query.setParameter("maxOnlineTime", maxOnlineTime);
		query.setParameter("sex",sex);
		query.setParameter("userId",userId);
		query.setFirstResult(firstSelectNum);
		query.setMaxResults(selectNum);
		return query.list();
	}

	String getUserByLikeUserIdHql = 
			"select distinct U "
			+ "from UserPo as U ,LikePo as L "
			+ "where U.id = L.userId "
			+ "and U.sex = :sex "
			+ "and L.likeUserId = :likeUserId "
			+ "and L.isSuccess = :notLike "
			+ "and L.likeTime < :likeTime "
			+ "order by L.likeTime desc";
	String getUserByLikeUserIdHql1 = "select distinct U from UserPo as U ,LikePo as L where U.id = L.userId and L.likeUserId = :likeUserId and L.isSuccess = :notLike and L.likeTime > :lastOnlineTime order by likeTime";
	@Override
	public List<UserPo> getUserByLikeUserId(Integer userId, Long liketime,Integer sex,Integer selectNum,Integer firstSelectNum) {
		Query query = getSession().createQuery(getUserByLikeUserIdHql);
		System.out.println("sex "+sex);
		System.out.println("likeUserId "+userId);
		System.out.println("Values.notLike "+Values.notLike);
		System.out.println("liketime "+liketime);
		System.out.println("firstSelectNum "+firstSelectNum);
		System.out.println("selectNum "+selectNum);
		
		query.setParameter("sex", sex);
		query.setParameter("likeUserId", userId);
		query.setParameter("notLike",Values.notLike);
		query.setParameter("likeTime", liketime);
		query.setFirstResult(firstSelectNum);
		query.setMaxResults(selectNum);
		return query.list();
	}

	//未给定位的用户获取用户
	String getUserBySchoolHql = "select distinct U "
							+ "form UserPo as U "
							+ "where U.id != :userId "
							+ "and U.schoolId = :schoolId "
							+ "and U.sex = :sex "
							+ "and U.lastOnlineTime < :maxLastOnlineTime "
							+ "order by U.lastOnlineTime desc";
//	String getUserBySchoolHql = "select distinct U from UserPo as U where U.schoolId = :schoolId and U.lastOnlineTime > :minOnlineTime and U.lastOnlineTime < :maxOnlineTime and U.id not in(select L.userId from LikePo as L where L.likeUserId = :userId)";
	@Override
	public List<UserPo> getUserBySchool(Integer userId,Integer schoolId,long maxLastOnlineTime,Integer sex,Integer selectNum,Integer firstSelectNum) {
		Query query = getSession().createQuery(getUserBySchoolHql);
		query.setParameter("userId", userId);
		query.setParameter("schoolId", schoolId);
		query.setParameter("sex", sex);
		query.setParameter("maxLastOnlineTime",maxLastOnlineTime);
		query.setFirstResult(firstSelectNum);
		query.setMaxResults(selectNum);
		return query.list();
	}

	//获取那些没给定位的用户
	String getUserBySchoolHql2 = "select distinct U "
								+ "from UserPo as U "
								+ "where U.id in (:userIds) "
								+ "and U.id != :userId "
								+ "and U.schoolId = :schoolId "
								+ "and U.sex = :sex "
								+ "and U.lastOnlineTime < :maxLastOnlineTime "
								+ "order by U.lastOnlineTime desc";
	@Override
	public List<UserPo> getUserBySchool(Integer schoolId, List<Integer> userIds, long maxLastOnlineTime,
			Integer userId, Integer sex, Integer selectNum, Integer firstSelectNum) {
		Query query = getSession().createQuery(getUserBySchoolHql2);
		query.setParameterList("userIds", userIds);
		query.setParameter("userId", userId);
		query.setParameter("schoolId", schoolId);
		query.setParameter("sex", sex);
		query.setParameter("maxLastOnlineTime", maxLastOnlineTime);
		query.setFirstResult(firstSelectNum);
		query.setMaxResults(selectNum);
		return query.list();
	}

	String updateLastOnlineTimeHql = "update UserPo "
							+ "set lastOnlineTime = :lastOnlineTime "
							+ "where id = :userId";
	@Override
	public Integer updateLastOnlineTime(Integer userId) {
		Query query = getSession().createQuery(updateLastOnlineTimeHql);
		query.setParameter("lastOnlineTime", System.currentTimeMillis());
		query.setParameter("userId", userId);
		return query.executeUpdate();
	}

}
