package hibernateTest;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qiangu.keyu.api.BaiduMapApi;
import com.qiangu.keyu.api.ReadXmlApi;
import com.qiangu.keyu.api.YunPianWangApi;

public class OtherTest {


	private BaiduMapApi baiduMapApi;
	
	public void setBaiduMapApi(BaiduMapApi baiduMapApi) {
		this.baiduMapApi = baiduMapApi;
	}
	
	public void ttest() throws HttpException, IOException{
		System.out.println("");
		System.out.println("......");
		System.out.println(baiduMapApi);
		System.out.println(baiduMapApi.getProvince("31.700452", "118.876993"));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("浙江理工大".contains("浙江理工大学"));
//		System.out.println(URLEncoder.encode("学院"));
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		YunPianWangApi yunPianWangApi = (YunPianWangApi) ac.getBean("yunPianWangApi");
		yunPianWangApi.sendSms("123456", "18857117310");
//		BaiduMapApi baiduMapApi = (BaiduMapApi) ac.getBean("baiduMapApi");
//		System.out.println(baiduMapApi.getBaiduSchool("30.318659","120.352625"));
//		ReadXmlApi readXmlApi = (ReadXmlApi) ac.getBean("readXmlApi");
//		System.out.println(readXmlApi.getYunPianWangInfo().get("text"));
	}

}
