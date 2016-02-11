package com.qiangu.keyu.dao;

import java.io.Serializable;
import java.util.List;

import com.qiangu.keyu.po.LabelPo;
import com.qiangu.keyu.po.UserLabelPo;

public interface LabelDao extends BaseDao<LabelPo> {

	public List<LabelPo> getLabelsById(Integer userId);
	
	public Integer updateOldLabel(List<Integer> ids,Integer userId);
	
	public Serializable addUserLabel(UserLabelPo userLabelPo);
}
