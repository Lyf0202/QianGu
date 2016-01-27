package com.qiangu.keyu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.dao.ChatDao;
import com.qiangu.keyu.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatDao chatDao;
	
	@Override
	public List<Map> getChatInfo(Map<String, String[]> parameters) {
		Integer userId = Integer.valueOf(parameters.get(Keys.userId)[0]);
		return chatDao.getChatUsersById(userId);
	}

}
