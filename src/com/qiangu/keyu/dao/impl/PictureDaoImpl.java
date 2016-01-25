package com.qiangu.keyu.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qiangu.keyu.dao.PictureDao;
import com.qiangu.keyu.po.PicturePo;

@Repository
public class PictureDaoImpl extends BaseDaoImpl<PicturePo> implements PictureDao{

	/**
	 * 
	 */
	String getUserAvatarHql = "select PicturePo from Picture as P ,UserPo as U where U.avatarId = P.id and U.id = :id";
	@Override
	public PicturePo getUserAvatar(Integer userId) {
		Map<String , Object> params = new HashMap<>();
		params.put("id",userId);
		PicturePo p = getT(getUserAvatarHql, params);
		return p;
	}

}
