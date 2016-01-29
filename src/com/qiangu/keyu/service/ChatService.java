package com.qiangu.keyu.service;

import java.util.List;
import java.util.Map;

import com.qiangu.keyu.po.ChatPo;

public interface ChatService {

	public List<Map> getChatInfo(Integer userId);
	
	public List<ChatPo> getChat(Integer userId);
}
