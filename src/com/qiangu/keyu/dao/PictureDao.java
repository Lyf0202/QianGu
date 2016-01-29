package com.qiangu.keyu.dao;

import java.io.Serializable;

import com.qiangu.keyu.po.AvatarPo;
import com.qiangu.keyu.po.PicturePo;

public interface PictureDao extends BaseDao<PicturePo> {

	public PicturePo getUserAvatar(Integer userId);
	
	public Serializable addAvatar(AvatarPo avatar);
	
	public Serializable addPicture(PicturePo picture);
}
