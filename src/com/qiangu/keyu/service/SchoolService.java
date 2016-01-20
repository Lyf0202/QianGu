package com.qiangu.keyu.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;

import com.qiangu.keyu.po.SchoolCoding;

public interface SchoolService {

	public List<String> getLocationSchool(Map<String, String> parameters) throws HttpException, IOException;
	
	
	public List<SchoolCoding> getDistanceSchool(Integer schoolId);
	
}
