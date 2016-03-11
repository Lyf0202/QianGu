package hibernateTest;

import static org.junit.Assert.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.util.URLEncoder;
import org.hibernate.annotations.Sort;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import com.qiangu.keyu.api.MongodbApi;
import com.qiangu.keyu.api.Sqlite;
import com.qiangu.keyu.api.UtilsApi;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JUnitTest {

	@Test
	public void test() {
		Long t = System.currentTimeMillis();
		System.out.println(t);
//		System.out.println(getDistance(120.352625, 30.318659, 120.349070, 30.315232));
	}

	@Test
	public void test1() {
//		System.out.println(java.net.URLEncoder.encode("教育"));
//		System.out.println("image/jpeg".split("/")[1]);
		Map<String, Integer> m1 = new HashMap<>();
		m1.put("userId", 4);
		Map<String, Integer> m2 = new HashMap<>();
		m2.put("userId", 6);
		Map<String, Integer> m3 = new HashMap<>();
		m3.put("userId", 2);
		Map<String, Integer> m4 = new HashMap<>();
		m4.put("userId", 3);
		List<Map> list1 = new ArrayList<>();
		list1.add(m1);
		list1.add(m2);
		list1.add(m3);
		list1.add(m4);
		Map<String, Integer> m5 = new HashMap<>();
		m5.put("userId", 1);
		Map<String, Integer> m6 = new HashMap<>();
		m6.put("userId", 7);
		Map<String, Integer> m7 = new HashMap<>();
		m7.put("userId", 5);
		Map<String, Integer> m8 = new HashMap<>();
		m8.put("userId", 8);
		List<Map> list2 = new ArrayList<>();
		list2.add(m5);
		list2.add(m6);
		list2.add(m7);
		list2.add(m8);
		System.out.println("-------");
		List<Map> list = null;
		list1.addAll(list);
		
	}
	
	public void sortListT(List<Map> list1){
		List<Map> list = new ArrayList<>();
		list.add(list1.get(0));
//		for(int i = 1 ; i < list1.size() ;i++){
//			if (condition) {
//				
//			}
//		}
		
	}

	@Test
	public void test2() {
		System.out.println(Double.valueOf("0.001234"));
	}

	@Test
	public void testMongodb() {
		MongodbApi m = new MongodbApi();
		BasicDBObject b = new BasicDBObject();
		Double[] d = { -73.80, 40.70};
		JSONObject json = new JSONObject();
		json.put("type","Point");
		json.put("coordinates", d);
		
		b.put("loc", json);
		b.put("name", "HZNU");
		WriteResult w = m.mongodbInsert(b);
		System.out.println("success");
	}

	@Test
	public void mongodbUpdate(){
		MongodbApi m = new MongodbApi();
		
		BasicDBObject updateCondition = new BasicDBObject();
		updateCondition.put("name", "杭师大");
		
		BasicDBObject updateSetValue = new BasicDBObject();
		Double[] d = { -88.88,66.66};
		JSONObject json = new JSONObject();
		json.put("type","Point");
		json.put("coordinates", d);
		updateSetValue.put("loc", json);
		
		m.mongodbUpdateOrInsert(updateCondition, updateSetValue);
		System.out.println("success !");
	}
	
	@Test
	public void mongodbQuery(){
		String near = "$near";
		String geometry = "$geometry";
		String maxDistance = "$maxDistance";
		long distance =  15000;
		MongodbApi m = new MongodbApi();
		BasicDBObject geovalueB = new BasicDBObject();
		geovalueB.put("type", "Point");
		geovalueB.put("coordinates", new Double[] {-73.80, 40.79});
		BasicDBObject nearB = new BasicDBObject();
		nearB.put(geometry, geovalueB);
		nearB.put(maxDistance, distance);
		BasicDBObject b = new BasicDBObject().append("loc", 
				new BasicDBObject().append(near, nearB));
		m.mongodbFind(b);
	}
	
	@Test
	public void test3() {
		try {
			// 实例化Mongo对象，连接27017端口
			Mongo mongo = new Mongo("localhost", 27017);
			// 连接名为yourdb的数据库，假如数据库不存在的话，mongodb会自动建立
			DB db = mongo.getDB("firstMongodb");
			// Get collection from MongoDB, database named "yourDB"
			// 从Mongodb中获得名为yourColleection的数据集合，如果该数据集合不存在，Mongodb会为其新建立
			DBCollection collection = db.getCollection("testLBS");
			// 使用BasicDBObject对象创建一个mongodb的document,并给予赋值。
			BasicDBObject document = new BasicDBObject();
			document.put("id", 1003);
			// document.
			Float[] f = { (float) 121.1251, (float) 1255.15 };
			document.put("msg", f);
			// 将新建立的document保存到collection中去
//			collection.insert(document);
			// 创建要查询的document
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("name","HZNU");
			// 使用collection的find方法查找document
			DBCursor cursor = collection.find();
			
			// 循环输出结果
			while (cursor.hasNext()) {
				JSONObject json = JSONObject.fromObject(cursor.next());
//				System.out.println(json.get("name"));
				JSONObject j = (JSONObject) json.get("loc");
				JSONArray d = j.getJSONArray("coordinates");
				System.out.println(d.get(0)+"  "+d.get(1));
			}
//			System.out.println("---------");
//			List<DBObject> l = cursor.toArray();
//			for(DBObject d :l){
//				System.out.println(d);
//			}
			System.out.println("Done");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test4() {
//		System.out.println(getDistance(-73.92, 40.79, -73.84, 40.79));
		List<Integer> l = new ArrayList<>();
		List<Integer> li = new ArrayList<>();
		l.add(1);
		l.add(3);
		li.add(5);
		li.add(2);
		l.addAll(li);
		for(Integer i : l){
			System.out.println(i);
		}
	}

	@Test
	public void test5(){
		
		// 1.  -73.84  40.79
		// 2.  -73.88  40.78    3550
		// 3.  -73.92  40.79     6742
		// 4.  -73.97  40.77     11182
		// 5.  -73.824  40.729     6923
		// 6.  -73.86   40.80     2019
		// 7.  -73.82   40.80     2019
		// 8.  -73.844785  40.792155   469
		// 9.  -73.845480  40.799999    1205
		// 10.  -73.839511  40.781452   952
		// 11.  -73.850121  40.800012   1403
		//
		System.out.println(getDistance(120.006685, 30.290539, 120.106685, 30.290539));
	}
	
	public Object getObject() {
		JSONObject j = new JSONObject();
		j.accumulate("4132", "4545");
		return "";
	}

	public static double getDistance(double long1, double lat1, double long2, double lat2) {
		double a, b, R;
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
		return d;
	}
}
