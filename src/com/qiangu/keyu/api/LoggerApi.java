package com.qiangu.keyu.api;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LoggerApi {

	public static void info(Object obj,String str){
		 Logger logger = LogManager.getLogger(obj.getClass());
		 logger.info(str);
	}
	
	public static void debug(Object obj,String str){
		Logger logger = LogManager.getLogger(obj.getClass());
		logger.debug(str);
	}
	
	public static void error(Object obj,String str){
		Logger logger = LogManager.getLogger(obj.getClass());
		logger.error(str);;
	}
	
	public static void warn(Object obj,String str){
		Logger logger = LogManager.getLogger(obj.getClass());
		logger.warn(str);
	}
	
}
