package com.qiangu.keyu.api;


import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;
import com.mongodb.WriteResult;

public class MongodbApi {

	private final static String DBNAME = "KeYu";
	private final static String COLLECTIONName = "userLocation";
	private final static String HOST = "localhost";// 端口  
    private final static int PORT = 27017;// 端口  
    private final static int POOLSIZE = 100;// 连接数量  
    private final static int BLOCKSIZE = 100; // 等待队列长度  
    private static Mongo mongo = null;  
	
    static {
    	initDBPrompties();
    }
   
	public DB getDB(String dbName){
		return mongo.getDB(dbName);
	}
	
	public static void initDBPrompties(){
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
	
	public WriteResult mongodbInsert(BasicDBObject document){
		DB db = getDB(DBNAME);
		DBCollection collection = db.getCollection(COLLECTIONName);
		return collection.insert(document);
	}
	
}
