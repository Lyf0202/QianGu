package com.qiangu.keyu.dao;

import com.qiangu.keyu.po.InformPicturePo;
import com.qiangu.keyu.po.InformPo;
import com.qiangu.keyu.po.PicturePo;

public interface InformDao extends BaseDao<InformPo>{

	public void addInformPicture(InformPicturePo informPicturePo);
	
	public PicturePo getLastInformPicture(Integer userId);
}
