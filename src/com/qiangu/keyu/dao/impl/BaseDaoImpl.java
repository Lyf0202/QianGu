package com.qiangu.keyu.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qiangu.keyu.dao.BaseDao;

@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T getT(Class<T> c, Serializable id) {
		// TODO Auto-generated method stub
		Session session = getSession();
		System.out.println("session = "+session);
		return (T) session.get(c,id);
	}

	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
		getSession().save(t);
	}

	@Override
	public Integer update(String hql, Map<String, Object> params) {
		Integer updateNum = null;
		Query query = getSession().createQuery(hql);
		if(params != null && !params.isEmpty()){
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		updateNum = query.executeUpdate();
		return updateNum;
	}

	@Override
	public T getT(String hql, Map<String, Object> params) {
		Query query = getSession().createQuery(hql);
		if(params != null && !params.isEmpty()){
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		T t = (T) query.uniqueResult();
		
		return t;
	}

	@Override
	public List<T> findTs(String hql, Map<String, Object> params) {
		Query query = getSession().createQuery(hql);
		if(params != null && !params.isEmpty()){
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		List<T> list = query.list();
		if(list != null && list.size() != 0){
			return list;
		}
		return null;
	}
}
