package com.qiangu.keyu.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.api.QiNiuYunApi;
import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.dao.PictureDao;
import com.qiangu.keyu.dao.UserUpdateDao;
import com.qiangu.keyu.po.AvatarPo;
import com.qiangu.keyu.po.PicturePo;
import com.qiangu.keyu.service.UserUpdateService;

@Service
public class UserUpdateServiceImpl implements UserUpdateService {

	@Autowired
	private UserUpdateDao userUpdateDao;
	
	@Autowired
	private PictureDao pictureDao;
	
	@Autowired
	private QiNiuYunApi qiNiuYunAPI;
	
	@Override
	public Boolean updateName(Map<String, String[]> parameters) {
		String name = parameters.get(Keys.name)[0];
		Integer userId = Integer.valueOf(parameters.get(Keys.userId)[0]);
		Integer daoResult = userUpdateDao.updateName(name, userId);
		if(daoResult > 0){
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateAvatar(Integer userId, byte[] fileContent) {
		PicturePo p = pictureDao.getUserAvatar(userId);
		String newAvatarName = null;
		if(p != null){
			newAvatarName = getNewAvatarName(p.getPictureName());
			PicturePo np = new PicturePo();
			np.setPictureName(newAvatarName);
			pictureDao.save(np);
			AvatarPo avatar = new AvatarPo();
			avatar.setUserId(userId);
			avatar.setPictureId(np.getId());
			pictureDao.addAvatar(avatar);
			qiNiuYunAPI.pictureUpload(newAvatarName, fileContent);
			return true;
		}else{
			return false;
		}
	}
	
	public String getNewAvatarName(String oldAvatarName) {
		String[] str = oldAvatarName.split("_");
		int avatarNum = Integer.valueOf(str[1]);
		String newAvatarName = str[0] + "_" + ++avatarNum;
		return newAvatarName;
	}

	@Override
	public Boolean updateHeight(Integer userId, Double newHeight) {
		Integer daoResult = userUpdateDao.updateHeight(newHeight, userId);
		if(daoResult > 0){
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateBirthday(Integer userId, String newBirthday) {
		Integer daoResult = userUpdateDao.updateBirthday(newBirthday, userId);
		if(daoResult > 0){
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateWeight(Integer userId, Double newWeight) {
		Integer daoResult = userUpdateDao.updateWeight(newWeight, userId);
		if(daoResult > 0){
			return true;
		}
		return false;
	}

}
