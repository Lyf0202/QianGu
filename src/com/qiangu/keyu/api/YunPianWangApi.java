package com.qiangu.keyu.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONObject;

public class YunPianWangApi {

	public static final String okResult = "0";
	public static final String okMessage = "发送验证码成功";
	//同一手机号30秒内重复提交相同的内容
	public static final String limit_30_seconds = "8";
	public static final String limit_30_seconds_message = "同一手机号30秒内重复提交相同的内容";
	//同一手机号5分钟内重复提交相同的内容超过3次
	public static final String limit_5_minites = "9";
	public static final String limit_5_minites_message = "同一手机号5分钟内重复提交相同的内容超过3次";
	//24小时内同一手机号发送次数超过限制
	public static final String limit_24_hours = "17";
	public static final String limit_24_hours_message = "24小时内同一手机号发送次数超过限制";
	//不支持的国家地区
	public static final String limit_areas = "20";
	public static final String limit_areas_message = "不支持的国家地区";
	
	@Autowired
	private ReadXmlApi readXmlApi;
	
	//通用发送接口的http地址
    private String URI_SEND_SMS = "http://yunpian.com/v1/sms/send.json";
    //编码格式。发送编码格式统一用UTF-8
    private String ENCODING = "UTF-8";
    private String APIKEY = "";
    private String demoText = "";
    
    public YunPianWangApi() {
		// TODO Auto-generated constructor stub
	}
    /**
     * 基于HttpClient 4.3的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */
    public String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }
    
    /**
     * 通用接口发短信
     *
     * 
     * @param identifycode   　验证码
     * @param mobile 　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */
    public String sendSms(String identifycode, String mobile){
    	Map<String , String> map = readXmlApi.getYunPianWangInfo();
    	this.APIKEY = map.get("APIKEY");
    	this.demoText = map.get("text");
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", APIKEY);
        params.put("text", demoText.replaceAll("#code#", identifycode));
        params.put("mobile", mobile);
        JSONObject jsonObject = JSONObject.fromObject(post(URI_SEND_SMS, params));
        System.out.println(jsonObject);
        System.out.println("----------code = "+jsonObject.getString("code"));
        
        return jsonObject.getString("code");
    }
}
