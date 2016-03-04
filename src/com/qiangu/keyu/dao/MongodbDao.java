package com.qiangu.keyu.dao;

import java.util.List;
import java.util.Map;

import com.mongodb.WriteResult;

public interface MongodbDao {

	public WriteResult updateOrInsert(Integer userId,Double lng,Double lat);
	
	public Map<Integer,Map<String,Object>> findByDistance(Integer minDistance,Integer maxDistance,Double lng,Double lat);
	
	public Map<Integer,Map<String,Object>> findByArray(List<Integer> userIds);
	
	public Map<Integer,Map<String,Object>> findByUserId(Integer userId);
	
	public Map<Integer,Map<String,Object>> findByNoLocAndSchool();
}
