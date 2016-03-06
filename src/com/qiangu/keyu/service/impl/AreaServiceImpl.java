package com.qiangu.keyu.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.dao.AreaDao;
import com.qiangu.keyu.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaDao areaDao;
	
	@Override
	public Map<String, String> getHometownByAreaId(String areaId) {
		return areaDao.getHometown(areaId);
	}

}
