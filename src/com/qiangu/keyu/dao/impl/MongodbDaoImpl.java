package com.qiangu.keyu.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;
import com.qiangu.keyu.api.MongodbApi;
import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.dao.MongodbDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository
public class MongodbDaoImpl implements MongodbDao {

	@Autowired
	private MongodbApi mongodbApi;
	
	@Override
	public WriteResult updateOrInsert(Integer userId, Double lng, Double lat) {
		BasicDBObject updateCondition = new BasicDBObject();
		updateCondition.put(MongodbApi.userId, userId);
		BasicDBObject updateValue = new BasicDBObject();
		Double[] d = {lng,lat};
		JSONObject json = new JSONObject();
		json.put(MongodbApi.type,MongodbApi.Point);
		json.put(MongodbApi.coordinates, d);
		updateValue.put(MongodbApi.loc, json);
		return mongodbApi.mongodbUpdateOrInsert(updateCondition, updateValue);
	}

	
	/**
	 * db.places.find( { loc :
     *                   { $near :
     *                     { $geometry :
     *                        { type : "Point" ,
     *                          coordinates : [ <longitude> , <latitude> ] } ,
     *                       $maxDistance : <distance in meters>,
     *                       $minDistance : <distance in meters>
     *                } } } )
	 */
	@Override
	public Map<Integer,Map<String,Object>> findByDistance(Integer minDistance,Integer maxDistance, Double lng, Double lat) {
		
		//$geometry
		BasicDBObject geovalueB = new BasicDBObject();
		geovalueB.put(MongodbApi.type,MongodbApi.Point);
		geovalueB.put(MongodbApi.coordinates, new Double[] {lng,lat});
		
		//$near
		BasicDBObject nearB = new BasicDBObject();
		nearB.put(MongodbApi.geometry, geovalueB);
		nearB.put(MongodbApi.maxDistance,maxDistance);
		nearB.put(MongodbApi.minDistance,minDistance);
		
		//loc
		BasicDBObject searchB = new BasicDBObject().append(MongodbApi.loc, 
				new BasicDBObject().append(MongodbApi.near, nearB));
		
		DBCursor dbCursor = mongodbApi.mongodbFind(searchB);
		Map<Integer,Map<String,Object>> userM = new HashMap<>();
		while(dbCursor.hasNext()){
			Map<String,Object> m = new HashMap<>();
			JSONObject json = JSONObject.fromObject(dbCursor.next().toString());
			m.put(Keys.userId,json.get(MongodbApi.userId));
			JSONObject jsonLoc = (JSONObject) json.get(MongodbApi.loc);
			JSONArray jsonArray = jsonLoc.getJSONArray(MongodbApi.coordinates);
			m.put(Keys.lng,jsonArray.get(0));
			m.put(Keys.lat,jsonArray.get(1));
			userM.put((Integer)json.get(MongodbApi.userId), m);
		}
		return userM;
	}


	@Override
	public Map<Integer, Map<String, Object>> findByArray(List<Integer> userIds) {
		
		BasicDBObject inB = new BasicDBObject();
		inB.put(MongodbApi.in, userIds);
		
		BasicDBObject searchB = new BasicDBObject();
		searchB.put(MongodbApi.userId, inB);

		DBCursor dbCursor = mongodbApi.mongodbFind(searchB);
		Map<Integer,Map<String,Object>> userM = new HashMap<>();
		while(dbCursor.hasNext()){
			Map<String,Object> m = new HashMap<>();
			JSONObject json = JSONObject.fromObject(dbCursor.next().toString());
			m.put(Keys.userId,json.get(MongodbApi.userId));
			JSONObject jsonLoc = (JSONObject) json.get(MongodbApi.loc);
			JSONArray jsonArray = jsonLoc.getJSONArray(MongodbApi.coordinates);
			m.put(Keys.lng,jsonArray.get(0));
			m.put(Keys.lat,jsonArray.get(1));
			userM.put((Integer)json.get(MongodbApi.userId), m);
		}
		return userM;
	}

}
