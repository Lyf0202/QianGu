package com.qiangu.keyu.service;

import java.util.Map;

import com.qiangu.keyu.po.PicturePo;

public interface PictureService {
	
	public String addAvatar(Integer userId ,String avatarName,byte[] avatarContent);
	
	public PicturePo getAvatar(Integer userId);
	
	public void getpicturePo();
}
