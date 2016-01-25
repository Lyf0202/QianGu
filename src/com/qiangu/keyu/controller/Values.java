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
	
	//请求方法不存在
	public static final Integer statusOfNoMethod = 4;
	//
	public static final String messageOfNoMethod = "无效method";
	
	//发送验证码失败
	public static final Integer statusOfSendMessageFailed = 5;
	//
	public static final String messageOfSenfMessageFailed = "发送验证码失败";
	
	/**
	 * 基本参数值
	 */
	//女性
	public static final Integer male = 0;
	//男性
	public static final Integer female = 1;
	//用户未认证
	public static final Integer notVerify = 0;
	//用户已认证
	public static final Integer verifyed = 1;
	//不喜欢
	public static final Integer notLike = 0;
	//喜欢
	public static final Integer liked = 1;
	//聊天被删除
	public static final Integer relationDeleted = 0;
	//聊天未删除
	public static final Integer relationNotDelete = 1;
	//验证码长度
	public static final Integer verificationCodeLength = 4;
	
	/**
	 * 请求参数值
	 */
	//更新昵称
	public static final String methodOfUpdateName = "updateName";
	//更新身高
	public static final String methodOfUpdateHeight = "updateHeight";
	//更新头像
	public static final String methodOfUpdateAvatar = "updateAvatar";
	//更新个人描述
	public static final String methodOfUpdateMotto = "updateMotto";
	//更新家乡
	public static final String methodOfUpdateCity = "updateCity";
	//更新生日
	public static final String methodOfUpdateBirthday = "updateBirthday";
	//更新体重
	public static final String methodOfUpdateWeight = "updateWeight";
	//更新学院
	public static final String methodOfUpdateDepartment = "updateDepartment";
	//更新标签
	public static final String methodOfUpdateLabel = "updateLabel";
	//发送验证码
	public static final String methodOfSendMessage = "sendMessage";
	
	
}
