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
import com.qiangu.keyu.po.ChatPo;
import com.qiangu.keyu.po.LikePo;
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
		UserPo user = userService.getUserByUserId(userId);
		Long onlineTime = null;
		Long openTime = null;
		Integer distance = null;
		Long likeTime = null;
		//判断是否是刚打开APP时第一次获取用户
		if(parameters.get(Keys.openTime) == null){
//			onlineTime = System.currentTimeMillis();
//			openTime = System.currentTimeMillis();
			distance = 0;
			onlineTime = Long.valueOf("1456643744919");
			openTime = Long.valueOf("1456643744919");
			likeTime = Long.valueOf("1456643744919");
		}else{
			onlineTime = Long.valueOf(parameters.get(Keys.onlineTime)[0]);
			openTime = Long.valueOf(parameters.get(Keys.openTime)[0]);
			distance = Integer.valueOf(parameters.get(Keys.distance)[0]);
			likeTime = Long.valueOf(parameters.get(Keys.likeTime)[0]);
		} 
		Integer sex = user.getSex();
		if(sex == Values.female){
			sex = Values.male;
		}else {
			sex = Values.female;
		}
		List<JSONObject> distanceUserJSONList = new ArrayList<>();
		Map<Integer,Map<String,Object>> userLoc = userService.getUserLoc(userId);
		Double lng = (Double)userLoc.get(userId).get(Keys.lng);
		Double lat = (Double)userLoc.get(userId).get(Keys.lat);
		
		Integer noLocUserNum = 0;
		
		//判断该用户是否给了定位权限
		if(lat == Values.noLocLat){
			Integer schoolId = user.getSchoolId();
			Map<String,Object> schoolResult = userService.getNoLocMainUserBySchool(userId, sex, schoolId, onlineTime);
			List<UserPo> schoolUser = (List<UserPo>) schoolResult.get(Keys.mainUser);
			if(schoolUser != null){
				 distanceUserJSONList = keYuApi.getDistanceUserJSONList(schoolUser, userId);
				 resultJSON.accumulate(Keys.onlineTime, schoolResult.get(Keys.onlineTime));
			}else {
				resultJSON.accumulate(Keys.onlineTime, 0);
			}
		}else{
			//根据距离和时间获取用户
			Map<String,Object> distanceResult = userService.getMainUserByDistance(userId, lng, lat, distance, onlineTime, sex, openTime);
			List<UserPo> distanceUserList = new ArrayList<>(); 
			distanceUserList =(List<UserPo>) distanceResult.get(Keys.mainUser);
			
			//根据学校获取位开启定位功能的用户
			Map<String,Object> schoolResult = userService.getNoLocMainUserBySchool(userId, sex, user.getSchoolId(),onlineTime);
			List<UserPo> schoolUserList = (List<UserPo>) schoolResult.get(Keys.school);
			if(schoolUserList != null){
				distanceUserList.addAll(schoolUserList);
			}
			noLocUserNum = Values.onceSchoolUserNum;
			
			distanceUserJSONList = keYuApi.getDistanceUserJSONList(distanceUserList,userId);
			resultJSON.accumulate(Keys.onlineTime, distanceResult.get(Keys.onlineTime));
			resultJSON.accumulate(Keys.distance,distanceResult.get(Keys.distance));
			resultJSON.accumulate(Keys.openTime, openTime);
			
		}
		
		//获取点击喜欢的用户
		Map<String,Object> likeResult = userService.getMainUserByLike(userId, likeTime, sex);
		List<UserPo> likeUserList =  (List<UserPo>) likeResult.get(Keys.likeUser);
		List<JSONObject> likeUserJSONList = keYuApi.getLikeUserJSONList(likeUserList);
		resultJSON.accumulate(Keys.likeTime, likeResult.get(Keys.likeTime));
		
		//判断返回的用户是否已经是全部用户
		Integer distanceNum = distanceUserJSONList.size();
		Integer likeNum = likeUserJSONList.size();
		if(distanceNum < Values.onceUserNum + noLocUserNum && likeNum < Values.onceLikeUserNum){
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
	
	public JSONObject getClickLikeInfoToJSON(Map<String,String[]> parameters){
		JSONObject returnJSON = new JSONObject();
		JSONObject statusJSON = new JSONObject();
		JSONObject resultJSON = new JSONObject();
		Integer userId = Integer.valueOf(parameters.get(Keys.userId)[0]);
		Integer likeUserId = Integer.valueOf(parameters.get(Keys.likeUserId)[0]);
		Object clickResult = userService.findClickLikeResult(userId, likeUserId);
		if(clickResult instanceof LikePo){
			resultJSON.accumulate(Keys.isCanChat, Values.notLike);
		}else {
			UserPo user = userService.getUserByUserId(likeUserId);
			JSONObject userInfoJSON = keYuApi.userPoToJSON(user);
			ChatPo chatPo = (ChatPo)clickResult;
			JSONObject chatInfo = new JSONObject();
			chatInfo.accumulate(Keys.hasChat,chatPo.getIsStartChat());
			chatInfo.accumulate(Keys.intimacy, chatPo.getIntimacyB());
			chatInfo.accumulate(Keys.startChatDate, chatPo.getStartTime().getTime());
			chatInfo.accumulate(Keys.chatId, chatPo.getId());
			chatInfo.accumulate(Keys.deleteUserId, chatPo.getDeleteUserId());
			userInfoJSON.put(Keys.chatInfo, chatInfo);
			resultJSON.put(Keys.chatUser, userInfoJSON);
			resultJSON.accumulate(Keys.isCanChat, Values.liked);
		}
		statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
		
		returnJSON.put(Keys.status, statusJSON);
		returnJSON.put(Keys.result, resultJSON);
		return returnJSON;
	}
	
	public JSONObject getDeleteUserInfoToJSON(Map<String,String[]> parameters){
		JSONObject returnJSON = new JSONObject();
		JSONObject statusJSON = new JSONObject();
		JSONObject resultJSON = new JSONObject();
		
		
		return returnJSON;
	}
}
