package com.qiangu.keyu.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiangu.keyu.api.LoggerApi;
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

	/**
	 * 发送验证码接口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "sendMessageService.do", produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String sendMessageController(HttpServletRequest request, HttpServletResponse response) {
		
		LoggerApi.info(this, request.getRemoteAddr() + " sendMessageService.do " + utilsApi.getCurrentTime());
		String contentType = request.getContentType();
		LoggerApi.info(this, "ContentType = " + contentType);
		Map<String, String[]> parameters = request.getParameterMap();
		String resultStr = "123456789";
		// String verificationCode =
		// utilsApi.getRandomNum(Values.verificationCodeLength);
		String verificationCode = "1111";
		HttpSession session = request.getSession();
		JSONObject resultJSON = utilsApi.parametersIsValid(contentType, parameters);
		if (resultJSON == null) {
			session.setAttribute(Keys.verificationCode, verificationCode);
			session.setAttribute(Keys.verificationCodeTime, System.currentTimeMillis());
			resultJSON = loginAndRegisterResult.getSendMessageResult(parameters, verificationCode);
		}
		resultStr = resultJSON.toString();
//		System.out.println("resultStr ========= " + resultStr);
		LoggerApi.info(this, "resultStr = " + resultStr);

		return resultStr;
	}

	
	/**
	 * 登录或注册接口
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	@RequestMapping(value = "loginOrRegisterService.do", produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String loginOrRegisterController(HttpServletRequest request, HttpServletResponse response)
			throws HttpException, IOException {
		LoggerApi.info(this, "loginOrRegisterService.do " + utilsApi.getCurrentTime());
		String contentType = request.getContentType();
		LoggerApi.info(this, "ContentType = " + contentType);
		Map<String, String[]> parameters = request.getParameterMap();
		String resultStr = "123456789";
		JSONObject resultJSON = utilsApi.parametersIsValid(contentType, parameters);
		if (resultJSON == null) {
			// 判断验证码是否正确
			resultJSON = utilsApi.requestIsLegal(request, parameters);
			if (resultJSON == null) {
				resultJSON = loginAndRegisterResult.getResult(parameters);
			}
		}
		resultStr = resultJSON.toString();
//		System.out.println("resultStr ========= " + resultStr);
		LoggerApi.info(this, "resultStr = " + resultStr);
		return resultStr;
	}

	
	/**
	 * 完成注册接口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "completeRegisterService.do", produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String completeRegister(HttpServletRequest request, HttpServletResponse response) {
		LoggerApi.info(this, "completeRegisterService.do " + utilsApi.getCurrentTime());
		JSONObject resultJSON = null;
		Object object = utilsApi.getUploadParameters(request);
		if (object instanceof JSONObject) {
			resultJSON = (JSONObject) object;
		} else {
			List<Object> parametersList = (List<Object>) object;
			Map<String, String> parameters = (Map<String, String>) parametersList.get(0);
			Map<String, byte[]> fileContents = (Map<String, byte[]>) parametersList.get(1);
			resultJSON = loginAndRegisterResult.getResult(parameters, fileContents);
		}
		String resultStr = resultJSON.toString();
//		System.out.println(resultStr);
		LoggerApi.info(this, "resultStr = " + resultStr);
		return resultStr;
	}

	/**
	 * 注册过程其他服务接口
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	@RequestMapping(value = "registerService.do", produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String RegisterServiceController(HttpServletRequest request, HttpServletResponse response)
			throws HttpException, IOException {
		LoggerApi.info(this, "registerService.do " + utilsApi.getCurrentTime());
		String contentType = request.getContentType();
		LoggerApi.info(this, "ContentType = " + contentType);
		Map<String, String[]> parameters = request.getParameterMap();
		String resultStr = "123456789";
		JSONObject resultJSON = utilsApi.parametersIsValid(contentType, parameters);
		if (resultJSON == null) {
			resultJSON = loginAndRegisterResult.getResult(parameters);
		}
		resultStr = resultJSON.toString();
//		System.out.println("resultStr ========= " + resultStr);
		LoggerApi.info(this, "resultStr = " + resultStr);
		return resultStr;
	}
}
