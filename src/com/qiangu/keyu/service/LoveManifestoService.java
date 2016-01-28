package com.qiangu.keyu.service;

import java.util.Map;

import com.qiangu.keyu.po.LoveManifestoPo;

public interface LoveManifestoService {

	public LoveManifestoPo getLoveManifestoPo(Map<String , String[]> parameters);
	
	public LoveManifestoPo getLoveManifestoPoByUserId(Integer userId);
}
