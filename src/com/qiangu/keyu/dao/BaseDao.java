package com.qiangu.keyu.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface BaseDao<T> {

	public void setSessionFactory(SessionFactory sessionFactory);
	
	public Session getSession();
	
	public T getT(Class<T> c,Serializable id);
	
	public Serializable save(T t);
	
	public Integer update(String hql,Map<String , Object> params);
	
	public T getT(String hql,Map<String , Object> params);
	
	public List<T> findTs(String hql , Map<String , Object> params);
}
