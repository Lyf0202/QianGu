package com.qiangu.keyu.dao;

import java.util.List;
import java.util.Map;

import com.qiangu.keyu.po.ChatPo;

public interface ChatDao extends BaseDao<ChatPo>{

	/**
	 * 根据userId获取该用户当前正在聊天的信息
	 * @param userId
	 * @return
	 */
	public List<Map> getChatUsersById(Integer userId);
	
	/**
	 * 根据userId获取该用户当前正在聊天的ChatPo
	 * @param userId
	 * @return
	 */
	public List<ChatPo> getChatPo(Integer userId);
	
	public Integer deleteChatUser(Integer userId,Integer chatUserId);
}
