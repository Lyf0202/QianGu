package com.qiangu.keyu.result;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;

import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.infoToJSON.LoginAndRegisterInfoToJSON;
import com.qiangu.keyu.infoToJSON.SchoolServiceInfoToJSON;

import net.sf.json.JSONObject;

public class LoginAndRegisterResult {

	@Autowired
	private LoginAndRegisterInfoToJSON loginAndRegisterInfoToJSON;
	
	@Autowired
	private SchoolServiceInfoToJSON schoolServiceInfoToJSON ;

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
	
	/**
	 * 获取一般请求结果
	 * @param parameters
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public JSONObject getResult(Map<String,String[]> parameters) throws HttpException, IOException{
		JSONObject result = null;
		if(parameters.get(Keys.method)[0].equals(Values.methodOfLoginOrRegister)){
			result = loginAndRegisterInfoToJSON.loginOrRegisterInfoToJSON(parameters);
		}else if(parameters.get(Keys.method)[0].equals(Values.methodOfGetSchool)){
			result = schoolServiceInfoToJSON.getLocationSchoolInfoToJSON(parameters);
		}else{
			result = new JSONObject();
			JSONObject statusJSON = new JSONObject();
			statusJSON.accumulate(Keys.status, Values.statusOfNoMethod);
			statusJSON.accumulate(Keys.message, Values.messageOfNoMethod);
			result.put(Keys.status, statusJSON);
		}
		return result;
	}
	
	/**
	 * 获取带有图片信息的请求结果
	 * @param parameters
	 * @param fileContents
	 * @return
	 * @throws Exception 
	 */
	public JSONObject getResult(Map<String,String> parameters,Map<String,byte[]> fileContents) throws Exception{
		JSONObject result ;
		if(parameters.get(Keys.method).equals(Values.methodOfCompleteRegister)){
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
