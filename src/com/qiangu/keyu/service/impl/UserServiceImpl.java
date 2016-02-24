package com.qiangu.keyu.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.dao.LikeDao;
import com.qiangu.keyu.dao.MongodbDao;
import com.qiangu.keyu.dao.UserDao;
import com.qiangu.keyu.po.LikePo;
import com.qiangu.keyu.po.UserPo;
import com.qiangu.keyu.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MongodbDao mongodbDao;
	
	@Autowired
	private LikeDao likeDao;
	
	@Override
	public UserPo getLoginOrRegisterUserInfo(Map<String, String[]> parameters) {
		String telephone = parameters.get(Keys.telephone)[0];
		
		UserPo user = userDao.getUserByTelephone(telephone);
		return user;
	}

	@Override
	public List<UserPo> getChatUserInfo(List<Map> chatList) {
		List<Integer>  list = new ArrayList<>();
		for(Map m : chatList){
			list.add((Integer) m.get(Keys.userId));
		}
		for(int i = list.size() ; i < Values.chatNum - 1; i++){
			list.add(null);
		}
		return userDao.getChatUsersByIds(list);
	}

	@Override
	public UserPo getUserByUserId(Integer userId) {
		
		return userDao.getUserByUserId(userId);
	}

	@Override
	public Serializable addUser(UserPo user) {
		return userDao.save(user);		
	}

	@Override
	public UserPo getOpenAppUser(Integer userId,Double lng,Double lat) {
//		mongodbDao.updateOrInsert(userId, lng, lat);
		//11182
		mongodbDao.updateOrInsert(-1,-73.97,40.77);
		//3550
		mongodbDao.updateOrInsert(-2, -73.88,40.78);
		//6742
		mongodbDao.updateOrInsert(-3,-73.92,40.79);
		return null;
	}

	@Override
	public Object addUserLoc(Integer userId, Double lng, Double lat,Integer type) {
		Integer distance = 0;
		if(type == 1){
			distance = 3000;
		}else if(type == 2){
			distance = 5000;
		}else if(type == 3){
			distance = 10000;
		}else if(type == 4){
			distance = 15000;
		}
		String str = "result = ";
		List<Map<String,Object>> list = mongodbDao.findByDistance(distance, -73.84, 40.79);
		if(list.size() == 0){
			str = "no user";
		}else{
			for(Map<String,Object> m : list){
				str += m.get(Keys.userId) + " ";
			}
		}
		return str;
	}

	@Override
	public List<UserPo> getMainUser(Double lng, Double lat, Integer userId,Integer maxDistance,long minOnlineTime,long maxOnlineTime) {
		List<Map<String,Object>> distanceUser = mongodbDao.findByDistance(maxDistance, lng, lat);
		List<Integer> distanceId = new ArrayList<>();
		for(Map<String,Object> m : distanceUser){
			distanceId.add((Integer)m.get(Keys.userId));
		}
		List<Integer> likeUserId = likeDao.getLikeUserIdByLikedUserId(userId);
		List<UserPo> listUser = userDao.getUserByDistance(distanceId, likeUserId,minOnlineTime,maxOnlineTime);
		Map<Integer,UserPo> mapUser = new HashMap<>();
		for(UserPo u : listUser){
			mapUser.put(u.getId(),u);
		}
		
		return null;
	}

}
