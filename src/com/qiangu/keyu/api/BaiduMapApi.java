package com.qiangu.keyu.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONObject;

public class BaiduMapApi {

	public final String results = "results";
	public final String status = "status";
	public final String message = "message";
	
	private String ak;
	private HttpClient client;
	private String fullURL;
	@Autowired
	private ReadXmlApi readXmlApi;

	public void setReadXmlApi(ReadXmlApi readXmlApi) {
		this.readXmlApi = readXmlApi;
	}
	public BaiduMapApi() {
		client = new HttpClient();
	}

	/**
	 * 将IOS获取到的经纬度转换成百度经纬度
	 * @param lat
	 * @param lon
	 * @param coordsFrom
	 * @param coordsTo
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public Map<String, String> changeLatAndLon(String lat, String lon, String coordsFrom, String coordsTo) throws HttpException, IOException {
		ak = readXmlApi.getBaiduMapAk().get(readXmlApi.BaiduAk);
		System.out.println("ak = "+ak);
		String url = "http://api.map.baidu.com/geoconv/v1/";
		fullURL = url + "?coords=" + lat + "," + lon + "&from=" + coordsFrom + "&to=" + coordsTo + "&ak=" + ak;
		JSONObject jsonObject = getBaiduResult(fullURL);
		System.out.println("jsonObject.toString() ==== "+jsonObject.toString());
		List<JSONObject> coordsList = jsonObject.getJSONArray("result");
		Map<String, String> coords = new HashMap<String, String>();
		coords.put("lat", String.valueOf(coordsList.get(0).get("x")));
		coords.put("lon", String.valueOf(coordsList.get(0).get("y")));
		return coords;
	}
	
	/**
	 * 根据经纬度得到对应的省份
	 * @param lat
	 * @param lng
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public String getProvince(String lat, String lng) throws HttpException, IOException{
		Map<String , String> baiduLatAndLon = changeLatAndLon(lat, lng,"3","5");
		lat = baiduLatAndLon.get("lat");
		lng = baiduLatAndLon.get("lon");
		ak = readXmlApi.getBaiduMapAk().get(readXmlApi.BaiduAk);
		String url = "http://api.map.baidu.com/geocoder/v2/";
		String output = "json";
		String pois = "1";
		fullURL = url + "?ak=" + ak + "&location=" + lat + "," + lng + "&output="
				+ output + "&pois=" + pois;
		JSONObject jsonObject = getBaiduResult(fullURL);
		JSONObject address = jsonObject.getJSONObject("result");
		JSONObject province = address.getJSONObject("addressComponent");
		return province.getString("province");
	}
	
	/**
	 * 根据经纬度获取到附近的学校
	 * @param lat
	 * @param lng
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public JSONObject getBaiduSchool(String lat, String lng) throws HttpException, IOException{
		Map<String , String> baiduLatAndLon = changeLatAndLon(lat, lng,"3","5");
		lat = baiduLatAndLon.get("lat");
		lng = baiduLatAndLon.get("lon");
		ak = readXmlApi.getBaiduMapAk().get(readXmlApi.BaiduAk);
		String url = "http://api.map.baidu.com/place/v2/search?";
		String output = "json";
//		String school = "%E9%AB%98%E6%A0%A1";
		String school = "%E6%95%99%E8%82%B2";
		fullURL = url + "query=" + school+"&location="+lat+","
				+ lng+"&radius=2000&output=json&ak=" + ak;
		
		JSONObject jsonObject = getBaiduResult(fullURL);
		System.out.println(jsonObject);
		return jsonObject;
	}
	
	/**
	 * 通过URL发送百度地图请求
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public JSONObject getBaiduResult(String url) throws HttpException, IOException{
		ak = readXmlApi.getBaiduMapAk().get(readXmlApi.BaiduAk);
		PostMethod post = new PostMethod(url);
		client.executeMethod(post);
		String response = "";
		response = post.getResponseBodyAsString();
		JSONObject jsonObject = JSONObject.fromObject(response);
		return jsonObject;
	}

	public static void main(String[] args) throws HttpException, IOException {
		BaiduMapApi baiduMapApi  = new BaiduMapApi();
		System.out.println(baiduMapApi.getProvince("31.700452", "118.876993"));
	}
}
