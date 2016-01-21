package com.qiangu.keyu.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.dao.UserUpdateDao;
import com.qiangu.keyu.service.UserUpdateService;

@Service
public class UserUpdateServiceImpl implements UserUpdateService {

	@Autowired
	private UserUpdateDao userUpdateDao;
	
	@Override
	public Boolean updateName(Map<String, String[]> parameters) {
		String name = parameters.get(Keys.name)[0];
		Integer id = Integer.valueOf(parameters.get(Keys.id)[0]);
		Integer daoResult = userUpdateDao.updateName(name, id);
		if(daoResult > 0){
			return true;
		}
		return false;
	}

}
