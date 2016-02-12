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
	
	public JSONObject updateAvatarInfoToJSON(Map<String,String> parameters,Map<String,byte[]> fileContents){
		returnJSON = new JSONObject();
		statusJSON = new JSONObject();
		Integer userId = Integer.valueOf(parameters.get(Keys.userId));
		byte[] avatarContent = fileContents.get(Keys.avatar);
		if(userUpdateService.updateAvatar(userId, avatarContent)){
			statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
		}else{
			statusJSON.accumulate(Keys.status, Values.statusOfServiceError);
			statusJSON.accumulate(Keys.message, Values.messageOfServiceError);
		}
		returnJSON.put(Keys.status,statusJSON);
 		return returnJSON;
	}
	
	public JSONObject updateHeightInfoToJSON(Map<String,String[]> parameters){
		returnJSON = new JSONObject();
		statusJSON = new JSONObject();
		Integer userId = Integer.valueOf(parameters.get(Keys.userId)[0]);
		Double height = Double.valueOf(parameters.get(Keys.height)[0]);
		if(userUpdateService.updateHeight(userId, height)){
			resultJSON = new JSONObject();
			resultJSON.accumulate(Keys.height,height);
			statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
			returnJSON.put(Keys.result, resultJSON);
		}else{
			statusJSON.accumulate(Keys.status, Values.statusOfServiceError);
			statusJSON.accumulate(Keys.message, Values.messageOfServiceError);
		}
		returnJSON.put(Keys.status, statusJSON);
		return returnJSON;
	}
	
	public JSONObject updateWeightInfoToJSON(Map<String,String[]> parameters){
		returnJSON = new JSONObject();
		statusJSON = new JSONObject();
		Integer userId = Integer.valueOf(parameters.get(Keys.userId)[0]);
		Double weight = Double.valueOf(parameters.get(Keys.weight)[0]);
		if(userUpdateService.updateWeight(userId, weight)){
			resultJSON = new JSONObject();
			resultJSON.accumulate(Keys.weight ,weight);
			statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
			returnJSON.put(Keys.result, resultJSON);
		}else{
			statusJSON.accumulate(Keys.status, Values.statusOfServiceError);
			statusJSON.accumulate(Keys.message, Values.messageOfServiceError);
		}
		returnJSON.put(Keys.status, statusJSON);
		return returnJSON;
	}
	
	public JSONObject updateBirthdayInfoToJSON(Map<String,String[]> parameters){
		returnJSON = new JSONObject();
		statusJSON = new JSONObject();
		Integer userId = Integer.valueOf(parameters.get(Keys.userId)[0]);
		String birthday = parameters.get(Keys.birthday)[0];
		if(userUpdateService.updateBirthday(userId, birthday)){
			resultJSON = new JSONObject();
			resultJSON.accumulate(Keys.birthday,birthday);
			statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
			returnJSON.put(Keys.result, resultJSON);
		}else{
			statusJSON.accumulate(Keys.status, Values.statusOfServiceError);
			statusJSON.accumulate(Keys.message, Values.messageOfServiceError);
		}
		returnJSON.put(Keys.status, statusJSON);
		return returnJSON;	
	}
	
	public JSONObject updateLoveManifestoInfoToJSON(Map<String,String[]> parameters){
		returnJSON = new JSONObject();
		statusJSON = new JSONObject();
		Integer userId = Integer.valueOf(parameters.get(Keys.userId)[0]);
		String loveManifesto = parameters.get(Keys.motto)[0];
		if(userUpdateService.updateLoveManifesto(userId, loveManifesto)){
			resultJSON = new JSONObject();
			resultJSON.accumulate(Keys.motto,loveManifesto);
			statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
			returnJSON.put(Keys.result, resultJSON);
		}else{
			statusJSON.accumulate(Keys.status, Values.statusOfServiceError);
			statusJSON.accumulate(Keys.message, Values.messageOfServiceError);
		}
		returnJSON.put(Keys.status, statusJSON);
		return returnJSON;	
	}
	
	public JSONObject updateHometownInfoToJSON(Map<String,String[]> parameters){
		returnJSON = new JSONObject();
		statusJSON = new JSONObject();
		Integer userId = Integer.valueOf(parameters.get(Keys.userId)[0]);
		Integer areaId = Integer.valueOf(parameters.get(Keys.city)[0]);
		if(userUpdateService.updateHometown(userId, areaId)){
			statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
			resultJSON = new JSONObject();
			resultJSON.accumulate(Keys.city, areaId);
			returnJSON.put(Keys.status, statusJSON);
		}else{
			statusJSON.accumulate(Keys.status, Values.statusOfServiceError);
			statusJSON.accumulate(Keys.message, Values.messageOfServiceError);
		}
		returnJSON.put(Keys.status, statusJSON);
		return returnJSON;
	}
	
	public JSONObject updateLabelsInfoToJSON(Map<String,String[]> parameters){
		returnJSON = new JSONObject();
		statusJSON = new JSONObject();
		Integer userId = Integer.valueOf(parameters.get(Keys.userId)[0]);
		String[] officialLabels = parameters.get(Keys.officialLabels);
		String[] userLabels = parameters.get(Keys.userLabels);
		String[] oldLabels = parameters.get(Keys.oldLabels);
		
		if(userUpdateService.updateLabel(userId, oldLabels, officialLabels, userLabels) != null){
			statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
		}else{
			statusJSON.accumulate(Keys.status, Values.statusOfServiceError);
			statusJSON.accumulate(Keys.message,Values.messageOfServiceError);
		}
		returnJSON.put(Keys.status, statusJSON);
		return returnJSON;
		
	}
}
