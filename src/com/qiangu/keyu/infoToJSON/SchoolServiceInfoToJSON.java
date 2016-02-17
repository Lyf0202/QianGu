package com.qiangu.keyu.infoToJSON;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;

import com.qiangu.keyu.controller.Keys;
import com.qiangu.keyu.controller.Values;
import com.qiangu.keyu.po.SchoolCoding;
import com.qiangu.keyu.service.SchoolService;

import net.sf.json.JSONObject;

public class SchoolServiceInfoToJSON {

	@Autowired
	private SchoolService schoolService;
	
	public JSONObject getLocationSchoolInfoToJSON(Map<String, String[]> parameters) throws HttpException, IOException{
		JSONObject returnJSON = new JSONObject();
		JSONObject statusJSON = new JSONObject();
		JSONObject resultJSON = new JSONObject();
		List<SchoolCoding> schools = schoolService.getLocationSchool(parameters);
		if(schools == null || schools.size() == 0){
			statusJSON.accumulate(Keys.status,Values.statusOfNoSchools);
			statusJSON.accumulate(Keys.message, Values.messageOfNoSchools);
		}else{
			statusJSON.accumulate(Keys.status, Values.statusOfSuccess);
			JSONObject schoolJSON = new JSONObject();
			for(SchoolCoding s : schools){
				schoolJSON.accumulate(String.valueOf(s.getId()), s.getSchool_name());
			}
			resultJSON.put(Keys.school, schoolJSON);
			returnJSON.put(Keys.result, resultJSON);
		}
		returnJSON.put(Keys.status, statusJSON);
		return returnJSON;
	}
}
