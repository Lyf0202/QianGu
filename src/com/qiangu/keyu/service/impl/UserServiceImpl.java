package com.qiangu.keyu.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.dao.MongodbDao;
import com.qiangu.keyu.dao.UserDao;
import com.qiangu.keyu.po.UserPo;
import com.qiangu.keyu.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MongodbDao mongodbDao;
	
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
		List<Map<String,Object>> list = mongodbDao.findByDistance(8000, -73.84, 40.79);
		if(list.size() == 0){
			System.out.println("no user");
		}else{
			for(Map<String,Object> m : list){
				System.out.println("userId = "+m.get(Keys.userId));
			}
		}
		return null;
	}

}
