package com.qiangu.keyu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.api.BaiduMapApi;
import com.qiangu.keyu.api.ReadXmlApi;
import com.qiangu.keyu.dao.LabelDao;
import com.qiangu.keyu.dao.MongodbDao;
import com.qiangu.keyu.dao.PictureDao;
import com.qiangu.keyu.dao.TestDao;
import com.qiangu.keyu.dao.UserDao;
import com.qiangu.keyu.dao.UserUpdateDao;
import com.qiangu.keyu.po.AreasCoding;
import com.qiangu.keyu.po.CitiesCoding;
import com.qiangu.keyu.po.PicturePo;
import com.qiangu.keyu.po.ProvinceCoding;
import com.qiangu.keyu.po.SchoolCoding;
import com.qiangu.keyu.po.SchoolTypeCoding;
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
	private PictureDao pictureDao ;
	
	@Autowired
	private MongodbDao mongodbDao;
	
	@Autowired
	private UserDao userDao;
	
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
	public void testXml(){
		readXmlApi.getBaiduMapAk();
	}

	@Override
	public void addTestBaseDao() {
		// TODO Auto-generated method stub
//		System.out.println(testdao.getT(UserPo.class, 2).getName());
//		System.out.println(testdao.getSession());
//		System.out.println(testdao.getCity(10));
//		UserPo user = new UserPo();
//		user.setName("王宝强");
//		testdao.save(user);
		String hql = "update UserPo set password = :password where sex = :sex";
		Map<String,Object> params = new HashMap<>();
		params.put("password","123456");
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
		System.out.println("l = "+l);
		for(int i = 0 ; i< l.size();i++){
			System.out.println(l.get(i).getId());
		}
		
	}

	@Override
	public void getTest() {
		List<Integer> distanceId = new ArrayList<>();
//		for(int i= 1 ; i <= 11 ;i++){
//			distanceId.add(i);
//		}
		long minOnlineTime = Long.valueOf("1456643744123");
		long maxOnlineTime = Long.valueOf("1456643744987");
		List<UserPo> userL = userDao.getUserByDistance(distanceId, minOnlineTime, maxOnlineTime, 1, 1,20,0);
		System.out.println("userL.size() = "+userL.size());
		for(UserPo u : userL){
			System.out.println(u.getId());
		}
//		long lastOnlineTime = Long.valueOf("1456643744123");
//		List<UserPo> ulist = userDao.getUserByLikeUserId(1, lastOnlineTime, 0,20,0);
//		System.out.println("ulist.size() = "+ ulist.size());
//		for(UserPo u : ulist){
//			System.out.println(u.getId());
//		}
	}

	

}
