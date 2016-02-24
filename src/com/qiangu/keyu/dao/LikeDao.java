package com.qiangu.keyu.dao;

import java.util.List;

import com.qiangu.keyu.po.LikePo;

public interface LikeDao extends BaseDao<LikePo>{

	public List<LikePo> getLikeUserByLikedUserId(Integer likedUserId);
	
	public List<Integer> getLikeUserIdByLikedUserId(Integer likedUserId); 
}
