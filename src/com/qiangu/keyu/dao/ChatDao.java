package com.qiangu.keyu.dao;

import java.util.List;

import com.qiangu.keyu.po.ChatPo;

public interface ChatDao extends BaseDao<ChatPo>{

	public List<ChatPo> getChatUsersById(Integer userId);
	
}
