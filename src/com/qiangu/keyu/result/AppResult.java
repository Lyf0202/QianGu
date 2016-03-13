package com.qiangu.keyu.result;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.infoToJSON.AppInfoToJSON;

import net.sf.json.JSONObject;

@Component
public class AppResult {

	@Autowired
	private AppInfoToJSON appInfoToJSON;
	
	public JSONObject getResult(Map<String, String[]> parameters) {
		JSONObject result = null;
		String method = parameters.get(Keys.method)[0];
		if (method.equals(Values.methodOfGetSystemTime)) {
			result = appInfoToJSON.getToken(parameters);
		} 
		else {
			result = new JSONObject();
			JSONObject statusJSON = new JSONObject();
			statusJSON.accumulate(Keys.status, Values.statusOfNoMethod);
			statusJSON.accumulate(Keys.message, Values.messageOfNoMethod);
			result.put(Keys.status, statusJSON);
		}

		return result;
	}
}
