package com.qiangu.keyu.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qiangu.keyu.dao.TestDao;
import com.qiangu.keyu.model.ChatUserModel;
import com.qiangu.keyu.po.CitiesCoding;
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

}
