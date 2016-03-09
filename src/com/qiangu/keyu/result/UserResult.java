package com.qiangu.keyu.result;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.infoToJSON.UserInfoToJSON;

import net.sf.json.JSONObject;

@Component
public class UserResult {

	@Autowired
	private UserInfoToJSON userInfoToJSON;

	public JSONObject getResult(Map<String, String[]> parameters) {
		JSONObject result = null;
		if (parameters.get(Keys.method)[0].equals(Values.methodOfOpenApp)) {
			result = userInfoToJSON.openAppInfoToJSON(parameters);
		} else if (parameters.get(Keys.method)[0].equals(Values.methodOfGetUser)) {
			result = userInfoToJSON.getUserInfoToJSON(parameters);
		} else if (parameters.get(Keys.method)[0].equals(Values.methodOfClickLike)) {
			result = userInfoToJSON.getClickLikeInfoToJSON(parameters);
		} else if (parameters.get(Keys.method)[0].equals(Values.methodOfDeleteChatUser)) {
			result = userInfoToJSON.getDeleteChatUserInfoToJSON(parameters);
		} else if (parameters.get(Keys.method)[0].equals(Values.methodOfUpdateIntimacy)) {
			result = userInfoToJSON.getUpdateIntimacyInfoToJSON(parameters);
		} else if (parameters.get(Keys.method)[0].equals(Values.methodOfStartChat)) {
			result = userInfoToJSON.getUpdateForStartChatInfoToJSON(parameters);
		} else {
			result = new JSONObject();
			JSONObject statusJSON = new JSONObject();
			statusJSON.accumulate(Keys.status, Values.statusOfNoMethod);
			statusJSON.accumulate(Keys.message, Values.messageOfNoMethod);
			result.put(Keys.status, statusJSON);
		}

		return result;
	}
	
	public JSONObject getResult(Map<String,String> parameters,Map<String,byte[]> fileContents){
		JSONObject result = null;
		if(parameters.get(Keys.method).equals(Values.methodOfUploadIDVerifyPicture)){
			result = userInfoToJSON.getUploadIDVerifyPicInfoToJSON(parameters, fileContents);
		}else {
			result = new JSONObject();
			JSONObject statusJSON = new JSONObject();
			statusJSON.accumulate(Keys.status, Values.statusOfNoMethod);
			statusJSON.accumulate(Keys.message, Values.messageOfNoMethod);
			result.put(Keys.status, statusJSON);
		}

		return result;
	}
}
