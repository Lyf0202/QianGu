package com.qiangu.keyu.dao;

import java.util.List;
import java.util.Map;

import com.qiangu.keyu.po.ChatPo;

public interface ChatDao extends BaseDao<ChatPo>{

	public List<Map> getChatUsersById(Integer userId);
	
}
