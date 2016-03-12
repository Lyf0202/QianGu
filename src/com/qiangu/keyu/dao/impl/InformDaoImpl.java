package com.qiangu.keyu.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qiangu.keyu.dao.InformDao;
import com.qiangu.keyu.po.InformPicturePo;
import com.qiangu.keyu.po.InformPo;
import com.qiangu.keyu.po.PicturePo;

@Repository
public class InformDaoImpl extends BaseDaoImpl<InformPo> implements InformDao{

	@Override
	public void addInformPicture(InformPicturePo informPicturePo) {
		getSession().save(informPicturePo);
	}

	String getLastInformPictureHql = "select P "
							+ "from PicturePo as P "
							+ "where P.id = "
							+ "(select max(IP.pictureId) "
							+ "from InformPo as I , "
							+ "InformPicturePo as IP "
							+ "where I.id = IP.informId "
							+ "and I.informUserId = :userId)";
	@Override
	public PicturePo getLastInformPicture(Integer userId) {
		Query query = getSession().createQuery(getLastInformPictureHql);
		query.setParameter("userId", userId);
		return (PicturePo) query.uniqueResult();
	}


}
