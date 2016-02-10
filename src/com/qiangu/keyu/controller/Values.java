package com.qiangu.keyu.controller;

public class Values {

	
	/**
	 * 返回状态值
	 */
	//
	public static final Integer statusOfSuccess = 1;
	
	//无效ContentType
	public static final Integer statusOfInvalidRequest = 100;
	//
	public static final String messageOfInvalidRequest = "无效请求";
	
	//请求参数有空值
	public static final Integer statusOfNullParameter = 101;
	//
	public static final String messageOfNullParameters = "请求参数有空值";
	
	//请求验证失败
	public static final Integer statusOfInvalidToken = 102;
	//
	public static final String messageOfInvalidToken = "请求验证失败";
	
	//服务器内部错误
	public static final Integer statusOfServiceError = 103;
	//
	public static final String messageOfServiceError = "服务器内部错误";
	
	//请求方法不存在
	public static final Integer statusOfNoMethod = 104;
	//
	public static final String messageOfNoMethod = "无效method";
	
	//发送验证码失败
	public static final Integer statusOfSendMessageFailed = 105;
	//
	public static final String messageOfSenfMessageFailed = "发送验证码失败";
	
	//验证码错误
	public static final Integer statusOfWrongVerificationCode = 106;
	//
	public static final String messageOfWrongVerificationCode = "验证码错误";
	
	//验证码失效
	public static final Integer statusOfInvalidVerificationCode = 107;
	//
	public static final String messageOfInvalidVerificationCode = "验证码失效";
	
	//用户不存在
	public static final Integer statusOfUserNotExist = 108;
	//
	public static final String messageOfUserNotExist = "该用户不存在";
	
	//定位学校失败
	public static final Integer statusOfNoSchools = 109;
	//
	public static final String messageOfNoSchools = "附近没有匹配学校";
	
	
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
	//验证码有效期
	public static final Long verificationCodeTime = (long) (5 * 60 * 1000) ;
	//同时能够聊天的人数
	public static final Integer chatNum = 3;
	//当前使用标签
	public static final Integer isNow = 1;
	//当前未使用标签
	public static final Integer notIsNow = 0;
	//
	public static final String yes = "yes";
	//
	public static final String no = "no";
	
	
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
	public static final String methodOfUpdateCity = "updateHometown";
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
	//登录或注册
	public static final String methodOfLoginOrRegister = "loginOrRegister";
	//完成注册
	public static final String methodOfCompleteRegister = "completeRegister";
	//
	public static final String methodOfGetSchool = "getSchool";
	
	
}
