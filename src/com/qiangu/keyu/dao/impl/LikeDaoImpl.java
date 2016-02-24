package com.qiangu.keyu.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.dao.LikeDao;
import com.qiangu.keyu.po.LikePo;

@Repository
public class LikeDaoImpl extends BaseDaoImpl<LikePo> implements LikeDao{

	
	String getLikeUserByLikedUserIdHql = "from LikePo where likeUserId = :userId and isSuccess = :notLike order by likeTime";
	@Override
	public List<LikePo> getLikeUserByLikedUserId(Integer likedUserId) {
		Map<String,Object> params = new HashMap<>();
		params.put("userId", likedUserId);
		params.put("notLike",Values.notLike);
		return findTs(getLikeUserByLikedUserIdHql, params);
	}
	
	String getLikeUserIdByLikedUserIdHql = "select id from LikePo where likeUserId = :userId and isSuccess = :notLike order by likeTime";
	@Override
	public List<Integer> getLikeUserIdByLikedUserId(Integer likedUserId) {
		Query query = getSession().createQuery(getLikeUserIdByLikedUserIdHql);
		query.setParameter("userId",likedUserId);
		query.setParameter("notLike",Values.notLike);
		return query.list();
	}

}
