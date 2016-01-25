package com.qiangu.keyu.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.api.QiNiuYunApi;
import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.dao.PictureDao;
import com.qiangu.keyu.dao.UserUpdateDao;
import com.qiangu.keyu.po.PicturePo;
import com.qiangu.keyu.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {

	@Autowired
	private PictureDao pictureDao;
	
	@Autowired
	private UserUpdateDao userUpdateDao;
	@Autowired
	private QiNiuYunApi qiniuApi;
	@Override
	public String updateUserAvatar(Map<String, String> parameters,Map<String,byte[]> fileContents) {
		Integer userId = Integer.valueOf(parameters.get(Keys.userId));
		PicturePo p = pictureDao.getUserAvatar(userId);
		String newAvatarName = getNewAvatarName(p.getPictureName());
		if(qiniuApi.pictureUpload(newAvatarName, fileContents.get(Keys.avatar))){
			PicturePo newP = new PicturePo();
			newP.setPictureName(newAvatarName);
			pictureDao.save(newP);
			System.out.println("newP.getId() = "+newP.getId());
			userUpdateDao.updateAvatarId(newP.getId(), userId);
		}
		
		return null;
	}

	public String getNewAvatarName(String oldAvatarName){
		String[] str = oldAvatarName.split("_");
		int avatarNum = Integer.valueOf(str[1]);
		String newAvatarName = str[0] + "_"+ ++avatarNum;
		return newAvatarName;
	}
	
}
