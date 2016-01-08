package test;



import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXml {

	public static Map<String, String> getHuanXinInfo() {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		File file = new File(FinalValues.readXmlPath);
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		List<Element> attrlist = childElements.get(2).elements();
		map.put("AppKey_1", attrlist.get(0).elementText("AppKey_1"));
		map.put("AppKey_2", attrlist.get(0).elementText("AppKey_2"));
		map.put("clientId", attrlist.get(0).elementText("clientId"));
		System.out.println(attrlist.get(0).elementText("AppKey_1"));
		System.out.println(attrlist.get(0).elementText("clientSecret"));
		map.put("clientSecret", attrlist.get(0).elementText("clientSecret"));
		return map;
	}

	public static Map<String, String> getYunPianWangInfo() {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		File file = new File(FinalValues.readXmlPath);
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		List<Element> attrlist = childElements.get(1).elements();
		map.put("APIKEY", attrlist.get(0).elementText("APIKEY"));
		System.out.println(attrlist.get(0).elementText("APIKEY"));
		System.out.println(attrlist.get(0).elementText("text"));
		map.put("text", attrlist.get(0).elementText("text"));
		return map;
	}

	public static Map<String, String> getMysqlConnection() {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		File file = new File(FinalValues.readXmlPath);
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		List<Element> attrlist = childElements.get(0).elements();
		map.put("database", attrlist.get(0).elementText("database"));
		map.put("user", attrlist.get(0).elementText("user"));
		map.put("password", attrlist.get(0).elementText("password"));
		map.put("url", attrlist.get(0).elementText("url"));
		return map;
	}

	public static Map<String, String> getQiNiuInfo() {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		File file = new File(FinalValues.readXmlPath);
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		map.put("ACCESS_KEY", childElements.get(4).elementText("ACCESS_KEY"));
		map.put("SECRET_KEY", childElements.get(4).elementText("SECRET_KEY"));
		map.put("bucket", childElements.get(4).elementText("bucket"));
		map.put("url",childElements.get(4).elementText("url"));
		map.put("photoSize",childElements.get(4).elementText("photoSize"));
		return map;
	}

	public static Map<String, String> getAppVersionNum() {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		File file = new File(FinalValues.readXmlPath);
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		List<Element> attrlist = childElements.get(3).elements();
		map.put("version", attrlist.get(0).elementText("num"));
		return map;
	}

	public static String schoolName = "schoolName";

	public static List<Map<String, String>> getSchool(int cityId) {
		List<Map<String, String>> school = new ArrayList<Map<String, String>>();
		SAXReader reader = new SAXReader();
		File file = new File(FinalValues.readXmlSchool);
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		List<Element> citylist = childElements.get(cityId - 1).elements();
		List<Element> schoollist = citylist.get(0).elements();
		for (int i = 1; i <= schoollist.size(); i++) {
			Map<String, String> schoolMap = new HashMap<String, String>();
			schoolMap.put(schoolName, citylist.get(0)
					.elementText("school_" + i));
			school.add(schoolMap);
		}

		return school;
	}

	public static Map<Integer, List<String>> getQuestion() {
		Map<Integer, List<String>> questions = new HashMap<Integer, List<String>>();
		SAXReader reader = new SAXReader();
		File file = new File(FinalValues.questionPath);
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		List<Element> citylist = childElements.get(0).elements();
		for (int i = 0; i < 30; i++) {
			List<String> questionList = new ArrayList<String>();
			questionList.add(citylist.get(i).elementText("ques"));
			questionList.add(citylist.get(i).elementText("answer_A"));
			questionList.add(citylist.get(i).elementText("color_A"));
			questionList.add(citylist.get(i).elementText("answer_B"));
			questionList.add(citylist.get(i).elementText("color_B"));
			questionList.add(citylist.get(i).elementText("answer_C"));
			questionList.add(citylist.get(i).elementText("color_C"));
			questionList.add(citylist.get(i).elementText("answer_D"));
			questionList.add(citylist.get(i).elementText("color_D"));
			questions.put(i,questionList);
		}
		return questions;
	}

	public static void test() {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		File file = new File("school.xml");
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		System.out.println(childElements.size());
		List<Element> citylist = childElements.get(0).elements();
		List<Element> schoollist = citylist.get(0).elements();
		for (int i = 1; i <= schoollist.size(); i++) {
			System.out.println(citylist.get(0).elementText("school_" + i));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// getHuanXinInfo();
//		Map<Integer, List<String>> questions = getQuestion();
//		System.out.println(questions.size());
//		for (int i = 0; i < 4; i++) {
//			System.out.println(questions.get(i).get(0));
//			System.out.println(questions.get(i).get(1));
//			System.out.println(questions.get(i).get(2));
//			System.out.println(questions.get(i).get(3));
//			System.out.println(questions.get(i).get(4));
//		}
		Map<String, String> map = getQiNiuInfo();
		System.out.println(map.get("url"));
		System.out.println(map.get("photoSize"));

	}

}
