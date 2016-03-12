package com.qiangu.keyu.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.api.QiNiuYunApi;
import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.dao.InformDao;
import com.qiangu.keyu.dao.PictureDao;
import com.qiangu.keyu.po.InformPicturePo;
import com.qiangu.keyu.po.InformPo;
import com.qiangu.keyu.po.PicturePo;
import com.qiangu.keyu.po.UserPo;
import com.qiangu.keyu.service.InformService;
import com.qiangu.keyu.service.PictureService;
import com.qiangu.keyu.service.UserService;
@Service
public class InformServiceImpl implements InformService {

	@Autowired
	private QiNiuYunApi qiniuYunApi;
	@Autowired
	private InformDao informDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PictureDao pictureDao;
	
	@Override
	public void addUserInform(Integer userId, Integer informedUserId, String informMessage,
			Map<String, byte[]> fileContents) {
		UserPo user = userService.getUserByUserId(userId);
		
		InformPo informPo = new InformPo();
		informPo.setInformedUserId(informedUserId);
		informPo.setInformTime(new Date());
		informPo.setInformUserId(userId);
		informPo.setMessage(informMessage);
		
		informDao.save(informPo);
		Integer pictureNumber = 0;
		
		PicturePo picturePo = informDao.getLastInformPicture(userId);
		
		if (picturePo != null) {
			String pictureName = picturePo.getPictureName();
			pictureNumber = Integer.valueOf(pictureName.substring(pictureName.lastIndexOf("_") + 1, pictureName.length()));
		}
		for(int i = 0 ; i < fileContents.size() ; i++){
			pictureNumber ++;
			String newPictureName = user.getTelephone() + Values.informPicture + pictureNumber;
			if (qiniuYunApi.pictureUpload(newPictureName, fileContents.get(Keys.informPicture+i))) {
				PicturePo newPicturePo = new PicturePo();
				newPicturePo.setPictureName(newPictureName);
				pictureDao.save(newPicturePo);
				InformPicturePo informPicturePo = new InformPicturePo();
				informPicturePo.setInformId(informPo.getId());
				informPicturePo.setPictureId(newPicturePo.getId());
				informDao.addInformPicture(informPicturePo);
			}
		}
	}

}
