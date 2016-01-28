package com.qiangu.keyu.service;

import java.util.List;
import java.util.Map;

import com.qiangu.keyu.po.LabelPo;

public interface LabelService {

	public List<LabelPo> getLabels(Map<String , String[]> parameters);
}
