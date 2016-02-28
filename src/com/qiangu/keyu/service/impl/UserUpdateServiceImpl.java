package com.qiangu.keyu.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.api.QiNiuYunApi;
import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.dao.LabelDao;
import com.qiangu.keyu.dao.LoveManifestoDao;
import com.qiangu.keyu.dao.PictureDao;
import com.qiangu.keyu.dao.UserUpdateDao;
import com.qiangu.keyu.po.AvatarPo;
import com.qiangu.keyu.po.LabelPo;
import com.qiangu.keyu.po.LoveManifestoPo;
import com.qiangu.keyu.po.PicturePo;
import com.qiangu.keyu.po.UserLabelPo;
import com.qiangu.keyu.service.UserUpdateService;

@Service
public class UserUpdateServiceImpl implements UserUpdateService {

	@Autowired
	private UserUpdateDao userUpdateDao;

	@Autowired
	private PictureDao pictureDao;

	@Autowired
	private LoveManifestoDao loveManifestoDao;

	@Autowired
	private QiNiuYunApi qiNiuYunAPI;

	@Autowired
	private LabelDao labelDao;

	@Override
	public Boolean updateName(Map<String, String[]> parameters) {
		String name = parameters.get(Keys.name)[0];
		Integer userId = Integer.valueOf(parameters.get(Keys.userId)[0]);
		Integer daoResult = userUpdateDao.updateName(name, userId);
		if (daoResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateAvatar(Integer userId, byte[] fileContent) {
		PicturePo p = pictureDao.getUserAvatar(userId);
		String newAvatarName = null;
		if (p != null) {
			newAvatarName = getNewAvatarName(p.getPictureName());
			PicturePo np = new PicturePo();
			np.setPictureName(newAvatarName);
			pictureDao.save(np);
			AvatarPo avatar = new AvatarPo();
			avatar.setUserId(userId);
			avatar.setPictureId(np.getId());
			pictureDao.addAvatar(avatar);
			qiNiuYunAPI.pictureUpload(newAvatarName, fileContent);
			return true;
		} else {
			return false;
		}
	}

	public String getNewAvatarName(String oldAvatarName) {
		String[] str = oldAvatarName.split("_");
		int avatarNum = Integer.valueOf(str[1]);
		String newAvatarName = str[0] + "_" + ++avatarNum;
		return newAvatarName;
	}

	@Override
	public Boolean updateHeight(Integer userId, Double newHeight) {
		Integer daoResult = userUpdateDao.updateHeight(newHeight, userId);
		if (daoResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateBirthday(Integer userId, String newBirthday) {
		Integer daoResult = userUpdateDao.updateBirthday(newBirthday, userId);
		if (daoResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateWeight(Integer userId, Double newWeight) {
		Integer daoResult = userUpdateDao.updateWeight(newWeight, userId);
		if (daoResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateLoveManifesto(Integer userId, String loveManifesto) {
		LoveManifestoPo loveManifestoPo = new LoveManifestoPo();
		loveManifestoPo.setLoveManifesto(loveManifesto);
		loveManifestoPo.setUserId(userId);
		Serializable saveResult = loveManifestoDao.save(loveManifestoPo);
		if (saveResult != null && Integer.valueOf(saveResult.toString()) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean updateHometown(Integer userId, Integer areaId) {
		Integer daoResult = userUpdateDao.updateHometown(areaId, userId);
		if (daoResult > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Object> updateLabel(Integer userId, String[] oldLabel, String[] officialLabels,
			String[] userLabels) {
		List<Object> listU = null;
		if (!oldLabel[0].equals(Values.noLabels)) {
			List<Integer> labelIds = new ArrayList<>();
			for (String s : oldLabel) {
				labelIds.add(Integer.valueOf(s));
			}
			if (labelDao.updateOldLabel(labelIds, userId) > 0) {
				listU = new ArrayList<>();	
			}
		}
		
		if (!officialLabels[0].equals(Values.noLabels)) {
			listU = new ArrayList<>();	
			for (String s : officialLabels) {
				UserLabelPo uL = new UserLabelPo();
				uL.setIsNow(Values.isNow);
				uL.setLabelId(Integer.valueOf(s));
				uL.setUserId(userId);
				labelDao.addUserLabel(uL);
				listU.add(s);
			}
		}
		if (!userLabels[0].equals(Values.noLabels)) {
			listU = new ArrayList<>();	
			for (String s : userLabels) {
				LabelPo l = new LabelPo();
				l.setLabelContent(s);
				l.setTypeId(Values.userLabels);
				labelDao.save(l);
				UserLabelPo uL = new UserLabelPo();
				uL.setIsNow(Values.isNow);
				uL.setLabelId(l.getId());
				uL.setUserId(userId);
				labelDao.addUserLabel(uL);
				listU.add(s);
			}
		}

		return listU;
	}

	@Override
	public Boolean updateLikeUserOrder(Integer userId, Integer likeUserOrder) {
		Integer daoResult = userUpdateDao.updateLikeUserOrder(likeUserOrder, userId);
		if (daoResult > 0) {
			return true;
		} else {
			return false;
		}
	}

}
