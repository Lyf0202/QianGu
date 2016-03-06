package com.qiangu.keyu.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qiangu.keyu.dao.AreaDao;
import com.qiangu.keyu.po.AreasCoding;

@Repository
public class AreaDaoImpl extends BaseDaoImpl<AreasCoding> implements AreaDao{

	
	String getHometownHql = "select new Map(A.area as area,C.city as city,P.province as province) "
						+ "from AreasCoding as A ,CitiesCoding as C,ProvinceCoding as P "
						+ "where A.areaId = :areaId "
						+ "and A.cityId = C.cityId "
						+ "and C.provinceId = P.provinceId";
	@Override
	public Map getHometown(String areaId) {
		Query query = getSession().createQuery(getHometownHql);
		query.setParameter("areaId", areaId);
		return (Map) query.uniqueResult();
	}

	
}
