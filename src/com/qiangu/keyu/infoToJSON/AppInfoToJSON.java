package com.qiangu.keyu.infoToJSON;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qiangu.keyu.api.UtilsApi;
import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;

import net.sf.json.JSONObject;

@Component
public class AppInfoToJSON {

	@Autowired
	private UtilsApi utilsApi;
	
	public JSONObject getToken(Map<String, String[]> parameters){
		JSONObject resultJSON = new JSONObject();
		JSONObject statusJSON = new JSONObject();
		JSONObject returnJSON = new JSONObject();
		
		String nowTime = utilsApi.getCurrentTime();
		String token = nowTime.substring(0, 10);
		statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
		resultJSON.accumulate(Keys.systemTime, token);
		
		returnJSON.put(Keys.status, statusJSON);
		returnJSON.put(Keys.result, resultJSON);
		return returnJSON;
	}
}
