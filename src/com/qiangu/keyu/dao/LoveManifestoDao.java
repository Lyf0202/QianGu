package com.qiangu.keyu.dao;

import java.util.List;

import com.qiangu.keyu.po.LoveManifestoPo;

public interface LoveManifestoDao extends BaseDao<LoveManifestoPo>{

	public LoveManifestoPo getLoveManifestoById(Integer id);
	
	public LoveManifestoPo getLoveManifestoByUserId(Integer userId);
}
