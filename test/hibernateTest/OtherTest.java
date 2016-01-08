package hibernateTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qiangu.keyu.api.BaiduMapApi;
import com.qiangu.keyu.api.ReadXmlApi;

public class OtherTest {


	private BaiduMapApi baiduMapApi;
	
	public void setBaiduMapApi(BaiduMapApi baiduMapApi) {
		this.baiduMapApi = baiduMapApi;
	}
	
	public void ttest(){
		System.out.println("");
		System.out.println("......");
		System.out.println(baiduMapApi);
		System.out.println(baiduMapApi.getProvince("31.700452", "118.876993"));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		BaiduMapApi baiduMapApi = (BaiduMapApi) ac.getBean("baiduMapApi");
		System.out.println(baiduMapApi.getBaiduSchool("30.318659","120.352625"));
//		ReadXmlApi readXmlApi = (ReadXmlApi) ac.getBean("readXmlApi");
//		System.out.println(readXmlApi.getYunPianWangInfo().get("text"));
	}

}
