package com.qiangu.keyu.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qiangu.keyu.dao.LoveManifestoDao;
import com.qiangu.keyu.po.LoveManifestoPo;

@Repository
public class LoveManifestoDaoImpl extends BaseDaoImpl<LoveManifestoPo> implements LoveManifestoDao{

	@Override
	public LoveManifestoPo getLoveManifestoById(Integer id) {
		
		return getT(LoveManifestoPo.class, id);
	}

	String getLoveManifestoByUserIdHql = "from LoveManifestoPo as L where L.id = (select max(Lo.id) from LoveManifestoPo as Lo where Lo.userId = :userId)";
	@Override
	public LoveManifestoPo getLoveManifestoByUserId(Integer userId) {
		Map<String,Object> params = new HashMap<>();
		params.put("userId", userId);
		return getT(getLoveManifestoByUserIdHql, params);
	}

}
