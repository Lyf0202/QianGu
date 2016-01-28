package com.qiangu.keyu.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiangu.keyu.api.UtilsApi;
import com.qiangu.keyu.api.YunPianWangApi;
import com.qiangu.keyu.result.LoginAndRegisterResult;

import net.sf.json.JSONObject;

@Controller
public class LoginAndRegisterController {

	
	
	@Autowired
	private UtilsApi utilsApi;
	
	@Autowired
	private LoginAndRegisterResult loginAndRegisterResult;
	
	@RequestMapping(value="sendMessageService.do",produces="text/json;charset=UTF-8")
	@ResponseBody
	public String sendMessageController(HttpServletRequest request, HttpServletResponse response){
		System.out.println("\n sendMessageService.do " + utilsApi.getCurrentTime());
		String contentType = request.getContentType();
		System.out.println("ContentType = " + contentType);
		Map<String, String[]> parameters = request.getParameterMap();
		String resultStr = "123456789";
//		String verificationCode = utilsApi.getRandomNum(Values.verificationCodeLength);
		String verificationCode = "1111";
		HttpSession session = request.getSession();
		JSONObject resultJSON = utilsApi.parametersIsValid(contentType, parameters);
		if (resultJSON == null) {
			session.setAttribute(Keys.verificationCode,verificationCode);
			session.setAttribute(Keys.verificationCodeTime, System.currentTimeMillis());
			resultJSON = loginAndRegisterResult.getSendMessageResult(parameters, verificationCode);
		}
		resultStr = resultJSON.toString();
		System.out.println("resultStr ========= " + resultStr);

		return resultStr;
	}
	
	@RequestMapping(value="loginOrRegisterService.do",produces="text/json;charset=UTF-8")
	@ResponseBody
	public String loginOrRegisterController(HttpServletRequest request, HttpServletResponse response){
		System.out.println("\n sendMessageService.do " + utilsApi.getCurrentTime());
		String contentType = request.getContentType();
		System.out.println("ContentType = " + contentType);
		Map<String, String[]> parameters = request.getParameterMap();
		String resultStr = "123456789";
		JSONObject resultJSON = utilsApi.parametersIsValid(contentType, parameters);
		if(resultJSON == null){
			resultJSON = utilsApi.requestIsLegal(request, parameters);
			if(resultJSON == null){
				resultJSON = loginAndRegisterResult.getResult(parameters);
			}
		}
		resultStr = resultJSON.toString();
		System.out.println("resultStr ========= " + resultStr);
		return null;
	}
}
