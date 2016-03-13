package com.qiangu.keyu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiangu.keyu.api.KeYuApi;
import com.qiangu.keyu.api.LoggerApi;
import com.qiangu.keyu.api.UtilsApi;
import com.qiangu.keyu.result.UserResult;

import net.sf.json.JSONObject;

@Controller
public class UserController {

	@Autowired
	private UtilsApi utilsApi;
	
	@Autowired
	private UserResult userResult;
	
	@Autowired
	private KeYuApi keyuApi;
	
	
	@RequestMapping(value = "userService.do", produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String userController(HttpServletRequest request, HttpServletResponse response){
		LoggerApi.info(this, "userService.do" + utilsApi.getCurrentTime());
		Map<String, String[]> parameters = request.getParameterMap();
		String resultStr = "123456789";
		JSONObject resultJSON = utilsApi.parametersIsValid("", parameters);
		if (resultJSON == null) {
			resultJSON = keyuApi.isValidUser(parameters);
			if (resultJSON == null) {
				resultJSON = userResult.getResult(parameters); 
			} 
			
		}
		resultStr = resultJSON.toString();
		LoggerApi.info(this, "resultStr = " + resultStr);
		return resultStr;
	}
	
	@RequestMapping(value = "openAppService.do", produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String openAppController(HttpServletRequest request, HttpServletResponse response){
		LoggerApi.info(this, "updateUserInfoService.do" + utilsApi.getCurrentTime());
		Map<String, String[]> parameters = request.getParameterMap();
		String resultStr = "123456789";
		JSONObject resultJSON = utilsApi.parametersIsValid("", parameters);
		if (resultJSON == null) {
			resultJSON = keyuApi.isValidUser(parameters);
			if (resultJSON == null) {
				resultJSON = userResult.getResult(parameters);
			}
			 
		}
		resultStr = resultJSON.toString();
		LoggerApi.info(this, "resultStr = " + resultStr);
		return resultStr;
		
	}
	
	@RequestMapping(value = "userPicService.do", produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String userPicController(HttpServletRequest request, HttpServletResponse response){
		LoggerApi.info(this, "userPicService.do " + utilsApi.getCurrentTime());
		JSONObject resultJSON = null;
		Object object = utilsApi.getUploadParameters(request);
		if (object instanceof JSONObject) {
			resultJSON = (JSONObject) object;
		} else {
			List<Object> parametersList = (List<Object>) object;
			Map<String, String> parameters = (Map<String, String>) parametersList.get(0);
			Map<String, byte[]> fileContents = (Map<String, byte[]>) parametersList.get(1);
			resultJSON = userResult.getResult(parameters, fileContents);
		}
		String resultStr = resultJSON.toString();
		LoggerApi.info(this, "resultStr = " + resultStr);
		return resultStr;
	}
	
}
