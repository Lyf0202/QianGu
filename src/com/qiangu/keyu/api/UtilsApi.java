package com.qiangu.keyu.api;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.controller.Keys;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class UtilsApi {

	/**
	 * 根据正则表达式判断str是否符合
	 * 
	 * @param str
	 * @param regEx
	 * @return
	 */
	public boolean isValid(String str, String regEx) {
		// 编译正则表达式
		Pattern pattern = Pattern.compile(regEx);
		// 忽略大小写的写法
		Matcher matcher = pattern.matcher(str);
		// 字符串是否与正则表达式相匹配
		boolean rs = matcher.matches();

		return rs;
	}

	/**
	 * 获取当前时间，以yyyy-MM-dd HH:mm:ss形式
	 * 
	 * @return
	 */
	public String getCurrentTime() {
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = "";
		nowTime = df.format(dt);
		return nowTime;
	}

	/**
	 * 
	 * @return
	 */
	public String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		// 去掉"-"符号
		String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);
		return temp;
	}

	/**
	 * 获取num位随机数
	 * 
	 * @param num
	 * @return
	 */
	public String getRandomNum(int num) {
		String str = "";
		for (int i = 0; i < num; i++) {
			Random ran = new Random();
			str += ran.nextInt(9);
		}
		return str;
	}

	/**
	 * 判断一般接口参数中是否有空值
	 * 
	 * @param parameters
	 * @return 有空值返回 true 否则返回false
	 */
	public boolean isHasNull(Map<String, String[]> parameters) {
		StringBuilder str = new StringBuilder("\n");
		for (String keySet : parameters.keySet()) {
			if (parameters.get(keySet) == null || parameters.get(keySet).length == 0
					|| parameters.get(keySet)[0].equals("")) {
				return true;
			}
			str.append(keySet);
			str.append(" : ");
			// System.out.print(keySet + " : ");
			for (String s : parameters.get(keySet)) {
				str.append(s);
				str.append(" , ");
				// System.out.print(s + " , ");
			}
			str.append("\n");
			// System.out.println();
		}
		LoggerApi.info(this, str.toString());
		if (parameters.size() == 0)
			return true;
		else
			return false;

	}

	/**
	 * 根据两个经纬度求两个经纬度之间的距离
	 * 
	 * @param long1
	 * @param lat1
	 * @param long2
	 * @param lat2
	 * @return
	 */
	public double getDistance(double long1, double lat1, double long2, double lat2) {
		double a, b, R;
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
		return d;
	}

	// public JSONObject uploadRequestIsLegal(HttpServletRequest request,
	// Map<String, String> parameters){
	// Map<String,String[]> params = new HashMap<>();
	// for (String keySet : parameters.keySet()) {
	// String[] strs = {parameters.get(keySet)};
	// params.put(keySet, strs);
	// }
	// return requestIsLegal(request, params);
	// }
	/**
	 * 判断验证码是否正确,正确，返回null 否则返回对应的错误信息JSON
	 * 
	 * @param request
	 * @param parameters
	 * @return
	 */
	public JSONObject requestIsLegal(HttpServletRequest request, Map<String, String[]> parameters) {
		JSONObject returnJSON = new JSONObject();
		JSONObject statusJSON = new JSONObject();
		String verificationCode = parameters.get(Keys.verificationCode)[0];
		HttpSession session = request.getSession();
		Object sendVerificationCodeTime = session.getAttribute(Keys.verificationCodeTime);
		LoggerApi.info(this, "sendVerificationCodeTime = " + sendVerificationCodeTime);
		// System.out.println("sendVerificationCodeTime = " +
		// sendVerificationCodeTime);
		Object verificationCodeService = session.getAttribute(Keys.verificationCode);
		if (verificationCodeService != null && sendVerificationCodeTime != null) {
			long startTime = Long.valueOf(String.valueOf(sendVerificationCodeTime)).longValue();
			long nowTime = System.currentTimeMillis();
			if ((nowTime - startTime) <= Values.verificationCodeTime) {
				if (verificationCodeService.equals(verificationCode)) {
					return null;
				} else {
					statusJSON.accumulate(Keys.status, Values.statusOfWrongVerificationCode);
					statusJSON.accumulate(Keys.message, Values.messageOfWrongVerificationCode);
				}
			} else {
				statusJSON.accumulate(Keys.status, Values.statusOfInvalidVerificationCode);
				statusJSON.accumulate(Keys.message, Values.messageOfInvalidVerificationCode);
			}
		} else {
			statusJSON.accumulate(Keys.status, Values.statusOfServiceError);
			statusJSON.accumulate(Keys.message, Values.messageOfServiceError);
		}
		returnJSON.put(Keys.status, statusJSON);
		return returnJSON;
	}

	/**
	 * 判断contentType是否正确或客户端传入参数是否有空值
	 * 
	 * @param contentType
	 * @param parameters
	 * @return 若参数正确无误，则返回null ，否则 返回对应的json
	 */
	public JSONObject parametersIsValid(String contentType, Map<String, String[]> parameters) {
		JSONObject returnJSON = null;
		JSONObject statusJSON = new JSONObject();
		// if (contentType != null &&
		// contentType.equals("application/x-www-form-urlencoded")) {
		if (true) {
			if (isHasNull(parameters)) {
				returnJSON = new JSONObject();
				statusJSON.accumulate(Keys.status, Values.statusOfNullParameter);
				statusJSON.accumulate(Keys.message, Values.messageOfNullParameters);
				returnJSON.put(Keys.status, statusJSON);
			}
		} else {
			returnJSON = new JSONObject();
			statusJSON.accumulate(Keys.status, Values.statusOfInvalidRequest);
			statusJSON.accumulate(Keys.message, Values.messageOfInvalidRequest);
			returnJSON.put(Keys.status, statusJSON);
		}
		return returnJSON;
	}

	/**
	 * 判断上传图片时的参数中是否有空值
	 * 
	 * @param parameters
	 * @return 有返回 true 否则返回false
	 */
	public boolean uploadParameterIsHasNull(Map<String, String> parameters) {
		StringBuilder str = new StringBuilder("\n");
		for (String keySet : parameters.keySet()) {
			if (parameters.get(keySet) == null || parameters.get(keySet).equals("")) {
				return true;
			}
			str.append(keySet);
			str.append(" : ");
			str.append(parameters.get(keySet));
			str.append("\n");
			// System.out.println(keySet + " : " + parameters.get(keySet));
		}
		LoggerApi.info(this, str.toString());
		if (parameters.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断上传图片时的参数是否有效
	 * 
	 * @param parameters
	 * @return 参数正确无误 返回null ，否则返回对应的json
	 */
	public JSONObject parametersIsValid(Map<String, String> parameters) {
		JSONObject resultJSON = null;
		if (uploadParameterIsHasNull(parameters)) {
			JSONObject statusJSON = new JSONObject();
			statusJSON.accumulate(Keys.status, Values.statusOfNullParameter);
			statusJSON.accumulate(Keys.message, Values.messageOfNullParameters);
			resultJSON = new JSONObject();
			resultJSON.put(Keys.status, statusJSON);
		}
		return resultJSON;
	}

	/**
	 * 获取上传图片时的参数
	 * 
	 * @param request
	 * @return
	 */
	public Object getUploadParameters(HttpServletRequest request) {
		List<Object> parametersList = new ArrayList<Object>();
		Map<String, String> parameters = new HashMap<>();
		Map<String, byte[]> fileContents = new HashMap<>();
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (isMultipartContent) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			List items = null;
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e1) {
				e1.printStackTrace();
			}
			for (Object object : items) {
				FileItem fileItem = (FileItem) object;
				if (fileItem.isFormField()) {
					String key = fileItem.getFieldName();
					String value = null;
					try {
						value = fileItem.getString("utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					parameters.put(key, value);// 如果你页面编码是utf-8的
				} else {
					// 找出要上传的文件的名字
					String fileName = fileItem.getName();
					fileName = fileName + "." + fileItem.getContentType().split("/")[1];
					parameters.put(fileItem.getFieldName(), fileName);
					fileContents.put(fileItem.getFieldName(), fileItem.get());
					//
				}
			}
			parametersList.add(parameters);
			parametersList.add(fileContents);
			JSONObject resultJSON = parametersIsValid(parameters);
			if (resultJSON != null) {
				return resultJSON;
			}
		} else {
			JSONObject resultJSON = new JSONObject();
			JSONObject statusJSON = new JSONObject();
			statusJSON.accumulate(Keys.status, Values.statusOfInvalidRequest);
			statusJSON.accumulate(Keys.message, Values.messageOfInvalidRequest);
			resultJSON.put(Keys.status, statusJSON);
			return resultJSON;
		}

		return parametersList;
	}

}
