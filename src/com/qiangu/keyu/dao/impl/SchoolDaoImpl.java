package com.qiangu.keyu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qiangu.keyu.dao.SchoolDao;
import com.qiangu.keyu.po.SchoolCoding;

@Repository
public class SchoolDaoImpl extends BaseDaoImpl<SchoolCoding> implements SchoolDao {

	/**
	 * 根据省份查询学校
	 */
	final String getSchoolHql = "select S.school_name from SchoolCoding as S ,ProvinceCoding as P "
			+ "where S.school_pro_id = P.id and P.province = :province";
	@Override
	public List<String> getSchool(String province) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery(getSchoolHql);
		query.setCacheable(true);
		query.setString("province", province);
		return query.list();
	}
	
	/**
	 * 
	 */
	@Override
	public SchoolCoding getSchoolById(Integer id) {
		// TODO Auto-generated method stub
		return (SchoolCoding) getSession().get(SchoolCoding.class, id);
	}
	
	/**
	 * 
	 */
	final String getSchoolByProvinceIdHql = "from SchoolCoding as S where "
			+ "S.school_pro_id = :school_pro_id and S.lng != null and "
			+ "S.lat != null";
	@Override
	public List<SchoolCoding> getSchoolByProvinceId(Integer provinceId) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery(getSchoolByProvinceIdHql);
		query.setCacheable(true);
		query.setInteger("school_pro_id",provinceId);
		return query.list();
	}


}
