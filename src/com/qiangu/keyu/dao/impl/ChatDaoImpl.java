package com.qiangu.keyu.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qiangu.keyu.dao.ChatDao;
import com.qiangu.keyu.po.ChatPo;

@Repository
public class ChatDaoImpl extends BaseDaoImpl<ChatPo> implements ChatDao {

	String getChatUsersByIdHql = "select new Map(id as chatId,userBId as userId,startTime as startChatDate,isStartChat as hasChat,endTime as endTime,deleteUserId as deleteUserId,intimacyA as intimacy) from ChatPo where userAId = :userAId and endTime = null";
	String getChatUsersByIdHql2 = "select new Map(id as chatId,userAId as userId,startTime as startChatDate,isStartChat as hasChat,endTime as endTime,deleteUserId as deleteUserId,intimacyB as intimacy) from ChatPo where userBId = :userBId and endTime = null";

	@Override
	public List<Map> getChatUsersById(Integer userId) {
		Query query = getSession().createQuery(getChatUsersByIdHql);
		query.setInteger("userAId", userId);
		List<Map> list1 = query.list();
		
		query = getSession().createQuery(getChatUsersByIdHql2);
		query.setInteger("userBId", userId);
		List<Map> list2 = query.list();
		for(Map m : list2){
			list1.add(m);
		}
		return list1;
	}

}
