package com.qiangu.keyu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiangu.keyu.dao.TestDao;
import com.qiangu.keyu.po.CitiesCoding;
import com.qiangu.keyu.po.ProvinceCoding;
import com.qiangu.keyu.po.SchoolCoding;
import com.qiangu.keyu.po.SchoolTypeCoding;
import com.qiangu.keyu.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testdao;
	
	public void setTestdao(TestDao testdao) {
		this.testdao = testdao;
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

}
