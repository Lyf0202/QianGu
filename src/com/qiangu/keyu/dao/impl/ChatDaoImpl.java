package com.qiangu.keyu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.dao.ChatDao;
import com.qiangu.keyu.po.ChatPo;

@Repository
public class ChatDaoImpl extends BaseDaoImpl<ChatPo> implements ChatDao {

	String getChatUsersByIdHql = "select new Map(id as chatId,userBId as userId,startTime as startChatDate,isStartChat as hasChat,endTime as endTime,deleteUserId as deleteUserId,intimacyA as userIntimacy,intimacyB as chatUserIntimacy) from ChatPo "
			+ "where userAId = :userAId "
			+ "and endTime = null "
			+ "and startTime != null";
	String getChatUsersByIdHql2 = "select new Map(id as chatId,userAId as userId,startTime as startChatDate,isStartChat as hasChat,endTime as endTime,deleteUserId as deleteUserId,intimacyB as userIntimacy,intimacyA as chatUserIntimacy) from ChatPo "
			+ "where userBId = :userBId "
			+ "and endTime = null "
			+ "and startTime != null";
	@Override
	public List<Map> getChatUsersById(Integer userId) {
		Query query = getSession().createQuery(getChatUsersByIdHql);
		query.setInteger("userAId", userId);
		List<Map> list1 = query.list();
		
		query = getSession().createQuery(getChatUsersByIdHql2);
		query.setInteger("userBId", userId);
		List<Map> list2 = query.list();
		
		list1.addAll(list2);
		return list1;
	}
	
	String getNewChatUserByIdHql1 = "select new Map(id as chatId,userBId as userId,startTime as startChatDate,isStartChat as hasChat,endTime as endTime,deleteUserId as deleteUserId,intimacyA as userIntimacy,intimacyB as chatUserIntimacy) from ChatPo "
			+ "where userAId = :userAId "
			+ "and endTime = null "
			+ "and startTime = null";
	String getNewChatUserByIdHql2 = "select new Map(id as chatId,userAId as userId,startTime as startChatDate,isStartChat as hasChat,endTime as endTime,deleteUserId as deleteUserId,intimacyB as userIntimacy,intimacyA as chatUserIntimacy) from ChatPo "
			+ "where userBId = :userBId "
			+ "and endTime = null "
			+ "and startTime = null";
	@Override
	public List<Map> getNewChatUserById(Integer userId){
		Query query = getSession().createQuery(getNewChatUserByIdHql1);
		query.setInteger("userAId", userId);
		List<Map> list1 = query.list();
		
		query = getSession().createQuery(getNewChatUserByIdHql2);
		query.setInteger("userBId", userId);
		List<Map> list2 = query.list();
		
		if (list1.size() == 0 && list2.size() == 0) {
			return list1;
		}else if (list1.size() == 0) {
			return list2;
		}else if (list2.size() == 0) {
			return list1;
		}else {
			return (Integer)list1.get(0).get(Keys.chatId) > (Integer)list2.get(0).get(Keys.chatId) ?
				list2 : list1;
		}
	}

	String hql = "from ChatPo where userAId = :userId and endTime != null and userAId != deleteUserId";
	@Override
	public List<ChatPo> getChatPo(Integer userId) {
		Map<String,Object> params = new HashMap<>();
		params.put("userId", userId);
		return findTs(hql, params);
	}
	
	
	String deleteChatUserHql = "update ChatPo "
							+ "set endTime = :endTime , deleteUserId = :deleteUserId "
							+ "where (userAId = :userId1 "
							+ "and userBId = :chatUserId1) "
							+ "or (userBId = :userId2 "
							+ "and userAId = :chatUserId2)";
	@Override
	public Integer deleteChatUser(Integer userId, Integer chatUserId) {
		Query query = getSession().createQuery(deleteChatUserHql);
		query.setParameter("endTime", new Date());
		query.setParameter("deleteUserId", userId);
		query.setParameter("userId1", userId);
		query.setParameter("chatUserId1", chatUserId);
		query.setParameter("userId2", userId);
		query.setParameter("chatUserId2", chatUserId);
		return query.executeUpdate();
	}
	
	String updateIntimacyHql1 = "update ChatPo "
							+ "set intimacyA = :userIntimacy, "
							+ "intimacyB = :chatUserIntimacy "
							+ "where id = :chatId "
							+ "and userAId = :userId";
	String updateIntimacyHql2 = "update ChatPo "
							+ "set intimacyA = :chatUserIntimacy, "
							+ "intimacyB = :userIntimacy "
							+ "where id = :chatId "
							+ "and userBId = :userId";
	@Override
	public Integer updateIntimacy(Integer isUserA,Integer chatId,Integer userId, Double userIntimacy, Double chatUserIntimacy) {
		Query query ;
		if(isUserA == Values.isUserA){
			query = getSession().createQuery(updateIntimacyHql1);
		}else {
			query = getSession().createQuery(updateIntimacyHql2);
		}
		query.setParameter("userIntimacy", userIntimacy);
		query.setParameter("chatUserIntimacy",chatUserIntimacy );
		query.setParameter("chatId", chatId);
		query.setParameter("userId", userId);
		return query.executeUpdate();
	}
	
	String deleteChatForNotStartChatHql = "update ChatPo "
									+ "set deleteUserId = :deleteUserId, "
									+ "endTime = :endTime "
									+ "where id = :chatId";
	@Override
	public Integer deleteChatForNotStartChat(Integer chatId) {
		Query query = getSession().createQuery(deleteChatForNotStartChatHql);
		query.setParameter("deleteUserId", Values.officeId);
		query.setParameter("endTime", new Date());
		query.setParameter("chatId", chatId);
		return query.executeUpdate();
	}
	
	String updateForStartChatHql = "update ChatPo "
								+ "set isStartChat = :startChat "
								+ "where chatId = :chatId";
	@Override
	public Integer updateForStartChat(Integer chatId) {
		Query query = getSession().createQuery(updateForStartChatHql);
		query.setParameter("startChat", Values.startChat);
		query.setParameter("chatId", chatId);
		return query.executeUpdate();
	}

}
