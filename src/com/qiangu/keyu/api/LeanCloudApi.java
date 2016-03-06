package com.qiangu.keyu.api;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

public class LeanCloudApi {

	private HttpClient client;
	
	public LeanCloudApi() {
		// TODO Auto-generated constructor stub
		client = new HttpClient();
	}
	
	public void createConversation() throws HttpException, IOException{
		String url = "https://api.leancloud.cn/1.1/classes/_Conversation";
		PostMethod post = new PostMethod(url);
		post.addRequestHeader("X-LC-Id", "4BzfDN9LNhn6YCU5epcghqGQ-gzGzoHsz");
		post.addRequestHeader("X-LC-Key", "2xN74HujOb2hhCFq8Mo6gNl0");
		post.addRequestHeader("Content-Type", "application/json");
	
		client.executeMethod(post);
		String response = "";
		response = post.getResponseBodyAsString();
		System.out.println(response);
	}
	
}
