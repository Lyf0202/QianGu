package hibernateTest;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qiangu.keyu.api.BaiduMapApi;
import com.qiangu.keyu.dao.BaseDao;
import com.qiangu.keyu.po.CitiesCoding;
import com.qiangu.keyu.po.ProvinceCoding;
import com.qiangu.keyu.po.SchoolTypeCoding;
import com.qiangu.keyu.po.UserPo;
import com.qiangu.keyu.service.TestService;


public class HibernateTest {

	private TestService testService;
	
	
	
	@Before
	public void before(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		testService = (TestService) ac.getBean("testServiceImpl");
		
	}
	
	@Test
	public void test() {
		System.out.println("table create successly");
		System.out.println(testService.getSchool(1));
//		ProvinceCoding provinceCoding = new ProvinceCoding();
//		provinceCoding.setProvince("白鹤");
//		provinceCoding.setProvinceId("1020");
//		testService.addProvince(provinceCoding);
	}
	
	@Test
	public void test1(){
		SchoolTypeCoding schoolTypeCoding = new SchoolTypeCoding();
		schoolTypeCoding.setSchoolTypeName("杭师");
		testService.addSchoolType(schoolTypeCoding);
		System.out.println("add successly");
	}
	
	@Test
	public void test2(){
		UserPo userPo = new UserPo();
		userPo.setSex(1);
		userPo.setName("科比");
		testService.addUser(userPo);
		System.out.println("successly!");
	}
	
	@Test
	public void test3(){
		testService.testXml();
	}

	@After
	public void after(){
		
	}
}
