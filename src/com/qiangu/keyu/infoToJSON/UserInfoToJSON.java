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
		Integer sex ;
		if(user.getSex() == Values.female){
			sex = Values.male;
		}else{
			sex = Values.female;
		}
		Long onlineTime = Long.valueOf(parameters.get(Keys.onlineTime)[0]);
		Long openTime = Long.valueOf(parameters.get(Keys.openTime)[0]);
		
		List<JSONObject> distanceUserJSONList = new ArrayList<>();
		
		Map<Integer,Map<String,Object>> userLoc = userService.getUserLoc(userId);
		Double lng = (Double)userLoc.get(userId).get(Keys.lng);
		Double lat = (Double)userLoc.get(userId).get(Keys.lat);
		
		if(lng == Values.noLocLng){
//			List<JSONObject> schoolUserJSONList = keYuApi.getSchoolUserJSONList(userId,user.getSchoolId(), minOnlineTime, maxOnlineTime);
//			resultJSON.put(Keys.schoolUser, schoolUserJSONList);
		}else{
			Integer distance = Integer.valueOf(parameters.get(Keys.distance)[0]);
			
			//获取附近的人
			Map<String,Object> distanceResult = userService.getMainUserByDistance(userId, lng, lat, distance, onlineTime,sex, openTime);
			List<UserPo> distanceUserList = (List<UserPo>) distanceResult.get(Keys.mainUser);
			distanceUserJSONList = keYuApi.getDistanceUserJSONList(distanceUserList,userId);
			resultJSON.accumulate(Keys.onlineTime, distanceResult.get(Keys.onlineTime));
			resultJSON.accumulate(Keys.distance,distanceResult.get(Keys.distance));
			resultJSON.accumulate(Keys.openTime, openTime);
		}
		/*
		 * ------------------------------------------------------
		 */
		Long likeTime = Long.valueOf(parameters.get(Keys.likeTime)[0]);
		Map<String,Object> likeResult = userService.getMainUserByLike(userId, likeTime, sex);
		List<UserPo> likeUserList =  (List<UserPo>) likeResult.get(Keys.likeUser);
		List<JSONObject> likeUserJSONList = keYuApi.getLikeUserJSONList(likeUserList);
		resultJSON.accumulate(Keys.likeTime, likeResult.get(Keys.likeTime));
		Integer distanceNum = distanceUserJSONList.size();
		Integer likeNum = likeUserJSONList.size();
		if(distanceNum < Values.onceUserNum && likeNum < Values.onceLikeUserNum){
			resultJSON.accumulate(Keys.isAllUser, Values.isAll);
		}else {
			resultJSON.accumulate(Keys.isAllUser, Values.notIsAll);
		}
		
		resultJSON.put(Keys.likeUser, likeUserJSONList);
		resultJSON.put(Keys.distanceUser, distanceUserJSONList);
		
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
		Long onlineTime = Long.valueOf(parameters.get(Keys.onlineTime)[0]);
		Long openTime = Long.valueOf(parameters.get(Keys.openTime)[0]);
		Integer sex = Integer.valueOf(parameters.get(Keys.sex)[0]);
		if(sex == Values.female){
			sex = Values.male;
		}else {
			sex = Values.female;
		}
		List<JSONObject> distanceUserJSONList = new ArrayList<>();
		
		Map<Integer,Map<String,Object>> userLoc = userService.getUserLoc(userId);
		Double lng = (Double)userLoc.get(userId).get(Keys.lng);
		Double lat = (Double)userLoc.get(userId).get(Keys.lat);
		if(lat == Values.noLocLat){
			Integer schoolId = Integer.valueOf(parameters.get(Keys.school)[0]);
//			List<JSONObject> schoolUserJSONList = keYuApi.getSchoolUserJSONList(userId,schoolId, minOnlineTime, maxOnlineTime);
//			resultJSON.put(Keys.schoolUser, schoolUserJSONList);
		}else{
			Integer distance = Integer.valueOf(parameters.get(Keys.distance)[0]);
			Map<String,Object> distanceResult = userService.getMainUserByDistance(userId, lng, lat, distance, onlineTime, sex, openTime);
			List<UserPo> distanceUserList = (List<UserPo>) distanceResult.get(Keys.mainUser);
			distanceUserJSONList = keYuApi.getDistanceUserJSONList(distanceUserList,userId);			
		}
		
		Long likeTime = Long.valueOf(parameters.get(Keys.likeTime)[0]);
		Map<String,Object> likeResult = userService.getMainUserByLike(userId, likeTime, sex);
		List<UserPo> likeUserList =  (List<UserPo>) likeResult.get(Keys.likeUser);
		List<JSONObject> likeUserJSONList = keYuApi.getLikeUserJSONList(likeUserList);
		resultJSON.accumulate(Keys.likeTime, likeResult.get(Keys.likeTime));
		Integer distanceNum = distanceUserJSONList.size();
		Integer likeNum = likeUserJSONList.size();
		if(distanceNum < Values.onceUserNum && likeNum < Values.onceLikeUserNum){
			resultJSON.accumulate(Keys.isAllUser, Values.isAll);
		}else {
			resultJSON.accumulate(Keys.isAllUser, Values.notIsAll);
		}
		
		resultJSON.put(Keys.distanceUser,distanceUserJSONList);
		resultJSON.put(Keys.likeUser, likeUserJSONList);
		
		statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
		
		returnJSON.put(Keys.result, resultJSON);
		returnJSON.put(Keys.status, statusJSON);
		return returnJSON;
	}
}
