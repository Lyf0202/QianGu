package com.qiangu.keyu.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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

	@RequestMapping(value="updateUserInfoService.do",produces="text/json;charset=UTF-8")
	@ResponseBody
	public String updateUserInfoController(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("\n updateUserinfoService.do " + utilsApi.getCurrentTime());
		String contentType = request.getContentType();
		System.out.println("ContentType = " + contentType);
		Map<String, String[]> parameters = request.getParameterMap();
		String resultStr = "123456789";
		JSONObject resultJSON = utilsApi.parametersIsValid(contentType, parameters);
		if (resultJSON == null) {
			resultJSON = updateResult.getResult(parameters);
		}
		resultStr = resultJSON.toString();
		System.out.println("resultStr ========= " + resultStr);

		return resultStr;
	}

	@RequestMapping(value="updatePicInfoService.do",produces="text/json;charset=UTF-8")
	@ResponseBody
	public String updatePicInfoController(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("\n updatePicInfoService.do " + utilsApi.getCurrentTime() + " *****************");
		JSONObject resultJSON = null;
		Object object = utilsApi.getUploadParameters(request);
		if(object instanceof JSONObject){
			resultJSON = (JSONObject) object;
		}else{
			List<Object> parametersList = (List<Object>) object;
			Map<String,String> parameters = (Map<String, String>) parametersList.get(0);
			Map<String,byte[]> fileContents = (Map<String, byte[]>) parametersList.get(1);
			resultJSON = updateResult.getResult(parameters, fileContents);
		}
		String resultStr = resultJSON.toString();
		System.out.println();
		System.out.println(resultStr);
		return resultStr;
	}
}
