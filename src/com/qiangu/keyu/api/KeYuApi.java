package com.qiangu.keyu.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.po.ChatPo;
import com.qiangu.keyu.po.LabelPo;
import com.qiangu.keyu.po.LoveManifestoPo;
import com.qiangu.keyu.po.SchoolCoding;
import com.qiangu.keyu.po.UserPo;
import com.qiangu.keyu.service.LabelService;
import com.qiangu.keyu.service.LoveManifestoService;
import com.qiangu.keyu.service.SchoolService;
import com.qiangu.keyu.service.UserService;

import net.sf.json.JSONObject;

public class KeYuApi {

	@Autowired
	private SchoolService schoolService;

	@Autowired
	private LoveManifestoService loveManifestoService;

	@Autowired
	private UserService userService;

	@Autowired
	private LabelService labelService;

	// 到百分百所需的总聊天句数（单个人）
	public static final double totalChatNum = 180;

	// 到百分百所需的总聊天时间
	public static final double totalChatTime = 3600 * 48;

	// 聊天时间所占百分比
	public static final double time = 0.1;
	// A的聊天句数所占比例
	public static final double chatA = 0.45;
	// B的聊天句数所占比例
	public static final double chatB = 0.45;

	/**
	 * 获取请密度
	 * 
	 * @param chatNumA
	 *            A的聊天句数
	 * @param chatNumB
	 *            B的聊天句数
	 * @param chatPo
	 * @return
	 */
	public Double getIntimacy(Integer chatNumA, Integer chatNumB, ChatPo chatPo) {
		Date startTime = chatPo.getStartTime();
		long chatTime = System.currentTimeMillis() - startTime.getTime();
		double timePercentage = (chatTime / 1000) / totalChatTime;
		timePercentage = timePercentage > 1 ? time : timePercentage * time;

		double chatAPercentage = chatNumA / totalChatNum;
		chatAPercentage = chatAPercentage > 1 ? chatA : chatAPercentage * chatA;

		double chatBPercentage = chatNumB / totalChatNum * chatB;
		chatBPercentage = chatBPercentage > 1 ? chatB : chatBPercentage * chatB;

		return timePercentage + chatAPercentage + chatBPercentage;
	}

	public JSONObject userPoToJSON(UserPo user) {
		JSONObject json = new JSONObject();
		json.accumulate(Keys.userId, user.getId());
		json.accumulate(Keys.sex, user.getSex());
		json.accumulate(Keys.name, user.getName());
		json.accumulate(Keys.birthday, user.getBirthday());
		json.accumulate(Keys.chatId, user.getTalkId());
		json.accumulate(Keys.lastLoginTime, user.getLastOnlineTime());
		json.accumulate(Keys.education, user.getEducation());
		json.accumulate(Keys.weight, user.getWeight());
		json.accumulate(Keys.height, user.getHeight());
		SchoolCoding school = schoolService.getSchoolById(user.getSchoolId());
		LoveManifestoPo loveManifestoPo = loveManifestoService.getLoveManifestoPoByUserId(user.getId());
		json.accumulate(Keys.school, school.getSchool_name());
		if (loveManifestoPo != null) {
			json.accumulate(Keys.motto, loveManifestoPo.getLoveManifesto());
		}
		List<LabelPo> labels = labelService.getLabels(user.getId());
		if (labels != null) {

			List<Map<Integer, String>> listLabels = new ArrayList<>();
			for (LabelPo l : labels) {
				Map<Integer, String> labelMap = new HashMap<>();
				labelMap.put(l.getId(), l.getLabelContent());
				listLabels.add(labelMap);
			}
			json.accumulate(Keys.labels, listLabels);
		}
		return json;
	}

	public JSONObject chatUserInfoToJSON(Map m) {
		Integer userId = (Integer) m.get(Keys.userId);
		UserPo u = userService.getUserByUserId(userId);
		JSONObject userJSON = userPoToJSON(u);
		JSONObject chatJSON = chatInfoToJSON(m);
		userJSON.put(Keys.chatInfo, chatJSON);
		return userJSON;
	}

	public JSONObject chatInfoToJSON(Map m) {
		JSONObject json = new JSONObject();
		json.accumulate(Keys.chatId, m.get(Keys.chatId));
		json.accumulate(Keys.startChatDate, ((Date) m.get(Keys.startChatDate)).getTime());
		json.accumulate(Keys.hasChat, m.get(Keys.hasChat));
		// json.accumulate(Keys.endTime, ((Date)m.get(Keys.endTime)).getTime());
		json.accumulate(Keys.deleteUserId, m.get(Keys.deleteUserId));
		json.accumulate(Keys.intimacy, m.get(Keys.intimacy));
		return json;
	}

	public List<JSONObject> getLikeUserJSONList(Integer userId, Long lastOnlinetime) {
		List<UserPo> likeUserList = userService.getMainUserByLike(userId, lastOnlinetime,1);
		List<JSONObject> likeUserJSONList = new ArrayList<>();
		for (UserPo u : likeUserList) {
			JSONObject uJSON = userPoToJSON(u);
			uJSON.accumulate(Keys.lng, u.getLng());
			uJSON.accumulate(Keys.lat, u.getLat());
			likeUserJSONList.add(uJSON);
		}
		return likeUserJSONList;
	}

	public List<JSONObject> getDistanceUserJSONList(Integer userId, Double lng, Double lat, Integer minDistance,
			Integer maxDistance, Long minOnlineTime, Long maxOnlineTime) {
		List<UserPo> distanceUserList = userService.getMainUserByDistance(lng, lat, userId, minDistance, maxDistance,
				minOnlineTime, maxOnlineTime,1);
		List<JSONObject> distanceUserJSONList = new ArrayList<>();
		for (UserPo u : distanceUserList) {
			if (u.getId() != userId) {
				JSONObject uJSON = userPoToJSON(u);
				uJSON.accumulate(Keys.lng, u.getLng());
				uJSON.accumulate(Keys.lat, u.getLat());
				distanceUserJSONList.add(uJSON);
			}
		}
		return distanceUserJSONList;
	}

	public List<JSONObject> getSchoolUserJSONList(Integer userId,Integer schoolId,long minOnlineTime, long maxOnlineTime) {
		List<UserPo> schoolUserList = userService.getMainUserBySchool(userId, schoolId, minOnlineTime, maxOnlineTime);
		List<JSONObject> schoolUserJSONList = new ArrayList<>();
		for (UserPo u : schoolUserList) {
			if (u.getId() != userId) {
				JSONObject uJSON = userPoToJSON(u);
				uJSON.accumulate(Keys.lng, u.getLng());
				uJSON.accumulate(Keys.lat, u.getLat());
				schoolUserJSONList.add(uJSON);
			}
		}
		return schoolUserJSONList;
	}
}
