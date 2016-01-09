package com.qiangu.keyu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.api.BaiduMapApi;
import com.qiangu.keyu.controller.ParametersValues;
import com.qiangu.keyu.dao.SchoolDao;
import com.qiangu.keyu.service.SchoolService;

import net.sf.json.JSONObject;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolDao schoolDao;
	@Autowired
	private BaiduMapApi baiduMapApi;
	@Autowired
	private ParametersValues parametersValues;
	
	/**
	 * 根据经纬度定位大学
	 */
	@Override
	public List<String> getLocationSchool(Map<String, String> parameters) {
		String lat = parameters.get(parametersValues.lat);
		String lng = parameters.get(parametersValues.lng);
		//根据经纬度获取用户所在省份
		String province = baiduMapApi.getProvince(lat, lng);
		//获取该省份所有的大学
		List<String> school = schoolDao.getSchool(province);
		//获取以该经纬度为中心方圆2000内所有的大学
		JSONObject jsonObject = baiduMapApi.getBaiduSchool(lat, lng);
		return locationSchool(school, jsonObject);
	}
	
	/**
	 * 规范化定位的大学列表
	 * @param schools
	 * @param jsonObject
	 * @return
	 */
	public List<String> locationSchool(List<String> schools,JSONObject jsonObject){
		List<JSONObject> schoolJSON = jsonObject.getJSONArray(baiduMapApi.results);
		List<String> locationSchools = new ArrayList<String>();
		for(JSONObject json : schoolJSON){
			for(String school : schools){
				if(json.getString("name").contains(school) && !locationSchools.contains(school)){
					locationSchools.add(school);
					break;
				}
			}
		}
		return locationSchools;
	}
	 

}
