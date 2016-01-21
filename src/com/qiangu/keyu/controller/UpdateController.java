package com.qiangu.keyu.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiangu.keyu.api.UtilsApi;
import com.qiangu.keyu.result.UpdateResult;

import net.sf.json.JSONObject;

@Controller
public class UpdateController {

	@Autowired
	private UtilsApi utilsApi;
	@Autowired
	private UpdateResult updateResult;
	
	@RequestMapping("updateUserinfoService.do")
	@ResponseBody
	public String updateUserInfoController(HttpServletRequest request, HttpServletResponse response){
		System.out.println("\n updateUserinfoService.do " + utilsApi.getCurrentTime());
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e2) {	
			e2.printStackTrace();
		}
		String contentType = request.getContentType();
		System.out.println("ContentType = "+ contentType);
		Map<String, String[]> parameters = request.getParameterMap();
		String resultStr = "123456789";
		JSONObject resultJSON = utilsApi.parametersIsValid(contentType, parameters);
		if(resultJSON == null){
			resultJSON = updateResult.getResult(parameters);
		}
		resultStr = resultJSON.toString();
		System.out.println("resultStr ========= " + resultStr);
		
		return resultStr;
	}
}
