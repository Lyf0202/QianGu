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

	public JSONObject getSendMessageResult(Map<String,String[]> parameters,String verificationCode){
		JSONObject result ;
		if(parameters.get(Keys.method)[0].equals(Values.methodOfSendMessage)){
			result = loginAndRegisterInfoToJSON.sendMessageInfoToJSON(parameters,verificationCode);
		}else{
			result = new JSONObject();
			JSONObject statusJSON = new JSONObject();
			statusJSON.accumulate(Keys.status, Values.statusOfNoMethod);
			statusJSON.accumulate(Keys.message, Values.messageOfNoMethod);
			result.put(Keys.status, statusJSON);
		}
		return result;
	}
	
	public JSONObject getResult(Map<String,String[]> parameters){
		JSONObject result ;
		if(parameters.get(Keys.method)[0].equals(Values.methodOfLoginOrRegister)){
			result = loginAndRegisterInfoToJSON.loginOrRegisterInfoToJSON(parameters);
		}else{
			result = new JSONObject();
			JSONObject statusJSON = new JSONObject();
			statusJSON.accumulate(Keys.status, Values.statusOfNoMethod);
			statusJSON.accumulate(Keys.message, Values.messageOfNoMethod);
			result.put(Keys.status, statusJSON);
		}
		return result;
	}
	
	public JSONObject getResult(Map<String,String> parameters,Map<String,byte[]> fileContents){
		JSONObject result ;
		if(parameters.get(Keys.method).equals(Values.completeRegister)){
			result = loginAndRegisterInfoToJSON.completeRegisterInfoToJSON(parameters, fileContents);
		}else{
			result = new JSONObject();
			JSONObject statusJSON = new JSONObject();
			statusJSON.accumulate(Keys.status, Values.statusOfNoMethod);
			statusJSON.accumulate(Keys.message, Values.messageOfNoMethod);
			result.put(Keys.status, statusJSON);
		}
		return result;
	}
}
