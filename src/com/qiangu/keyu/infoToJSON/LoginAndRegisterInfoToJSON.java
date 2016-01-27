package com.qiangu.keyu.infoToJSON;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qiangu.keyu.api.YunPianWangApi;
import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.po.SchoolCoding;
import com.qiangu.keyu.po.UserPo;
import com.qiangu.keyu.service.SchoolService;
import com.qiangu.keyu.service.UserService;

import net.sf.json.JSONObject;

public class LoginAndRegisterInfoToJSON {

	@Autowired
	private YunPianWangApi yunPianWangApi;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SchoolService schoolService;

	public JSONObject sendMessageInfoToJSON(Map<String, String[]> parameters) {
		JSONObject returnJSON = new JSONObject();
		JSONObject statusJSON = new JSONObject();
		// 发送验证码
		String yunpianwangResult = yunPianWangApi.sendSms(parameters.get(Keys.verificationCode)[0],
				parameters.get(Keys.telephone)[0]);
		System.out.println("yunpianwangResult = " + yunpianwangResult);
		// 发送成功情况
		if (yunpianwangResult.equals(YunPianWangApi.okResult)) {
			statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
			statusJSON.accumulate(Keys.message, YunPianWangApi.okMessage);
		}
		// 发送失败情况
		else {
			statusJSON.accumulate(Keys.status, Values.statusOfSendMessageFailed);
			if (yunpianwangResult.equals(YunPianWangApi.limit_24_hours)) {
				statusJSON.accumulate(Keys.message, YunPianWangApi.limit_24_hours_message);
			} else if (yunpianwangResult.equals(YunPianWangApi.limit_30_seconds)) {
				statusJSON.accumulate(Keys.message, YunPianWangApi.limit_30_seconds_message);
			} else if (yunpianwangResult.equals(YunPianWangApi.limit_5_minites)) {
				statusJSON.accumulate(Keys.message, YunPianWangApi.limit_5_minites_message);
			} else if (yunpianwangResult.equals(YunPianWangApi.limit_areas)) {
				statusJSON.accumulate(Keys.message, YunPianWangApi.limit_areas_message);
			} else {
				statusJSON.accumulate(Keys.message, Values.messageOfSenfMessageFailed);
			}
		}
		returnJSON.put(Keys.status, statusJSON);
		return returnJSON;
	}
	
	public JSONObject loginOrRegisterInfoToJSON(Map<String ,String[]> parameters){
		JSONObject returnJSON = new JSONObject();
		JSONObject statusJSON = new JSONObject();
		UserPo user = userService.getLoginOrRegisterUserInfo(parameters);
		if(user != null){
			statusJSON.accumulate(Keys.status, Values.statusOfUserNotExist);
			statusJSON.accumulate(Keys.message, Values.messageOfUserNotExist);
		}else{
			statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
			
		}
		return null;
	}
	
	public JSONObject userPoToJSON(UserPo user){
		JSONObject json = new JSONObject();
		json.accumulate(Keys.userId, user.getId());
		json.accumulate(Keys.sex, user.getSex());
		json.accumulate(Keys.name, user.getName());
		json.accumulate(Keys.birthday, user.getBirthday());
		json.accumulate(Keys.chatId, user.getTalkId());
		json.accumulate(Keys.lastLoginTime,user.getLastOnlineTime());
		json.accumulate(Keys.education, user.getEducation());
		json.accumulate(Keys.weight, user.getWeight());
		json.accumulate(Keys.height, user.getHeight());
		json.accumulate(Keys.motto, user.getLoveManifestoId());
		SchoolCoding school = schoolService.getSchoolById(user.getSchoolId());
		
		return null;
	}
	
	public JSONObject chatInfoToJSON(Map m){
	
		return null;
	}
}
