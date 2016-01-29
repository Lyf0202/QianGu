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
import com.qiangu.keyu.api.Sqlite;
import com.qiangu.keyu.dao.LoveManifestoDao;
import com.qiangu.keyu.po.AvatarPo;
import com.qiangu.keyu.po.ChatPo;
import com.qiangu.keyu.po.LabelPo;
import com.qiangu.keyu.po.LoveManifestoPo;
import com.qiangu.keyu.po.SchoolCoding;
import com.qiangu.keyu.po.SchoolTypeCoding;
import com.qiangu.keyu.po.UserPo;
import com.qiangu.keyu.service.ChatService;
import com.qiangu.keyu.service.LabelService;
import com.qiangu.keyu.service.LoveManifestoService;
import com.qiangu.keyu.service.PictureService;
import com.qiangu.keyu.service.SchoolService;
import com.qiangu.keyu.service.TestService;
import com.qiangu.keyu.service.UserService;


public class HibernateTest {

	private TestService testService;
	
	private SchoolService schoolService;
	private Map<String,String[]> map;
	private UserService userService;
	private LoveManifestoService loveManifestoService;
	private QiNiuYunApi qiniu;
	private LabelService labelService;
	private ChatService chatService;
	private PictureService pic;
	private LoveManifestoDao lo;
	@Before
	public void before(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		testService = (TestService) ac.getBean("testServiceImpl");
		schoolService = (SchoolService) ac.getBean("schoolServiceImpl");
		map = new HashMap<String,String[]>();
		qiniu = (QiNiuYunApi) ac.getBean("qiNiuYunApi");
		loveManifestoService = (LoveManifestoService) ac.getBean("loveManifestoServiceImpl");
		userService = (UserService) ac.getBean("userServiceImpl");
		labelService = (LabelService) ac.getBean("labelServiceImpl");
		chatService = (ChatService) ac.getBean("chatServiceImpl");
		lo = (LoveManifestoDao) ac.getBean("loveManifestoDaoImpl");
		pic = (PictureService) ac.getBean("pictureServiceImpl");
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
	public void tt(){
//		List<ChatPo> l = chatService.getChat(1);
//		for(ChatPo p :l){
//			System.out.println(p);
//		}
		AvatarPo a= new AvatarPo();
		a.setPictureId(123);
		pic.addAvatar(1,"123", null);
	}
	@Test
	public void test1(){
		Sqlite sqlite = new Sqlite();
		for(int i = 5 ; i <= 2734 ; i++){
			SchoolCoding school = schoolService.getSchoolById(i);
			if(school != null){
				String schoolName = school.getSchool_name();
				int proId = school.getSchool_pro_id();
				int typeId = school.getSchool_schooltype_id();
				String insertSql = "insert into school values ("+i+",'"+schoolName+"',"+proId+","+typeId+")";
				sqlite.insert(insertSql);
				System.out.println("success "+i);
			}
		}
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
		Map<String, String[]> parameters = new HashMap<>();
		String[] telephone = {"18857117310"};
		parameters.put("telephone", telephone);
		System.out.println(userService.getLoginOrRegisterUserInfo(parameters));
	}

	@After
	public void after(){
		
	}
}
