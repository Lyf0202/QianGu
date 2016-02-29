package com.qiangu.keyu.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qiangu.keyu.dao.PictureDao;
import com.qiangu.keyu.po.AvatarPo;
import com.qiangu.keyu.po.PicturePo;

@Repository
public class PictureDaoImpl extends BaseDaoImpl<PicturePo> implements PictureDao{

	/**
	 * 
	 */
	String getUserAvatarHql = "select P from PicturePo as P ,AvatarPo as A where A.pictureId = P.id and A.id = (select max(AP.id) from AvatarPo as AP where AP.userId = :userId)";
	@Override
	public PicturePo getUserAvatar(Integer userId) {
		Map<String , Object> params = new HashMap<>();
		params.put("userId",userId);
		PicturePo p = getT(getUserAvatarHql, params);
		return p;
	}
	
	@Override
	public Serializable addAvatar(AvatarPo avatar) {
	
		return getSession().save(avatar);
	}

	@Override
	public Serializable addPicture(PicturePo picture) {
		
		return save(picture);
	}

}
