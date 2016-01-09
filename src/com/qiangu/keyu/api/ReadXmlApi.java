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
	
	private SAXReader reader;
	private File file;
	private Document document;
	private Element root;
	private List<Element> childElements;
	private Map<String, String> resultMap;
	
	public ReadXmlApi() {
		// TODO Auto-generated constructor stub
		reader = new SAXReader();
		file = new File("E:/qiangu.xml");
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadXmlApi readXmlApi = new ReadXmlApi();
		Map<String, String> m = readXmlApi.getQiNiuInfo();
		System.out.println(m.get("bucket"));
	}

}
