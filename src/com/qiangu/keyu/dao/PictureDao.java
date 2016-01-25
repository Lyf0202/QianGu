package com.qiangu.keyu.dao;

import com.qiangu.keyu.po.PicturePo;

public interface PictureDao extends BaseDao<PicturePo> {

	public PicturePo getUserAvatar(Integer userId);
}
