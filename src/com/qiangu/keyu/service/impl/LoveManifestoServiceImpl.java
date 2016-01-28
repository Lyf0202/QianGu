package com.qiangu.keyu.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.dao.LoveManifestoDao;
import com.qiangu.keyu.po.LoveManifestoPo;
import com.qiangu.keyu.service.LoveManifestoService;

@Service
public class LoveManifestoServiceImpl implements LoveManifestoService {

	@Autowired
	private LoveManifestoDao loveManifestoDao;
	
	@Override
	public LoveManifestoPo getLoveManifestoPo(Map<String, String[]> parameters) {
		Integer userId = Integer.valueOf(parameters.get(Keys.userId)[0]);
		return loveManifestoDao.getLoveManifestoByUserId(userId);
	}

	@Override
	public LoveManifestoPo getLoveManifestoPoByUserId(Integer userId) {
		
		return loveManifestoDao.getLoveManifestoByUserId(userId);
	}

}
