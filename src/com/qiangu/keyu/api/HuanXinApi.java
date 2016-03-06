package com.qiangu.keyu.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Component;

import com.qiangu.keyu.controller.Values;

import net.sf.json.JSONObject;

@Component
public class HuanXinApi {

	public final String access_token = "access_token";
	public final String expires_in = "expires_in";
	public final String application = "application";
	
	private HttpClient httpClient;
	private Sqlite sqlite;
	
	private String AppKey_1 = "20150904";
	private String AppKey_2 = "qianguapp";
	private String clientId = "YXA6U-iCUFLQEeWV5X0sxk-O5g";
	private String clientSecret = "YXA6SiZouIHuo4pghnhMGhV6pn9SDIk";
	public HuanXinApi() {
		httpClient = new HttpClient();
		sqlite = new Sqlite();
	}
	
	public String getToken(){
		String token = null;
		String str = "";
		long nowtime = System.currentTimeMillis();
		String selectSql = "select * from huanxin where id = 1";
		List<Map<String, String>> list = sqlite.select(selectSql);
		long startTime = Long.parseLong(list.get(0).get("createTime"));
		long fulltime = (nowtime - startTime) / 1000;
		if (fulltime < 475200) {
			System.out.println("环信token有效");
			token = list.get(0).get(access_token);
		} else {
			System.out.println("环信token过期，重新获取");
			Map<String,String> tokenMap = updateToken();
			list = sqlite.select(selectSql);
			token = list.get(0).get(access_token);
		}
		return token;
	}
	
	public String registerHuanXinId(String username,String password){
		String token = getToken();
		String url = "http://a1.easemob.com/" + AppKey_1 + "/" + AppKey_2
				+ "/users";
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Content-Type", "application/json");
		post.setRequestHeader("Authorization", "Bearer "+token);
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("username", username)
				.accumulate("password", password);
		post.setRequestBody(jsonObject.toString());
		try {
			httpClient.executeMethod(post);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String response = "";
		try {
			response = post.getResponseBodyAsString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject1 = JSONObject.fromObject(response);
		if(jsonObject1.get("error") == null){
			return Values.yes;
		}else{
			LoggerApi.error(this, "fail: "+(String) jsonObject1.get("error"));
			return Values.no;
		}
	}
	
	public Map<String,String> updateToken(){
		String createTime = System.currentTimeMillis() + "";
		Map<String,String> tokenMap = new HashMap<String, String>();
		String url = "http://a1.easemob.com/" + AppKey_1 + "/" + AppKey_2
				+ "/token";
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Content-Type", "application/json");
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("grant_type", "client_credentials")
				.accumulate("client_id", clientId)
				.accumulate("client_secret", clientSecret);
		post.setRequestBody(jsonObject.toString());		
		try {
			httpClient.executeMethod(post);
		} catch (HttpException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		String str = "";
		try {
			str = post.getResponseBodyAsString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		//获取token
		JSONObject getTokenResult = JSONObject.fromObject(str);
		if(getTokenResult.get("error") == null){
			tokenMap.put(access_token, getTokenResult.getString(access_token));
			tokenMap.put(expires_in, getTokenResult.getString(expires_in));
			tokenMap.put(application, getTokenResult.getString(application));
		}else{
			return null;
		}
		
		//更新sqlite数据库
		String updateSql = "update huanxin set createTime = '" + createTime
				+ "' , access_token = '" + getTokenResult.getString(access_token) + "',"
				+ "expires_in = '" + getTokenResult.getString(expires_in) + "', application = '"
				+ getTokenResult.getString(application) + "' where id = 1";
		sqlite.update(updateSql);
		return tokenMap;
	}
	
	public static void main(String[] args) {
		HuanXinApi huanxinApi = new HuanXinApi();
//		huanxinApi.getToken();
		huanxinApi.registerHuanXinId("123456789", "abcdefg");
	}
}
