package com.qiangu.keyu.infoToJSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qiangu.keyu.api.KeYuApi;
import com.qiangu.keyu.api.MongodbApi;
import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.dao.MongodbDao;
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
	

	public JSONObject openAppInfoToJSON_1(Map<String,String[]> parameters){
		
		return null;
	}
	
	public JSONObject openAppInfoToJSON(Map<String,String[]> parameters){
		JSONObject returnJSON = new JSONObject();
		JSONObject statusJSON = new JSONObject();
		JSONObject resultJSON = new JSONObject();
		
		Integer userId = Integer.valueOf(parameters.get(Keys.userId)[0]);
		UserPo user = userService.getUserByUserId(userId);
		JSONObject me = keYuApi.userPoToJSON(user);
		resultJSON.put(Keys.me, me);
		
		//获取用户的聊天信息
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
			Map<Integer,Map<String,Object>> userLoc = userService.getUserLoc(userId);
			Double lng = (Double)userLoc.get(userId).get(Keys.lng);
			Double lat = (Double)userLoc.get(userId).get(Keys.lat);
			
			//更新用户所在经纬度

			
			Integer minDistance = Integer.valueOf(parameters.get(Keys.minDistance)[0]);
			Integer maxDistance = Integer.valueOf(parameters.get(Keys.maxDistance)[0]);
			//获取附近的人
			List<JSONObject> distanceUserJSONList = keYuApi.getDistanceUserJSONList(userId, lng, lat, minDistance, maxDistance, minOnlineTime, maxOnlineTime);
			resultJSON.put(Keys.distanceUser,distanceUserJSONList);
		}
		
		List<JSONObject> likeUserJSONList = null;
		//判断客户端本地是否已经有喜欢用户缓存    获取点击喜欢的用户列表
		if(parameters.get(Keys.hasLikeUser)[0].equals(Values.hasLikeUser)){
			likeUserJSONList = keYuApi.getLikeUserJSONList(userId,user.getLastOnlineTime());
		}else{
			likeUserJSONList = keYuApi.getLikeUserJSONList(userId,Long.valueOf("1456578500742"));
		}
		
		resultJSON.put(Keys.likeUser, likeUserJSONList);
		resultJSON.put(Keys.likeUserOrder, user.getLikeUserOrder());
		
		statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
		
		returnJSON.put(Keys.result, resultJSON);
		returnJSON.put(Keys.status, statusJSON);
		return returnJSON;
	}
	
	public JSONObject getUserInfoToJSON(Map<String,String[]> parameters){
		JSONObject returnJSON = new JSONObject();
		JSONObject statusJSON = new JSONObject();
		JSONObject resultJSON = new JSONObject();
		
		Integer userId = Integer.valueOf(parameters.get(Keys.userId)[0]);
		Long minOnlineTime = Long.valueOf(parameters.get(Keys.minOnlineTime)[0]);
		Long maxOnlineTime = Long.valueOf(parameters.get(Keys.maxOnlineTime)[0]);
		if(parameters.get(Keys.lat)[0].equals(Values.noLoc)){
			Integer schoolId = Integer.valueOf(parameters.get(Keys.school)[0]);
			List<JSONObject> schoolUserJSONList = keYuApi.getSchoolUserJSONList(userId,schoolId, minOnlineTime, maxOnlineTime);
			resultJSON.put(Keys.schoolUser, schoolUserJSONList);
		}else{
			Map<Integer,Map<String,Object>> userLoc = userService.getUserLoc(userId);
			Double lng = (Double)userLoc.get(userId).get(Keys.lng);
			Double lat = (Double)userLoc.get(userId).get(Keys.lat);
			
			Integer minDistance = Integer.valueOf(parameters.get(Keys.minDistance)[0]);
			Integer maxDistance = Integer.valueOf(parameters.get(Keys.maxDistance)[0]);
			List<JSONObject> distanceUserJSONList = keYuApi.getDistanceUserJSONList(userId, lng, lat, minDistance, maxDistance, minOnlineTime, maxOnlineTime);
			resultJSON.put(Keys.distanceUser,distanceUserJSONList);
		}
		
		statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
		
		returnJSON.put(Keys.result, resultJSON);
		returnJSON.put(Keys.status, statusJSON);
		return returnJSON;
	}
}
