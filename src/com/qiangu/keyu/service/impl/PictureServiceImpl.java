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
import com.qiangu.keyu.po.UserPo;
import com.qiangu.keyu.service.PictureService;
import com.qiangu.keyu.service.UserService;

@Service
public class PictureServiceImpl implements PictureService {

	@Autowired
	private PictureDao pictureDao;

	@Autowired
	private UserUpdateDao userUpdateDao;
	@Autowired
	private QiNiuYunApi qiniuApi;
	
	@Autowired
	private UserService userService;


	/**
	 * 向数据库中添加头像
	 */
	@Override
	public String addAvatar(Integer userId, String avatarName, byte[] avatarContent) {
		if (qiniuApi.pictureUpload(avatarName, avatarContent)) {
			PicturePo picture = new PicturePo();
			picture.setPictureName(avatarName);
			pictureDao.addPicture(picture);
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
	public PicturePo getAvatar(Integer userId) {
		
		return pictureDao.getUserAvatar(userId);
	}


	/**
	 * 上传学生证或毕业证进行身份认证
	 * 学生证或毕业证以 手机号 + _IDVerifyPicture_ + 上传次数  命名存储
	 * eg. 18857111234_IDVerifyPicture_1
	 */
	@Override
	public String addIDVerifyPicture(Integer userId,Map<String,byte[]> fileContents) {
		UserPo user = userService.getUserByUserId(userId);
		Integer iDVerifyPicId = user.getIDVerifyPicId();
		String iDVerifyPictureName = null;
		//判断是否是第一次上传
		if(iDVerifyPicId == null){
			iDVerifyPictureName = user.getTelephone() + Values.IDVerifyPicture + "1";
		}else{
			//否   
			//根据上次图片名得到图片命名编号
			PicturePo picture = pictureDao.getT(PicturePo.class,iDVerifyPicId);
			String pictureName = picture.getPictureName();
			Integer beginIndex = pictureName.lastIndexOf('_');
			Integer iDVerifyPicNum = Integer.valueOf(pictureName.substring(beginIndex+1, pictureName.length()));
			
			//获取新的图片命名
			iDVerifyPictureName = user.getTelephone()+Values.IDVerifyPicture + (iDVerifyPicNum + 1);	
			
		}
		PicturePo iDVerifyPicture = new PicturePo();
		iDVerifyPicture.setPictureName(iDVerifyPictureName);
		
		boolean uploadResult = qiniuApi.pictureUpload(iDVerifyPictureName, fileContents.get(Keys.iDVerifyPicture));
		if(uploadResult){
			//上传成功 存储到数据库
			pictureDao.save(iDVerifyPicture);
			//更新用户表
			user.setIDVerifyPicId(iDVerifyPicture.getId());
			return Values.yes;
		}else{
			return Values.no;
		}
	}

}
