package com.qiangu.keyu.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.api.BaiduMapApi;
import com.qiangu.keyu.api.UtilsApi;
import com.qiangu.keyu.controller.ParametersValues;
import com.qiangu.keyu.dao.SchoolDao;
import com.qiangu.keyu.po.SchoolCoding;
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
	@Autowired
	private UtilsApi utilsApi;
	
	/**
	 * 根据经纬度定位大学
	 * @throws IOException 
	 */
	@Override
	public List<String> getLocationSchool(Map<String, String> parameters) throws IOException {
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
				}
			}
		}
		return locationSchools;
	}

	
	/**
	 * 根据学校id获取该校对应省内其他高校，按距离从近到远排序
	 */
	@Override
	public List<SchoolCoding> getDistanceSchool(Integer schoolId) {
		// TODO Auto-generated method stub
		SchoolCoding school = schoolDao.getSchoolById(schoolId);
		List<SchoolCoding> schools = schoolDao.getSchoolByProvinceId(school.getSchool_pro_id());
		
		return sortSchoolByDistance(school, schools);

	}

	/**
	 * 对高校距离远近进行排序，以与school的距离为标准
	 * @param school
	 * @param schools
	 * @return
	 */
	public List<SchoolCoding> sortSchoolByDistance(SchoolCoding school,List<SchoolCoding> schools){
		List<Double> distances = new ArrayList<>();
		List<SchoolCoding> schoolAfterSort = new ArrayList<>();
		distances.add(null);
		schoolAfterSort.add(null);
		Double lng = school.getLng();
		Double lat = school.getLat();
		for(SchoolCoding s : schools){
			Double lngS = s.getLng();
			Double latS = s.getLat();
			Double distance = utilsApi.getDistance(lng, lat, lngS, latS);
			System.out.println(s.getSchool_name() + " : "+distance);
			int length = distances.size() + 1;
			for(int i = 0 ; i < length; i++){
				if(distances.get(i) == null || distance <= distances.get(i)){
					distances.add(i, distance);
					schoolAfterSort.add(i, s);
					break;
				}
			}
		}
		schoolAfterSort.remove(schoolAfterSort.size() - 1);
		return schoolAfterSort;
	}

	
	 

}
