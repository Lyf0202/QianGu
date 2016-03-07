package com.qiangu.keyu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.api.BaiduMapApi;
import com.qiangu.keyu.api.ReadXmlApi;
import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.dao.AreaDao;
import com.qiangu.keyu.dao.ChatDao;
import com.qiangu.keyu.dao.LabelDao;
import com.qiangu.keyu.dao.MongodbDao;
import com.qiangu.keyu.dao.PictureDao;
import com.qiangu.keyu.dao.TestDao;
import com.qiangu.keyu.dao.UserDao;
import com.qiangu.keyu.dao.UserUpdateDao;
import com.qiangu.keyu.po.AreasCoding;
import com.qiangu.keyu.po.CitiesCoding;
import com.qiangu.keyu.po.LabelPo;
import com.qiangu.keyu.po.LikePo;
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
	
	@Autowired
	private AreaDao areaDao;
	
	@Autowired
	private ChatDao chatDao;
	
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
				// 1.  -73.84  40.79
				// 2.  -73.88  40.78    3550
				// 3.  -73.92  40.79     6742
				// 4.  -73.97  40.77     11182
				// 5.  -73.824  40.729     6923
				// 6.  -73.86   40.80     2019
				// 7.  -73.82   40.80     2019
				// 8.  -73.844785  40.792155   469
				// 9.  -73.845480  40.799999    1205
				// 10.  -73.839511  40.781452   952
				// 11.  -73.850121  40.800012   1403
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
//		Map<Integer,Map<String,Object>> m = mongodbDao.findByDistance(0, 10, -12.12, -12.12);
//		System.out.println(m.keySet());
		Map m = areaDao.getHometown("210602");
		System.out.println(m.get(Keys.province));
		System.out.println(m.get(Keys.city));
		System.out.println(m.get(Keys.area));
		
//		int a = 10 / 0;
		
	}

	@Override
	public void updateTest() throws Exception {
//		LabelPo l = new LabelPo();
//		l.setTypeId(888);
//		l.setLabelContent("nanananan");
//		labelDao.save(l);
//		System.out.println("-----------");
////		throw new Exception();
//		int a = 10 / 0;
		Integer i = chatDao.deleteChatUser(-1, -2);
		System.out.println("--- "+ i);
	}

	

}
