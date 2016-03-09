package com.qiangu.keyu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.dao.ChatDao;
import com.qiangu.keyu.po.ChatPo;
import com.qiangu.keyu.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatDao chatDao;
	
	@Override
	public List<Map> getChatInfo(Integer userId) {
		return chatDao.getChatUsersById(userId);
	}

	@Override
	public List<ChatPo> getChat(Integer userId) {
		
		return chatDao.getChatPo(userId);
	}

	/**
	 * 更新用户亲密度
	 */
	@Override
	public void updateIntimacy(Integer userId, Integer chatId, Double userIntimacy, Double chatUserIntimacy) {
		ChatPo chat = chatDao.getT(ChatPo.class, chatId);
		if(chat.getUserAId() == userId){
			chat.setIntimacyA(userIntimacy);
			chat.setIntimacyB(chatUserIntimacy);
		}else {
			chat.setIntimacyA(chatUserIntimacy);
			chat.setIntimacyB(userIntimacy);
		}
	}

	/**
	 * 更新因为匹配后24小时未开始聊天
	 */
	@Override
	public Integer deleteChatForNotStartChat(Integer chatId) {
		
		return chatDao.deleteChatForNotStartChat(chatId);
	}

	/**
	 * 开始聊天后更新数据库
	 */
	@Override
	public Integer updateForStartChat(Integer chatId) {
		
		return chatDao.updateForStartChat(chatId);
	}

}
