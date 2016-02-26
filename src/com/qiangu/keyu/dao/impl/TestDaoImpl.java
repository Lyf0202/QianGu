package com.qiangu.keyu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

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
		String hql = "select U from UserPo as U where U.id in (:ids) and U.lastOnlineTime > :t";
		List<Integer> ids = new ArrayList<>();
//		for(int i = 1;i<15;i++){
//			ids.add(i);
//		}
		ids.add(9);
		ids.add(2);
		ids.add(12);
		long t = System.currentTimeMillis() - 1800*1000;
		Query query = getSession().createQuery(hql);
		query.setParameterList("ids",ids);
		query.setParameter("t", t);
		return query.list();
	}

	@Override
	public void getLike() {
		LikePo l  = (LikePo)getSession().get(LikePo.class,1);
		Date d = l.getLikeTime();
		String hql = "from LikePo where likeTime > :f and likeTime < :l";
		Query query = getSession().createQuery(hql);
		query.setParameter("f",d);
		query.setParameter("l",new Date());
		List<LikePo> list = query.list();
		System.out.println("list.size() = "+list.size());
		for(LikePo like : list){
			System.out.println(like);
		}
	}

}
