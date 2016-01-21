package com.qiangu.keyu.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.controller.Keys;

import net.sf.json.JSONObject;

public class UtilsApi {

	public boolean isValid(String str, String regEx) {
		// 编译正则表达式
		Pattern pattern = Pattern.compile(regEx);
		// 忽略大小写的写法
		Matcher matcher = pattern.matcher(str);
		// 字符串是否与正则表达式相匹配
		boolean rs = matcher.matches();

		return rs;
	}

	public String getCurrentTime() {
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = "";
		nowTime = df.format(dt);
		return nowTime;
	}

	public String getRandomNum(int num) {
		String str = "";
		for (int i = 0; i < num; i++) {
			Random ran = new Random();
			str += ran.nextInt(9);
		}
		return str;
	}

	public boolean isHasNull(Map<String, String[]> parameters) {

		for (String keySet : parameters.keySet()) {
			if (parameters.get(keySet) == null || parameters.get(keySet).length == 0
					|| parameters.get(keySet)[0].equals("")) {
				return true;
			}
			System.out.print(keySet + " : ");
			for (String s : parameters.get(keySet)) {
				System.out.print(s + " , ");
			}
			System.out.println();
		}
		return false;

	}

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

	public JSONObject parametersIsValid(String contentType, Map<String, String[]> parameters) {
		JSONObject returnJSON = null;
		JSONObject statusJSON = new JSONObject();
		if (contentType != null && contentType.equals("application/x-www-form-urlencoded")) {
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
}
