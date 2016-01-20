package com.qiangu.keyu.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.api.BaiduMapApi;
import com.qiangu.keyu.api.ReadXmlApi;
import com.qiangu.keyu.dao.TestDao;
import com.qiangu.keyu.po.CitiesCoding;
import com.qiangu.keyu.po.ProvinceCoding;
import com.qiangu.keyu.po.SchoolCoding;
import com.qiangu.keyu.po.SchoolTypeCoding;
import com.qiangu.keyu.po.UserPo;
import com.qiangu.keyu.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testdao;
	
	@Autowired
	private ReadXmlApi readXmlApi;
	
	
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

	

}
