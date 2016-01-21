package com.qiangu.keyu.controller;

public class Values {

	
	/**
	 * 返回状态值
	 */
	//
	public static final Integer statusOfSuccess = 100;
	
	//无效ContentType
	public static final Integer statusOfInvalidRequest = 0;
	//
	public static final String messageOfInvalidRequest = "无效请求";
	
	//请求参数有空值
	public static final Integer statusOfNullParameter = 1;
	//
	public static final String messageOfNullParameters = "请求参数有空值";
	
	//请求验证失败
	public static final Integer statusOfInvalidToken = 2;
	//
	public static final String messageOfInvalidToken = "请求验证失败";
	
	//服务器内部错误
	public static final Integer statusOfServiceError = 3;
	//
	public static final String messageOfServiceError = "服务器内部错误";
	
	//
	public static final Integer statusOfNoMethod = 4;
	//
	public static final String messageOfNoMethod = "无效method";
	
	/**
	 * 请求参数值
	 */
	//
	public static final String methodOfUpdateName = "updateName";
	public static final String methodOfUpdateHeight = "updateHeight";
	
}
