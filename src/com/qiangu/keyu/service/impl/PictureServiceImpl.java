package com.qiangu.keyu.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.api.QiNiuYunApi;
import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.dao.PictureDao;
import com.qiangu.keyu.dao.UserUpdateDao;
import com.qiangu.keyu.po.AvatarPo;
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
	public String addAvatar(Integer userId, String avatarName, byte[] avatarContent) {
		if (qiniuApi.pictureUpload(avatarName, avatarContent)) {
			PicturePo picture = new PicturePo();
			picture.setPictureName(avatarName);
			pictureDao.addPicture(picture);
			System.out.println("picture.getId() = " + picture.getId());
			AvatarPo avatar = new AvatarPo();
			avatar.setUserId(userId);
			avatar.setPictureId(picture.getId());
			pictureDao.addAvatar(avatar);
			return Values.yes;
		}else{
			return Values.no;
		}
	}


	@Override
	public void getpicturePo() {
		// TODO Auto-generated method stub
		System.out.println(pictureDao.getUserAvatar(27));
	}

}
