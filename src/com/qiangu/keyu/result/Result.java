package com.qiangu.keyu.result;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qiangu.keyu.infoToJSON.SchoolServiceInfoToJSON;

public class Result {

	@Autowired
	private SchoolServiceInfoToJSON schoolServiceInfoToJSON;
	
	public String getResult(Map<String,String> parameters){
		String result = "";
		
		return result;
	}
}
