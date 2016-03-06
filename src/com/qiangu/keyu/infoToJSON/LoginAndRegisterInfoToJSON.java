package com.qiangu.keyu.infoToJSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qiangu.keyu.api.KeYuApi;
import com.qiangu.keyu.api.LoggerApi;
import com.qiangu.keyu.api.QiNiuYunApi;
import com.qiangu.keyu.api.YunPianWangApi;
import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.po.LabelPo;
import com.qiangu.keyu.po.LoveManifestoPo;
import com.qiangu.keyu.po.SchoolCoding;
import com.qiangu.keyu.po.UserPo;
import com.qiangu.keyu.service.ChatService;
import com.qiangu.keyu.service.LabelService;
import com.qiangu.keyu.service.LoveManifestoService;
import com.qiangu.keyu.service.PictureService;
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
	@Autowired
	private LabelService labelService;
	@Autowired
	private LoveManifestoService loveManifestoService;
	@Autowired
	private ChatService chatService;
	@Autowired
	private PictureService pictureService;
	@Autowired
	private KeYuApi keYuApi;
	@Autowired
	private QiNiuYunApi qiniuYunApi;

	public JSONObject sendMessageInfoToJSON(Map<String, String[]> parameters, String verificationCode) {
		JSONObject returnJSON = new JSONObject();
		JSONObject statusJSON = new JSONObject();
		// 发送验证码
		// String yunpianwangResult = yunPianWangApi.sendSms(verificationCode,
		// parameters.get(Keys.telephone)[0]);
		String yunpianwangResult = "0";
		// System.out.println("yunpianwangResult = " + yunpianwangResult);
		LoggerApi.info(this, "yunpianwangResult = " + yunpianwangResult);
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

	public JSONObject loginOrRegisterInfoToJSON(Map<String, String[]> parameters) {
		JSONObject returnJSON = new JSONObject();
		JSONObject statusJSON = new JSONObject();
		UserPo user = userService.getLoginOrRegisterUserInfo(parameters);
		if (user == null) {
			statusJSON.accumulate(Keys.status, Values.statusOfUserNotExist);
			statusJSON.accumulate(Keys.message, Values.messageOfUserNotExist);
		} else {
			statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
			JSONObject resultJSON = new JSONObject();
			JSONObject me = keYuApi.userPoToJSON(user);
			String avatarPicName = pictureService.getAvatar(user.getId()).getPictureName();
			String meAvatarPicDownloadUrl = qiniuYunApi.getDownloadUrl(avatarPicName);
			// me.accumulate(Keys.avatar,meAvatarPicDownloadUrl);
			String meLittlePicDownloadUrl = qiniuYunApi.getDownloadUrl(avatarPicName, QiNiuYunApi.width,
					QiNiuYunApi.height);
			// me.accumulate(Keys.AvatarLittleSizePicUrl,meLittlePicDownloadUrl);
			resultJSON.put(Keys.me, me);
			List<Map> chatMapList = chatService.getChatInfo(user.getId());
			List<JSONObject> chatUserList = new ArrayList<>();
			for (int i = 1; i <= chatMapList.size(); i++) {
				JSONObject chatUser = keYuApi.chatUserInfoToJSON(chatMapList.get(i - 1));
				chatUserList.add(chatUser);
			}
			resultJSON.put(Keys.chatUser, chatUserList);
			returnJSON.put(Keys.result, resultJSON);
		}
		returnJSON.put(Keys.status, statusJSON);

		return returnJSON;
	}

	public JSONObject completeRegisterInfoToJSON(Map<String, String> parameters, Map<String, byte[]> fileContents)
			throws Exception {
		JSONObject returnJSON = new JSONObject();
		JSONObject statusJSON = new JSONObject();
		JSONObject resultJSON;
		UserPo user = new UserPo();
		user.setTelephone(parameters.get(Keys.telephone));
		user.setSex(Integer.valueOf(parameters.get(Keys.sex)));
		user.setName(parameters.get(Keys.name));
		user.setEducation(parameters.get(Keys.education));
		user.setSchoolId(Integer.valueOf(parameters.get(Keys.school)));
		user.setLastOnlineTime(System.currentTimeMillis());
		String avatarName = parameters.get(Keys.telephone) + "_" + 1;
		Double lng = Double.valueOf(parameters.get(Keys.lng));
		Double lat = Double.valueOf(parameters.get(Keys.lat));
		user.setLng(lng);
		user.setLat(lat);
		if (userService.addUserToRegister(user, fileContents.get(Keys.avatar)).equals(Values.yes)) {
			statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
		} else {
			statusJSON.accumulate(Keys.status, Values.statusOfServiceError);
			statusJSON.accumulate(Keys.message, Values.messageOfServiceError);
		}
		returnJSON.put(Keys.status, statusJSON);

		return returnJSON;
	}

}
