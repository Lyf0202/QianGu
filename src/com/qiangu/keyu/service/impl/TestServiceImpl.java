package com.qiangu.keyu.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.api.BaiduMapApi;
import com.qiangu.keyu.api.HuanXinApi;
import com.qiangu.keyu.api.QiNiuYunApi;
import com.qiangu.keyu.api.ReadXmlApi;
import com.qiangu.keyu.api.UtilsApi;
import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.dao.AreaDao;
import com.qiangu.keyu.dao.ChatDao;
import com.qiangu.keyu.dao.InformDao;
import com.qiangu.keyu.dao.LabelDao;
import com.qiangu.keyu.dao.LoveManifestoDao;
import com.qiangu.keyu.dao.MongodbDao;
import com.qiangu.keyu.dao.PictureDao;
import com.qiangu.keyu.dao.TestDao;
import com.qiangu.keyu.dao.UserDao;
import com.qiangu.keyu.dao.UserUpdateDao;
import com.qiangu.keyu.po.AreasCoding;
import com.qiangu.keyu.po.AvatarPo;
import com.qiangu.keyu.po.ChatPo;
import com.qiangu.keyu.po.CitiesCoding;
import com.qiangu.keyu.po.LabelPo;
import com.qiangu.keyu.po.LikePo;
import com.qiangu.keyu.po.LoveManifestoPo;
import com.qiangu.keyu.po.PicturePo;
import com.qiangu.keyu.po.ProvinceCoding;
import com.qiangu.keyu.po.SchoolCoding;
import com.qiangu.keyu.po.SchoolTypeCoding;
import com.qiangu.keyu.po.UserLabelPo;
import com.qiangu.keyu.po.UserPo;
import com.qiangu.keyu.service.TestService;
import com.qiangu.keyu.service.UserService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testdao;

	@Autowired
	private ReadXmlApi readXmlApi;

	@Autowired
	private UserUpdateDao userUpdateDao;

	@Autowired
	private LabelDao labelDao;

	@Autowired
	private PictureDao pictureDao;

	@Autowired
	private MongodbDao mongodbDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AreaDao areaDao;

	@Autowired
	private ChatDao chatDao;

	@Autowired
	private UtilsApi utilsApi;

	@Autowired
	private HuanXinApi huanXinApi;

	@Autowired
	private QiNiuYunApi qiniuYunApi;

	@Autowired
	private LoveManifestoDao loveManifestoDao;
	
	@Autowired
	private InformDao informDao;

	public void setTestdao(TestDao testdao) {
		this.testdao = testdao;
	}

	public void setReadXmlApi(ReadXmlApi readXmlApi) {
		this.readXmlApi = readXmlApi;
	}

	@Override
	public void addProvince(ProvinceCoding provinceCoding) {
		// TODO Auto-generated method stub
		testdao.addProvince(provinceCoding);
	}

	@Override
	public ProvinceCoding getProvince(Integer id) {
		// TODO Auto-generated method stub
		return testdao.getProvince(id);
	}

	@Override
	public CitiesCoding getCity(Integer id) {
		// TODO Auto-generated method stub
		return testdao.getCity(id);
	}

	@Override
	public SchoolCoding getSchool(Integer id) {
		// TODO Auto-generated method stub
		return testdao.getSchool(id);
	}

	@Override
	public SchoolTypeCoding getSchoolType(Integer id) {
		// TODO Auto-generated method stub
		return testdao.getSchoolType(id);
	}

	@Override
	public void addSchoolType(SchoolTypeCoding schoolTypeCoding) {
		// TODO Auto-generated method stub
		testdao.addSchoolType(schoolTypeCoding);
	}

	@Override
	public void addUser(UserPo userPo) {
		// TODO Auto-generated method stub
		testdao.addUser(userPo);
	}

	@Override
	public void testXml() {
		readXmlApi.getBaiduMapAk();
	}

	@Override
	public void addTestBaseDao() {
		// TODO Auto-generated method stub
		// System.out.println(testdao.getT(UserPo.class, 2).getName());
		// System.out.println(testdao.getSession());
		// System.out.println(testdao.getCity(10));
		// UserPo user = new UserPo();
		// user.setName("王宝强");
		// testdao.save(user);
		String hql = "update UserPo set password = :password where sex = :sex";
		Map<String, Object> params = new HashMap<>();
		params.put("password", "123456");
		params.put("sex", 0);
		System.out.println(testdao.update(hql, params));
	}

	@Override
	public void getChatUserModel() {
		// TODO Auto-generated method stub
		testdao.getChatUser();
	}

	@Override
	public AreasCoding getAreaCoding(Integer id) {
		// TODO Auto-generated method stub
		return testdao.getAreaCoding(id);
	}

	@Override
	public void updateLabel() {
		List<Integer> ids = new ArrayList<>();
		ids.add(1);
		ids.add(2);
		labelDao.updateOldLabel(ids, 1);
	}

	@Override
	public void getMapUser() {
		List<UserPo> l = testdao.getMapUser();
		System.out.println("l = " + l);
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i).getId());
		}

	}

	@Override
	public void getTest() {
		// 1. -73.84 40.79
		// 2. -73.88 40.78 3550
		// 3. -73.92 40.79 6742
		// 4. -73.97 40.77 11182
		// 5. -73.824 40.729 6923
		// 6. -73.86 40.80 2019
		// 7. -73.82 40.80 2019
		// 8. -73.844785 40.792155 469
		// 9. -73.845480 40.799999 1205
		// 10. -73.839511 40.781452 952
		// 11. -73.850121 40.800012 1403
		mongodbDao.updateOrInsert(1, -73.84, 40.79);
		mongodbDao.updateOrInsert(2, -73.88, 40.78);
		mongodbDao.updateOrInsert(3, -73.92, 40.79);
		mongodbDao.updateOrInsert(4, -73.97, 40.77);
		mongodbDao.updateOrInsert(5, -73.824, 40.729);
		mongodbDao.updateOrInsert(6, -73.86, 40.80);
		mongodbDao.updateOrInsert(7, -73.82, 40.80);
		mongodbDao.updateOrInsert(8, -73.844785, 40.792155);
		mongodbDao.updateOrInsert(9, -73.845480, 40.799999);
		mongodbDao.updateOrInsert(10, -73.839511, 40.781452);
		mongodbDao.updateOrInsert(11, -73.850121, 40.800012);
	}

	@Override
	public void getTTTest() {
		System.out.println(informDao.getLastInformPicture(1));
	}

	@Override
	public void updateTest() throws Exception {
		// LabelPo l = new LabelPo();
		// l.setTypeId(888);
		// l.setLabelContent("nanananan");
		// labelDao.save(l);
		// System.out.println("-----------");
		//// throw new Exception();
		// int a = 10 / 0;
//		Integer i = chatDao.updateIntimacy(0, 9, 11, 12.125, 21.15);
//		System.out.println("--- " + i);
		// ChatPo c = chatDao.getT(ChatPo.class,9);
		// System.out.println(c);
		System.out.println(System.currentTimeMillis());
		Integer  i =userDao.updateLastOnlineTime(2);
		System.out.println("count = "+i);
	}

	@Override
	public void addUsers() {
		String[] strs = {"12345789","lalalala","你好吗","我很好","你真的好吗","我真的很好","哈哈哈哈","啦啦啦啦啊","妈咪妈咪哄","aaaaaaaa","hahahaahaha"};
		for (int j = 1; j <= 10; j++) {
			String huanxin = utilsApi.getUUID();
			String huanxinUsername = huanxin.substring(0, 15);
			String huanxinPassword = huanxin.substring(15, 32);
			
			Double lng = -12.12;
			Double lat = -12.12;

			if(j > 2 ){
				lng = Double.valueOf("120.006685");
				lat = Double.valueOf("30.29"+utilsApi.getRandomNum(4));
			}
			String avatar = "/root/test/photo/"+utilsApi.getRandomNum(1)+".jpg";
//			String avatar = "G:/photo/" + utilsApi.getRandomNum(1) + ".jpg";
			File file = new File(avatar);
			byte[] b = null;
			try {
				FileInputStream fis = new FileInputStream(file);
				b = new byte[(int) file.length()];
				fis.read(b);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String telephone = "1885711" + utilsApi.getRandomNum(4);
			UserPo user = new UserPo();
			user.setTelephone(telephone);
			user.setName("我是谁");
			user.setEducation("本科");
			if(j % 2 == 0){
				user.setSex(1);
			}else{
				user.setSex(0);
			}
			user.setEnterTime(2014);
			user.setLng(lng);
			user.setLat(lat);
			user.setSchoolId(929);
			user.setTalkId(huanxinUsername);
			user.setTalkPassword(huanxinPassword);
			userDao.save(user);

			PicturePo picturePo = new PicturePo();
			picturePo.setPictureName(telephone + "_1");
			pictureDao.save(picturePo);

			AvatarPo avatarPo = new AvatarPo();
			avatarPo.setPictureId(picturePo.getId());
			avatarPo.setUserId(user.getId());
			pictureDao.addAvatar(avatarPo);

			LoveManifestoPo loveManifestoPo = new LoveManifestoPo();
			loveManifestoPo.setLoveManifesto(strs[j]);
			loveManifestoPo.setUserId(user.getId());
			loveManifestoDao.save(loveManifestoPo);

			for (int i = 0; i <= 5; i++) {
				UserLabelPo userLabelPo = new UserLabelPo();
				userLabelPo.setUserId(user.getId());
				userLabelPo.setIsNow(1);
				userLabelPo.setLabelId(Integer.valueOf(utilsApi.getRandomNum(1)));
				labelDao.addUserLabel(userLabelPo);
			}

			if (huanXinApi.registerHuanXinId(huanxinUsername, huanxinPassword).equals(Values.yes)) {
				if (qiniuYunApi.pictureUpload(telephone + "_1", b)) {
					mongodbDao.updateOrInsert(user.getId(), lng, lat);
					System.out.println("success...");
				}
				
			}

		}
	}

	@Override
	public void updateTime(Integer first,Integer last) {
		// TODO Auto-generated method stub
		long t = 3 * 60 * 1000;
		long nowt = System.currentTimeMillis();
		int count = 1;
		for(int i = first ; i <= last ;i++){
			testdao.updateLastOnlinetime(nowt - t * count, i);
			count ++;
		}
	}

	@Override
	public void updateResetLikePo(Integer first,Integer last) {
		testdao.updateResetLikePo(first, last);
		
	}

	@Override
	public void updateResetChatPo(Integer first, Integer last) {
		// TODO Auto-generated method stub
		testdao.updateResetChatPo(first, last);
	}

}
