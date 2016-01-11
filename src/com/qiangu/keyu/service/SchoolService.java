package com.qiangu.keyu.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;

public interface SchoolService {

	public List<String> getLocationSchool(Map<String, String> parameters) throws HttpException, IOException;
}
