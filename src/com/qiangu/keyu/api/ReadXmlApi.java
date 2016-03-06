package com.qiangu.keyu.api;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXmlApi {

	public final String BaiduAk = "ak";
	public final String YunPianWangAPIKEY = "APIKEY";
	public final String YunPianWangText = "text";
	public final String QiNiuACCESS_KEY = "ACCESS_KEY";
	public final String QiNiuSECRET_KEY = "SECRET_KEY";
	public final String QiNiuBucket = "bucket";
	public final String QiNiuPhotoSize = "photoSize";
	public final String QiNiuURL = "url";
	public final String AppKey_1 = "AppKey_1";
	public final String AppKey_2 = "AppKey_2";
	public final String clientId = "clientId";
	public final String clientSecret = "clientSecret";
	
	private SAXReader reader;
	private File file;
	private Document document;
	private Element root;
	private List<Element> childElements;
	private Map<String, String> resultMap;
	
	private final String UbuntuPath = "/root/KeYu/qiangu.xml";
	private final String WindowsPath = "E:/qiangu.xml";
	
	public ReadXmlApi() {
		// TODO Auto-generated constructor stub
		reader = new SAXReader();
		file = new File(WindowsPath);
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		root = document.getRootElement();
		childElements = root.elements();
		System.out.println("ReadXmlApi()...");
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String, String> getBaiduMapAk(){
		resultMap = new HashMap<String,String>();
		resultMap.put(BaiduAk,childElements.get(0).elementText(BaiduAk));
		return resultMap;
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String,String> getYunPianWangInfo(){
		resultMap = new HashMap<String,String>();
		List<Element> attrlist = childElements.get(1).elements();
		resultMap.put(YunPianWangAPIKEY, attrlist.get(0).elementText(YunPianWangAPIKEY));
		resultMap.put(YunPianWangText, attrlist.get(0).elementText(YunPianWangText));
		return resultMap;
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String,String> getQiNiuInfo(){
		resultMap = new HashMap<String,String>();
		resultMap.put(QiNiuACCESS_KEY, childElements.get(3).elementText(QiNiuACCESS_KEY));
		resultMap.put(QiNiuSECRET_KEY, childElements.get(3).elementText(QiNiuSECRET_KEY));
		resultMap.put(QiNiuBucket, childElements.get(3).elementText(QiNiuBucket));
		resultMap.put(QiNiuURL,childElements.get(3).elementText(QiNiuURL));
		resultMap.put(QiNiuPhotoSize,childElements.get(3).elementText(QiNiuPhotoSize));
		return resultMap;
	}
	
	public Map<String,String> getHuanXinInfo(){
		resultMap = new HashMap<String,String>();
		List<Element> attrlist = childElements.get(4).elements();
		resultMap.put(AppKey_1, attrlist.get(0).elementText(AppKey_1));
		resultMap.put(AppKey_2, attrlist.get(0).elementText(AppKey_2));
		resultMap.put(clientId, attrlist.get(0).elementText(clientId));
		resultMap.put(clientSecret, attrlist.get(0).elementText(clientSecret));
		return resultMap;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadXmlApi readXmlApi = new ReadXmlApi();
		Map<String, String> m = readXmlApi.getHuanXinInfo();
		System.out.println(m.get(readXmlApi.AppKey_1));
		System.out.println(m.get(readXmlApi.AppKey_2));
		System.out.println(m.get(readXmlApi.clientId));
		
	}

}
