package com.qiangu.keyu.dao;

import java.util.Date;
import java.util.List;

import com.qiangu.keyu.po.LikePo;

public interface LikeDao extends BaseDao<LikePo>{

	public List<LikePo> getLikeUserByLikedUserId(Integer likedUserId);
	
	public List<Integer> getLikeUserIdByLikedUserId(Integer likedUserId);
	
	public List<Integer> getLikeUserIdByTime(Integer likedUserId,Date lastOnlineTime);
	
	public LikePo getLikePoByUserIdAndLikeuserId(Integer userId,Integer likeUserId);
	
}
