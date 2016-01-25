package com.qiangu.keyu.result;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.infoToJSON.LoginAndRegisterInfoToJSON;

import net.sf.json.JSONObject;

public class LoginAndRegisterResult {

	@Autowired
	private LoginAndRegisterInfoToJSON loginAndRegisterInfoToJSON;
	
	public JSONObject getResult(Map<String,String[]> parameters){
		JSONObject result ;
		if(parameters.get(Keys.method).equals(Values.methodOfSendMessage)){
			result = loginAndRegisterInfoToJSON.sendMessageInfoToJSON(parameters);
		}else{
			result = new JSONObject();
			JSONObject statusJSON = new JSONObject();
			statusJSON.accumulate(Keys.status, Values.statusOfNoMethod);
			statusJSON.accumulate(Keys.message, Values.messageOfNoMethod);
			result.put(Keys.status, statusJSON);
		}
		return null;
	}
}
