package com.qiangu.keyu.infoToJSON;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.service.UserUpdateService;

import net.sf.json.JSONObject;

public class UpdateInfoToJSON {

	@Autowired
	private UserUpdateService userUpdateService;
	
	private JSONObject returnJSON;
	
	private JSONObject statusJSON;
	
	private JSONObject resultJSON;
	
	public JSONObject updateNameInfoToJSON(Map<String,String[]> parameters){
		returnJSON = new JSONObject();
		statusJSON = new JSONObject();
		if(userUpdateService.updateName(parameters)){
			resultJSON = new JSONObject();
			resultJSON.accumulate(Keys.name,parameters.get(Keys.name)[0]);
			statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
			returnJSON.put(Keys.result, resultJSON);
		}else{
			statusJSON.accumulate(Keys.status, Values.statusOfServiceError);
			statusJSON.accumulate(Keys.message, Values.messageOfServiceError);
		}
		returnJSON.put(Keys.status, statusJSON);
		return returnJSON;
	}
}
