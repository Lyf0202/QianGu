package com.qiangu.keyu.dao.impl;

import java.util.List;

import com.qiangu.keyu.dao.ChatDao;
import com.qiangu.keyu.po.ChatPo;

public class ChatDaoImpl extends BaseDaoImpl<ChatPo> implements ChatDao{

	String getChatUsersByIdHql = "select ChatPo from ChatPo where ";
	@Override
	public List<ChatPo> getChatUsersById(Integer userId) {
		
		return null;
	}

}
