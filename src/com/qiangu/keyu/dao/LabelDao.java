package com.qiangu.keyu.dao;

import java.util.List;

import com.qiangu.keyu.po.LabelPo;

public interface LabelDao extends BaseDao<LabelPo>{

	public List<LabelPo> getLabelsById(Integer userId);
}
