package com.qiangu.keyu.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiangu.keyu.api.LoggerApi;
import com.qiangu.keyu.api.UtilsApi;
import com.qiangu.keyu.result.AppResult;

import net.sf.json.JSONObject;

@Controller
public class AppController {
	
	@Autowired
	private UtilsApi utilsApi;
	
	@Autowired
	private AppResult appResult;

	@RequestMapping(value = "AppService.do", produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String appController(HttpServletRequest request, HttpServletResponse response){
		LoggerApi.info(this, "updateUserInfoService.do" + utilsApi.getCurrentTime());
		Map<String, String[]> parameters = request.getParameterMap();
		String resultStr = "123456789";
		JSONObject resultJSON = utilsApi.parametersIsValid("", parameters);
		if (resultJSON == null) {
			resultJSON = appResult.getResult(parameters); 
		}
		resultStr = resultJSON.toString();
		LoggerApi.info(this, "resultStr = " + resultStr);
		return resultStr;
	}
}
