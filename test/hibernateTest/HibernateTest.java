package hibernateTest;

import static org.junit.Assert.*;

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

import com.qiangu.keyu.po.SchoolTypeCoding;
import com.qiangu.keyu.service.SchoolService;
import com.qiangu.keyu.service.TestService;


public class HibernateTest {

	private TestService testService;
	
	private SchoolService schoolService;
	private Map<String,String> map;
	
	@Before
	public void before(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		testService = (TestService) ac.getBean("testServiceImpl");
		schoolService = (SchoolService) ac.getBean("schoolServiceImpl");
		map = new HashMap<String,String>();
	}
	
	@Test
	public void test() {
		System.out.println("table create successly");
		System.out.println(testService.getSchool(1));
		
		System.out.println(testService.getSchool(1));
	}
	
	@Test
	public void test1(){
		SchoolTypeCoding schoolTypeCoding = new SchoolTypeCoding();
		schoolTypeCoding.setSchoolTypeName("杭师");
		testService.addSchoolType(schoolTypeCoding);
		System.out.println("add successly");
	}
	
	@Test
	public void test2() throws HttpException, IOException{
		map.put("lat", "28.653412");
		map.put("lng","121.380412");
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
		
	}

	@After
	public void after(){
		
	}
}
