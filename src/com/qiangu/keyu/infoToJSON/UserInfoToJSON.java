package com.qiangu.keyu.infoToJSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qiangu.keyu.api.KeYuApi;
import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.po.UserPo;
import com.qiangu.keyu.service.ChatService;
import com.qiangu.keyu.service.UserService;

import net.sf.json.JSONObject;

@Component
public class UserInfoToJSON {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private KeYuApi keYuApi;
	
	@Autowired
	private ChatService chatService;
	
	public JSONObject openAppInfoToJSON(Map<String,String[]> parameters){
		JSONObject returnJSON = new JSONObject();
		JSONObject statusJSON = new JSONObject();
		JSONObject resultJSON = new JSONObject();
		
		Integer userId = Integer.valueOf(parameters.get(Keys.userId)[0]);
		UserPo user = userService.getUserByUserId(userId);
		JSONObject me = keYuApi.userPoToJSON(user);
		resultJSON.put(Keys.me, me);
		List<Map> chatMapList = chatService.getChatInfo(user.getId());
		List<JSONObject> chatUserList = new ArrayList<>();
		for (int i = 1; i <= chatMapList.size(); i++) {
			JSONObject chatUser = keYuApi.chatUserInfoToJSON(chatMapList.get(i - 1));
			chatUserList.add(chatUser);
		}
		resultJSON.put(Keys.chatUser, chatUserList);
		Long minOnlineTime = Long.valueOf(parameters.get(Keys.minOnlineTime)[0]);
		Long maxOnlineTime = Long.valueOf(parameters.get(Keys.maxOnlineTime)[0]);
		if(parameters.get(Keys.lat)[0].equals(Values.noLoc)){
			List<JSONObject> schoolUserJSONList = keYuApi.getSchoolUserJSONList(userId,user.getSchoolId(), minOnlineTime, maxOnlineTime);
			resultJSON.put(Keys.schoolUser, schoolUserJSONList);
		}else{
			Double lng = Double.valueOf(parameters.get(Keys.lng)[0]);
			Double lat = Double.valueOf(parameters.get(Keys.lat)[0]);
			Integer minDistance = Integer.valueOf(parameters.get(Keys.minDistance)[0]);
			Integer maxDistance = Integer.valueOf(parameters.get(Keys.maxDistance)[0]);
			List<JSONObject> distanceUserJSONList = keYuApi.getDistanceUserJSONList(userId, lng, lat, minDistance, maxDistance, minOnlineTime, maxOnlineTime);
			resultJSON.put(Keys.distanceUser,distanceUserJSONList);
		}
		List<JSONObject> likeUserJSONList = keYuApi.getLikeUserJSONList(userId,new Date(user.getLastOnlineTime()));
		resultJSON.put(Keys.likeUser, likeUserJSONList);
		statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
		
		returnJSON.put(Keys.result, resultJSON);
		returnJSON.put(Keys.status, statusJSON);
		return returnJSON;
	}
}
