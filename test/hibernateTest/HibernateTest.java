package hibernateTest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qiangu.keyu.api.QiNiuYunApi;
import com.qiangu.keyu.po.SchoolCoding;
import com.qiangu.keyu.po.SchoolTypeCoding;
import com.qiangu.keyu.po.UserPo;
import com.qiangu.keyu.service.SchoolService;
import com.qiangu.keyu.service.TestService;


public class HibernateTest {

	private TestService testService;
	
	private SchoolService schoolService;
	private Map<String,String[]> map;
	
	private QiNiuYunApi qiniu;
	@Before
	public void before(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		testService = (TestService) ac.getBean("testServiceImpl");
		schoolService = (SchoolService) ac.getBean("schoolServiceImpl");
		map = new HashMap<String,String[]>();
		qiniu = (QiNiuYunApi) ac.getBean("qiNiuYunApi");
	}
	
	@Test
	public void test() {
		
		List<SchoolCoding> l = schoolService.getDistanceSchool(929);
		System.out.println("------------------"+l.size());
		for(SchoolCoding s :l){
			System.out.println(s);
		}
		
	}
	
	@Test
	public void test1(){
		SchoolTypeCoding schoolTypeCoding = new SchoolTypeCoding();
		schoolTypeCoding.setSchoolTypeName("杭师");
		testService.addTestBaseDao();
		
		System.out.println("------------------------");
	}
	
	@Test
	public void test2() throws HttpException, IOException{
		String[] str1 = {"30.290539"};
		String[] str2 = {"120.006685"};
		map.put("lat", str1);
		map.put("lng", str2);
		List<String> s = schoolService.getLocationSchool(map);
		for(String school : s){
			System.out.println(school);
		}
		
		List<String> s1 = schoolService.getLocationSchool(map);
		for(String school : s1){
			System.out.println(school);
		}
	}
	
	@Test
	public void test3(){
		testService.getChatUserModel();
	}

	@After
	public void after(){
		
	}
}
