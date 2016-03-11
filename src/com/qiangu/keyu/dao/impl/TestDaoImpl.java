package com.qiangu.keyu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.dao.TestDao;
import com.qiangu.keyu.model.ChatUserModel;
import com.qiangu.keyu.po.AreasCoding;
import com.qiangu.keyu.po.CitiesCoding;
import com.qiangu.keyu.po.LikePo;
import com.qiangu.keyu.po.ProvinceCoding;
import com.qiangu.keyu.po.SchoolCoding;
import com.qiangu.keyu.po.SchoolTypeCoding;
import com.qiangu.keyu.po.UserPo;

@Repository
public class TestDaoImpl extends BaseDaoImpl<UserPo> implements TestDao {

	@Override
	public ProvinceCoding getProvince(Integer id) {
		// TODO Auto-generated method stub
		return (ProvinceCoding) getSession().get(ProvinceCoding.class, id);
	}

	@Override
	public void addProvince(ProvinceCoding provinceCoding) {
		// TODO Auto-generated method stub
		getSession().save(provinceCoding);
	}

	@Override
	public CitiesCoding getCity(Integer id) {
		// TODO Auto-generated method stub
		return (CitiesCoding) getSession().get(CitiesCoding.class, id);
	}

	@Override
	public SchoolCoding getSchool(Integer id) {
		// TODO Auto-generated method stub
		return (SchoolCoding) getSession().get(SchoolCoding.class, id);
	}

	@Override
	public SchoolTypeCoding getSchoolType(Integer id) {
		// TODO Auto-generated method stub
		return (SchoolTypeCoding) getSession().get(SchoolTypeCoding.class, id);
	}

	@Override
	public void addSchoolType(SchoolTypeCoding schoolTypeCoding) {
		// TODO Auto-generated method stub
		getSession().save(schoolTypeCoding);
	}

	@Override
	public void addUser(UserPo userPo) {
		// TODO Auto-generated method stub
		getSession().save(userPo);
	}

	@Override
	public List<ChatUserModel> getChatUser() {
		String hql = "select new Map(id as I,userBId as u,startTime as s,isStartChat,endTime,deleteUserId,intimacyA) from ChatPo where userAId = :userAId and endTime = null";
		Query query = getSession().createQuery(hql);
		query.setInteger("userAId", 1);
		List<Map> l = query.list();
		System.out.println(l.size());
		for(Map c : l){
			for(Object keySet : c.keySet()){
				System.out.println(keySet + "--"+c.get(keySet));
			}
		}
		return null;
	}

	@Override
	public AreasCoding getAreaCoding(Integer id) {
		
		return (AreasCoding) getSession().get(AreasCoding.class, id);
	}

	@Override
	public List<UserPo> getMapUser() {
		String hql = "select new Map(id as chatId,userBId as userId,startTime as startChatDate,isStartChat as hasChat,endTime as endTime,deleteUserId as deleteUserId,intimacyA as userIntimacy,intimacyB as chatUserIntimacy) from ChatPo "
				+ "where (userAId = :userAId and endTime = null) or "
				+ "(userBId = :userBId and endTime = null)";
		Query query = getSession().createQuery(hql);
		query.setParameter("userAId", -12);
		query.setParameter("userBId", -12);
		List<Map> list = query.list();
		System.out.println("------------- "+list.size());
		for(Map m : list){
			System.out.println(m.get(Keys.userId));
		}
		return null;
	}

	@Override
	public void getLike() {
		
	}

	String hql1 = "update UserPo "
				+ "set lastOnlineTime = :lastOnlineTime "
				+ "where id = :userId";
	@Override
	public void updateLastOnlinetime(long time,Integer userId) {
		Query query = getSession().createQuery(hql1);
		query.setParameter("lastOnlineTime", time);
		query.setParameter("userId", userId);
		query.executeUpdate();
		
	}

	String hql2 = "delete from LikePo "
			+ "where id <= :last and id >= :first";
	@Override
	public void updateResetLikePo(Integer first, Integer last) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery(hql2);
		query.setParameter("last", last);
		query.setParameter("first", first);
		query.executeUpdate();
	}

	String hql3 = "delete from ChatPo "
			+ "where id <= :last and id >= :first";
	@Override
	public void updateResetChatPo(Integer first, Integer last) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery(hql3);
		query.setParameter("last", last);
		query.setParameter("first", first);
		query.executeUpdate();
	}

}
