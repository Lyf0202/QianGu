package com.qiangu.keyu.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsApi {

	
	public boolean isValid(String str,String regEx){
		// 编译正则表达式
	    Pattern pattern = Pattern.compile(regEx);
	    // 忽略大小写的写法
	    Matcher matcher = pattern.matcher(str);
	    // 字符串是否与正则表达式相匹配
	    boolean rs = matcher.matches();
	    
	    return rs;
	}
	
	public String getCurrentTime(){
		Date dt=new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime="";
		nowTime= df.format(dt);
		return nowTime;
	}
	
	public String getRandomNum(int num){
		String str = "";
		for(int i = 0;i<num;i++){
			Random ran = new Random();
			str += ran.nextInt(9);
		}
		return str;
	}
}
