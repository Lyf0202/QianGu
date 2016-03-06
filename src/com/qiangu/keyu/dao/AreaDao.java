package com.qiangu.keyu.dao;

import java.util.List;
import java.util.Map;

import com.qiangu.keyu.po.AreasCoding;

public interface AreaDao extends BaseDao<AreasCoding>{

	public Map getHometown(String areaId);
}
