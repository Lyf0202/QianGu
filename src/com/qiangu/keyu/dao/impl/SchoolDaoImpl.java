package com.qiangu.keyu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qiangu.keyu.dao.SchoolDao;
import com.qiangu.keyu.po.SchoolCoding;

@Repository
public class SchoolDaoImpl extends BaseDaoImpl implements SchoolDao {

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

}
