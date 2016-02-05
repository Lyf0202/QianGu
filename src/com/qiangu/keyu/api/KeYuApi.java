package com.qiangu.keyu.api;

import java.util.Date;

import com.qiangu.keyu.po.ChatPo;

public class KeYuApi {

	//到百分百所需的总聊天句数（单个人）
	public static final double totalChatNum = 180;
	
	//到百分百所需的总聊天时间
	public static final double totalChatTime = 3600 * 48;
	
	//聊天时间所占百分比
	public static final double time = 0.1;
	//A的聊天句数所占比例
	public static final double chatA = 0.45;
	//B的聊天句数所占比例
	public static final double chatB = 0.45;
	
	
	public Double getIntimacy(Integer chatNumA ,Integer chatNumB,ChatPo chatPo){
		Date startTime = chatPo.getStartTime();
		long chatTime = System.currentTimeMillis() - startTime.getTime();
		double timePercentage = (chatTime /1000) / totalChatTime;
		timePercentage = timePercentage > 1 ? time : timePercentage * time;
		
		double chatAPercentage = chatNumA / totalChatNum ;
		chatAPercentage = chatAPercentage > 1 ? chatA :chatAPercentage * chatA;
		
		double chatBPercentage = chatNumB / totalChatNum * chatB;
		chatBPercentage = chatBPercentage > 1 ? chatB :chatBPercentage * chatB;
		
		return timePercentage + chatAPercentage + chatBPercentage;
	}
}
