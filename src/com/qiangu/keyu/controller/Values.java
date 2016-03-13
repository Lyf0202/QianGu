package com.qiangu.keyu.controller;

import com.sun.beans.editors.IntegerEditor;

public class Values {

	public static final Integer officeId = -1;
	public static final String IDVerifyPicture = "_IDVerifyPicture_";
	public static final String informPicture = "_informPicture_";
	
	public static final String md5Value = "14e1b600b1fd579f47433b88e8d85291";

	//小图大小
	public static final Integer littleSizeWidth = 200;
	public static final Integer littleSizeHeight = 200;
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
	public static final String messageOfInvalidToken = "无效token";
	
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
	
	//
	public static final Integer postMsgExtDeleteUser = 1;
	//
	public static final String messageOfDeleteUser = " 已经无情把你踢了";
	
	//
	
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
	//等待认证中
	public static final Integer waitVerifye = 2;
	//不喜欢 / 单方喜欢(聊天表中用)
	public static final Integer notLike = 0;
	//喜欢 / 已经双方喜欢(聊天表中用)
	public static final Integer liked = 1;
	//
	public static final Integer login = 1;
	//
	public static final Integer logout = 0;
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
	//无标签内容
	public static final String noLabels = "0";
	//官方标签
	public static final Integer officialLabels = 1;
	//自定义标签
	public static final Integer userLabels = 2;
	//不提供定位权限
	public static final String noLoc = "noLoc";
	//本地缓存在喜欢用户
	public static final String hasLikeUser = "1";
	//本地未缓存喜欢用户 
	public static final String noHasLikeUser = "0";
	//已经开始聊天
	public static final Integer startChat = 1;
	//未开始聊天
	public static final Integer notStartChat = 0;
	//没给定位时存储的经度
	public static final double noLocLng = -12.12;
	//没给定位时存储的纬度
	public static final double noLocLat = -12.12;
	//一次获取的最多用户量
	public static final Integer onceUserNum = 5;
	//一次获取的最多喜欢用户量
	public static final Integer onceLikeUserNum = 2;
	//一次获取的最多没有开启定位的用户量
	public static final Integer onceSchoolUserNum = 5;
	//
	public static final Integer onceUserNumWithoutVerifyed = 3;
	//最大与当前时间的时间差
	public static final long OnlineTime = 3 * 3600 * 1000;
	//最大匹配用户距离
	public static final long Distance = 3000;
	//每次减少的在线时间
	public static final long halfHour = 1800 * 1000;
	//每次增加的距离
	public static final Integer onceDistance = 500;
	//全部用户
	public static final Integer isAll = 1;
	//还未到全部用户
	public static final Integer notIsAll = 0;
	//起始亲密度
	public static final Double startIntimacy = 0.0;
	//聊天信息里UserA
	public static final Integer isUserA = 1;
	//
	public static final Integer notIsUserA = 0;
	//每天增加的百分比
	public static final Double timePercentage = 5.0;
	//每句话增加的百分比
	public static final Double chatNumPercentage = 0.25;
	//聊天到百分之百清晰所需的天数
	public static final Integer totalTime = 2;
	//聊天到百分之百清晰所需的聊天句数
	public static final Integer totalChatNum = 180;
	//自动删除聊天最大的未开始聊天时间限制
	public static final long maxTimeForNotStartChat = 24 * 3600 * 1000;
	//能够开始聊天
	public static final Integer canChat = 1;
	//不能开始聊天
	public static final Integer canNotChat = 0;
	//最大模糊半径
	public static final double maxDimRadius = 50;
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
	//
	public static final String methodOfExit = "userExit";
	//更新用户查看了点击喜欢用户的序号
	public static final String methodOfUpdateLikeUserOrder = "updateLikeUserOrder";
	//发送验证码
	public static final String methodOfSendMessage = "sendMessage";
	//登录或注册
	public static final String methodOfLoginOrRegister = "loginOrRegister";
	//完成注册
	public static final String methodOfCompleteRegister = "completeRegister";
	//定位学校
	public static final String methodOfGetSchool = "getSchool";
	//打开APP
	public static final String methodOfOpenApp = "openApp";
	//获取主页用户
	public static final String methodOfGetUser = "getUser";
	//点击喜欢
	public static final String methodOfClickLike = "clickLike";
	//删除聊天用户
	public static final String methodOfDeleteChatUser = "deleteChatUser";
	//更新亲密度
	public static final String methodOfUpdateIntimacy = "updateIntimacy";
	//开始聊天接口
	public static final String methodOfStartChat = "startChat";
	//
	public static final String methodOfUploadIDVerifyPicture = "uploadIdVerifyPicture";
	//
	public static final String methodOfUserFeedback = "userFeedback";
	//
	public static final String methodOfUserInform = "userInform";
	//
	public static final String methodOfGetSystemTime = "getSystemTime";
	
}
