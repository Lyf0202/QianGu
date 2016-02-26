package com.qiangu.keyu.api;


import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;
import com.mongodb.WriteResult;

public class MongodbApi {

	private final static String DBNAME = "KeYu";
	private final static String COLLECTIONName = "testCollection";
	private final static String HOST = "127.0.0.1";// 端口  
    private final static int PORT = 27017;// 端口  
    private final static int POOLSIZE = 100;// 连接数量  
    private final static int BLOCKSIZE = 100; // 等待队列长度  
    
    public static String near = "$near";
    public static String geometry = "$geometry";
    public static String maxDistance = "$maxDistance";
    public static String set = "$set";
    public static String in = "$in";
    public static String type = "type";
    public static String Point = "Point";
    public static String coordinates = "coordinates";
    public static String loc = "loc";
    public static String userId = "userId";
    
    private static Mongo mongo = null;  
	
    
   
    public MongodbApi() {
    	initDBPrompties();
	}
    
    /**
     * 根据数据库名获取对应数据库
     * @param dbName
     * @return
     */
	public DB getDB(String dbName){
		return mongo.getDB(dbName);
	}
	
	/**
	 * 初始化
	 */
	public void initDBPrompties(){
		// 其他参数根据实际情况进行添加  
        try {  
            mongo = new Mongo(HOST, PORT);  
            MongoOptions opt = mongo.getMongoOptions();  
            //设置连接数量
            opt.connectionsPerHost = POOLSIZE;
            //设置等待队列长度
            opt.threadsAllowedToBlockForConnectionMultiplier = BLOCKSIZE;  
        } catch (UnknownHostException e) { 
        	
        } catch (MongoException e) {  
  
        }  
	}
	
	/**
	 * 将document写入数据库
	 * @param document
	 * @return
	 */
	public WriteResult mongodbInsert(BasicDBObject document){
		DB db = getDB(DBNAME);
		DBCollection collection = db.getCollection(COLLECTIONName);
		
		return collection.insert(document);
	}
	
	/**
	 * 根据searchQuery查找对应数据
	 * @param searchQuery
	 * @return
	 */
	public DBCursor mongodbFind(BasicDBObject searchQuery){
		DB db = getDB(DBNAME);
		DBCollection collection = db.getCollection(COLLECTIONName);
		return collection.find(searchQuery);
	}
	
	/**
	 * 更新数据库
	 * @param updateCondition
	 * @param updateValue
	 * @return
	 */
	public WriteResult mongodbUpdateOrInsert(BasicDBObject updateCondition,BasicDBObject updateValue){
		DB db = getDB(DBNAME);
		DBCollection collection = db.getCollection(COLLECTIONName);
		BasicDBObject updateSetValue = new BasicDBObject("$set",updateValue);
		return collection.update(updateCondition, updateSetValue, true, true);
	}
	
}
