package com.qiangu.keyu.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.api.QiNiuYunApi;
import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.dao.ChatDao;
import com.qiangu.keyu.dao.LikeDao;
import com.qiangu.keyu.dao.MongodbDao;
import com.qiangu.keyu.dao.PictureDao;
import com.qiangu.keyu.dao.UserDao;
import com.qiangu.keyu.po.AvatarPo;
import com.qiangu.keyu.po.ChatPo;
import com.qiangu.keyu.po.LikePo;
import com.qiangu.keyu.po.PicturePo;
import com.qiangu.keyu.po.UserPo;
import com.qiangu.keyu.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private MongodbDao mongodbDao;

	@Autowired
	private LikeDao likeDao;

	@Autowired
	private ChatDao chatDao;
	
	@Autowired
	private QiNiuYunApi qiniuYunApi;
	
	@Autowired
	private PictureDao pictureDao;

	@Override
	public UserPo getLoginOrRegisterUserInfo(Map<String, String[]> parameters) {
		String telephone = parameters.get(Keys.telephone)[0];

		UserPo user = userDao.getUserByTelephone(telephone);
		return user;
	}

	@Override
	public List<UserPo> getChatUserInfo(List<Map> chatList) {
		List<Integer> list = new ArrayList<>();
		for (Map m : chatList) {
			list.add((Integer) m.get(Keys.userId));
		}
		for (int i = list.size(); i < Values.chatNum - 1; i++) {
			list.add(null);
		}
		return userDao.getChatUsersByIds(list);
	}

	@Override
	public UserPo getUserByUserId(Integer userId) {

		return userDao.getUserByUserId(userId);
	}

	@Override
	public Serializable addUser(UserPo user) {
		return userDao.save(user);
	}
	
	@Override
	public String addUserToRegister(UserPo user, byte[] avatarContent) throws Exception {
		//将用户数据写入数据库
		userDao.save(user);
		
		//将头像信息保存到数据库
		String avatarName = user.getTelephone() + "_" + 1;
		PicturePo picture = new PicturePo();
		picture.setPictureName(avatarName);
		pictureDao.addPicture(picture);
		
		//将用户和该头像绑定起来
		AvatarPo avatar = new AvatarPo();
		avatar.setUserId(user.getId());
		avatar.setPictureId(picture.getId());
		pictureDao.addAvatar(avatar);
		
		//如果头像上传七牛云成功,则返回yes,否则抛出异常事务回滚
		if (qiniuYunApi.pictureUpload(avatarName, avatarContent)) {
			return Values.yes;
		}else{
//			throw new Exception();
			int a = 10 / 0;
		}
		return Values.no;
	}
	
	@Override
	public UserPo getOpenAppUser(Integer userId, Double lng, Double lat) {
		// mongodbDao.updateOrInsert(userId, lng, lat);
		// 11182
		// mongodbDao.updateOrInsert(-1,-73.97,40.77);
		// //3550
		// mongodbDao.updateOrInsert(-2, -73.88,40.78);
		// //6742
		// mongodbDao.updateOrInsert(-3,-73.92,40.79);
		List<Integer> userIds = new ArrayList<>();
		userIds.add(-1);
		userIds.add(-2);
		Map<Integer, Map<String, Object>> m = mongodbDao.findByArray(userIds);
		System.out.println("m.size() = " + m.size());
		for (Integer i : m.keySet()) {
			System.out.println("-----");
			System.out.println(i);
		}
		return null;
	}

	@Override
	public Object addUserLoc(Integer userId, Double lng, Double lat, Integer type) {
		List<Integer> distanceId = new ArrayList<>();
		for (int i = 1; i < 10; i += 2) {
			distanceId.add(i);
		}
		long minOnlineTime = System.currentTimeMillis() - 5 * 3600 * 1000;
		long maxOnlineTime = System.currentTimeMillis();
		List<UserPo> listU = userDao.getUserByDistance(distanceId, minOnlineTime, maxOnlineTime, 1, 1, 20, 0);

		System.out.println("------------" + listU.size());
		for (UserPo u : listU) {
			System.out.println(u.getId());
		}
		String str = "result = ";

		return str;
	}

	@Override
	public Map<Integer, Map<String, Object>> getUserLoc(Integer userId) {

		return mongodbDao.findByUserId(userId);
	}

	@Override
	public void addUserLoc(Integer userId, Double lng, Double lat) {

		mongodbDao.updateOrInsert(userId, lng, lat);
	}

	@Override
	public Map<String, Object> getMainUserByDistance(Integer userId, Double lng, Double lat, Integer distance,
			long onlineTime, Integer sex, long openTime) {
		Map<String, Object> mainUser = new HashMap<>();
		List<UserPo> listU = new ArrayList<>();
		Integer firstSelectNum = 0;
		Integer selectNum = Values.onceUserNum;
		Integer minDistance = distance;
		Integer maxDistance = minDistance + Values.onceDistance;
		long maxOnlineTime = onlineTime;
		long minOnlineTime = onlineTime - Values.halfHour;
		Map<Integer, Map<String, Object>> distanceUser = distanceUser = mongodbDao.findByDistance(minDistance,
				maxDistance, lng, lat);
		List<Integer> distanceUserId = new ArrayList<Integer>(distanceUser.keySet());
		System.out.println(minDistance + " " + maxDistance + " : " + new ArrayList<Integer>(distanceUser.keySet()));
		while (listU.size() < Values.onceUserNum) {
			if (maxDistance >= Values.Distance) {
				break;
			}

			if (distanceUserId.size() <= Values.onceUserNum) {
				if (distanceUserId.size() <= 0) {
					minDistance = maxDistance;
					maxDistance = maxDistance + Values.onceDistance;
					distanceUser = mongodbDao.findByDistance(minDistance, maxDistance, lng, lat);
					distanceUserId = new ArrayList<Integer>(distanceUser.keySet());
					System.out.println(
							minDistance + " " + maxDistance + " : " + new ArrayList<Integer>(distanceUser.keySet()));
				} else {
					minOnlineTime = onlineTime - Values.OnlineTime;
					List<UserPo> listUser = userDao.getUserByDistance(distanceUserId, minOnlineTime, maxOnlineTime,
							userId, sex, selectNum, firstSelectNum);
					if(listUser != null && listUser.size() > 0){
						listU.addAll(listUser);
					}
					minDistance = maxDistance;
					maxDistance = maxDistance + Values.onceDistance;
					distanceUser = mongodbDao.findByDistance(minDistance, maxDistance, lng, lat);
					distanceUserId = new ArrayList<Integer>(distanceUser.keySet());
					System.out.println(
							minDistance + " " + maxDistance + " : " + new ArrayList<Integer>(distanceUser.keySet()));
				}
			} else {
				if (minOnlineTime <= openTime - Values.OnlineTime) {
					minDistance = maxDistance;
					maxDistance = maxDistance + Values.onceDistance;
					maxOnlineTime = openTime;
					minOnlineTime = maxOnlineTime - Values.halfHour;
					distanceUser = mongodbDao.findByDistance(minDistance, maxDistance, lng, lat);
					distanceUserId = new ArrayList<Integer>(distanceUser.keySet());
					System.out.println(
							minDistance + " " + maxDistance + " : " + new ArrayList<Integer>(distanceUser.keySet()));
				}
				if(distanceUserId.size() > 0){
					List<UserPo> listUser = userDao.getUserByDistance(distanceUserId, minOnlineTime, maxOnlineTime, userId,
						sex, selectNum, firstSelectNum);
					if(listUser != null && listUser.size() > 0){
						listU.addAll(listUser);
					}
				}
				maxOnlineTime = minOnlineTime;
				minOnlineTime = minOnlineTime - Values.halfHour;
			}
			selectNum = Values.onceUserNum - listU.size();
		}
		mainUser.put(Keys.mainUser, listU);
		mainUser.put(Keys.distance, minDistance);
		if (listU.size() > 0) {
			mainUser.put(Keys.onlineTime, listU.get(listU.size() - 1).getLastOnlineTime());
		} else {
			mainUser.put(Keys.onlineTime, 0);
		}
		return mainUser;
	}

	@Override
	public Map<String, Object> getMainUserByLike(Integer userId, Long likeTime, Integer sex) {
		Map<String, Object> likeUser = new HashMap<>();
		Integer firstSelectNum = 0;
		Integer selectNum = Values.onceLikeUserNum;
		List<UserPo> listUser = userDao.getUserByLikeUserId(userId, likeTime, sex, selectNum, firstSelectNum);
		likeUser.put(Keys.likeUser, listUser);
		if (listUser.size() > 0) {
			LikePo likePo = likeDao.getLikePoByUserIdAndLikeuserId(listUser.get(listUser.size() - 1).getId(), userId);
			likeUser.put(Keys.likeTime, likePo.getLikeTime());
		} else {
			likeUser.put(Keys.likeTime, 0);
		}
		return likeUser;
	}

	@Override
	public Map<String, Object> getNoLocMainUserBySchool(Integer userId, Integer sex, Integer schoolId,
			long maxLastOnlineTime) {
		Map<String, Object> schoolUser = new HashMap<>();
		Integer firstSelectNum = 0;
		Integer selectNum = Values.onceSchoolUserNum;

		Map<Integer, Map<String, Object>> noLocUser = mongodbDao.findByNoLocAndSchool();
		List<Integer> noLocUserIds = new ArrayList<Integer>(noLocUser.keySet());
		if (noLocUser.size() != 0) {
			List<UserPo> listUser = userDao.getUserBySchool(schoolId, noLocUserIds, maxLastOnlineTime, userId, sex,
					selectNum, firstSelectNum);
			schoolUser.put(Keys.schoolUser, listUser);
		} else {
			schoolUser.put(Keys.schoolUser, null);
		}
		return schoolUser;
	}

	@Override
	public Object findClickLikeResult(Integer userId, Integer likeUserId) {
		LikePo likePo = likeDao.getLikePoByUserIdAndLikeuserId(likeUserId, userId);
		if (likePo == null) {
			likePo = new LikePo();
			likePo = new LikePo();
			likePo.setLikeTime(System.currentTimeMillis());
			likePo.setUserId(userId);
			likePo.setLikeUserId(likeUserId);
			// likeDao.save(likePo);
			return likePo;
		} else {
			// likePo.setIsSuccess(Values.liked);
			ChatPo chatPo = new ChatPo();
			chatPo.setIsStartChat(Values.notStartChat);
			chatPo.setStartTime(new Date());
			chatPo.setUserAId(likeUserId);
			chatPo.setUserBId(userId);
			chatPo.setIntimacyA(Values.startIntimacy);
			chatPo.setIntimacyB(Values.startIntimacy);
			// chatDao.save(chatPo);
			return chatPo;
		}
	}

	@Override
	public Map<String, Object> getMainUserBySchool(Integer userId, Integer sex, Integer schoolId,
			long maxLastOnlineTime) {
		Map<String, Object> mainUser = new HashMap<>();
		Integer firstSelectNum = 0;
		Integer selectNum = Values.onceDistance;
		List<UserPo> listU = userDao.getUserBySchool(userId, schoolId, maxLastOnlineTime, sex, selectNum, firstSelectNum);
		if(listU != null){
			mainUser.put(Keys.mainUser, listU);
			mainUser.put(Keys.onlineTime, listU.get(listU.size() - 1).getLastOnlineTime());
		}else {
			mainUser.put(Keys.mainUser,null);
		}
		return mainUser;
	}


}
