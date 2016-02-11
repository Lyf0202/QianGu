package com.qiangu.keyu.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.dao.LabelDao;
import com.qiangu.keyu.po.LabelPo;
import com.qiangu.keyu.po.UserLabelPo;

@Repository
public class LabelDaoImpl extends BaseDaoImpl<LabelPo> implements LabelDao{

	String getLabelsByIdHql = "select L from LabelPo as L ,UserLabelPo as U where L.id = U.labelId and U.userId = :userId and U.isNow = :isNow";
	@Override
	public List<LabelPo> getLabelsById(Integer userId) {
		Map<String,Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("isNow", Values.isNow);	
		return findTs(getLabelsByIdHql, params);
	}
	
	String updateOldLabelHql = "update UserLabelPo as U set U.isNow = :isNow where U.userId = :userId and U.labelId in (:labelIds)";
	@Override
	public Integer updateOldLabel(List<Integer> ids, Integer userId) {
		Query query = getSession().createQuery(updateOldLabelHql);
		query.setParameter("isNow", Values.notIsNow);
		query.setParameter("userId", userId);
		query.setParameterList("labelIds", ids);
		return query.executeUpdate();
	}
	@Override
	public Serializable addUserLabel(UserLabelPo userLabelPo) {
		
		return getSession().save(userLabelPo);
	}

}
