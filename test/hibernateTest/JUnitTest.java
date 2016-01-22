package hibernateTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.util.URLEncoder;
import org.junit.Test;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class JUnitTest {

	@Test
	public void test() {
		
		System.out.println(getDistance(120.352625,30.318659,120.349070,30.315232));
	}
	
	@Test
	public void test1(){
		System.out.println(java.net.URLEncoder.encode("教育"));
		System.out.println("image/jpeg".split("/")[1]);
	}
	
	@Test
	public void test2(){
		List<Integer> li = new ArrayList<>();
		
		li.add(2);
		li.add(4);
		li.add(1, 3);
		for(Integer i : li)
			System.out.println(i);
	}

	@Test
	public void test3(){
		Object o = getObject();
		System.out.println(o instanceof JSONObject);
	}
	
	public Object getObject(){
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
