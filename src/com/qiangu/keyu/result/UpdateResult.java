package com.qiangu.keyu.result;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.infoToJSON.UpdateInfoToJSON;

import net.sf.json.JSONObject;

public class UpdateResult {

	@Autowired
	private UpdateInfoToJSON updateInfoToJSON;
	
	public JSONObject getResult(Map<String,String[]> parameters){
		JSONObject result = null;
		if(parameters.get(Keys.method)[0].equals(Values.methodOfUpdateName)){
			result = updateInfoToJSON.updateNameInfoToJSON(parameters);
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
