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
	 * 根据userId获取队列里面已经匹配到的信息
	 * @param userId
	 * @return
	 */
	public List<Map> getNewChatUserById(Integer userId);
	/**
	 * 根据userId获取该用户当前正在聊天的ChatPo
	 * @param userId
	 * @return
	 */
	public List<ChatPo> getChatPo(Integer userId);
	
	/**
	 * 
	 * @param userId
	 * @param chatUserId
	 * @return
	 */
	public Integer deleteChatUser(Integer userId,Integer chatUserId);
	
	/**
	 * 
	 * @param isUserA
	 * @param chatId
	 * @param userId
	 * @param userIntimacy
	 * @param chatUserIntimacy
	 * @return
	 */
	public Integer updateIntimacy(Integer isUserA,Integer chatId,Integer userId,Double userIntimacy,Double chatUserIntimacy);
	
	/**
	 * 
	 * @param chatId
	 * @return
	 */
	public Integer deleteChatForNotStartChat(Integer chatId);
	
	public Integer updateForStartChat(Integer chatId);
}
