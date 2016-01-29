package com.qiangu.keyu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.dao.LabelDao;
import com.qiangu.keyu.po.LabelPo;
import com.qiangu.keyu.service.LabelService;

@Service
public class LabelServiceImpl implements LabelService {

	@Autowired
	private LabelDao labelDao;
	
	
	@Override
	public List<LabelPo> getLabels(Integer userId) {
		return labelDao.getLabelsById(userId);
	}

}
