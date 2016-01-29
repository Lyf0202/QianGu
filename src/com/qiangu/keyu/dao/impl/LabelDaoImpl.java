package com.qiangu.keyu.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.dao.LabelDao;
import com.qiangu.keyu.po.LabelPo;

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

}
