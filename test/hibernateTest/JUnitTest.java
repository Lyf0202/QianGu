package hibernateTest;

import static org.junit.Assert.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.catalina.util.URLEncoder;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import com.qiangu.keyu.api.MongodbApi;
import com.qiangu.keyu.api.Sqlite;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class JUnitTest {

	@Test
	public void test() {

		System.out.println(getDistance(120.352625, 30.318659, 120.349070, 30.315232));
	}

	@Test
	public void test1() {
		System.out.println(java.net.URLEncoder.encode("教育"));
		System.out.println("image/jpeg".split("/")[1]);
	}

	@Test
	public void test2() {
		List<Integer> li = new ArrayList<>();

		li.add(2);
		li.add(4);
		li.add(1, 3);
		for (Integer i : li)
			System.out.println(i);
	}

	@Test
	public void testMongodb() {
		MongodbApi m = new MongodbApi();
		for (int i = 1; i < 10; i++) {
			BasicDBObject b = new BasicDBObject();
			Double[] d = { Double.valueOf("30."+i+12121), 121.123465 };
			b.put("loc", d);
			b.put("userId", i+15);
			WriteResult w = m.mongodbInsert(b);
			System.out.println("success");
		}
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
			collection.insert(document);
			// 创建要查询的document
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("id", 1001);
			// 使用collection的find方法查找document
			DBCursor cursor = collection.find();
			// 循环输出结果
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
			System.out.println("Done");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test4() {
		System.out.println(getDistance(-73.97,40.77,-73.80,40.79));
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
