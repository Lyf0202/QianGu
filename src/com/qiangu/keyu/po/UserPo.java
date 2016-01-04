package com.qiangu.keyu.po;

import java.util.Date;
/**
 * 用户基本信息表
 * @author lyf
 *
 */
public class UserPo {
	
	public UserPo() {
		// TODO Auto-generated constructor stub
	}
	
	//id
	private Integer id;
	//用户id
	private Integer userId;
	//用户登录手机号
	private String telephone;
	//用户登录密码
	private String password;
	//用户性别
	private Integer sex;
	//头像Id
	private Integer avatarId;
	//颜值
	private Integer beauty;
	//姓名
	private String name;
	//学历
	private String education;
	//入学年份
	private Integer enterTime;
	//学校id
	private Integer schoolId;
	//专业id
	private Integer majorId;
	//学生证/毕业证id
	private Integer IDVerifyPicId;
	//身份颜值状态
	private Integer verifyType;
	//爱情宣言id
	private Integer loveManifestoId;
	//身高
	private Double height;
	//体重
	private Double weight;
	//出生年月
	private Date birthday;
	//聊天id
	private String talkId;
	//聊天密码
	private String talkPassword;
	//注册时间
	private Date registerTime;
	//区县（家乡）id
	private Integer countyId;
	//上次登录时间
	private Date lastOnlineTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAvatarId() {
		return avatarId;
	}
	public void setAvatarId(Integer avatarId) {
		this.avatarId = avatarId;
	}
	public Integer getBeauty() {
		return beauty;
	}
	public void setBeauty(Integer beauty) {
		this.beauty = beauty;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public Integer getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(Integer enterTime) {
		this.enterTime = enterTime;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public Integer getMajorId() {
		return majorId;
	}
	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}
	public Integer getIDVerifyPicId() {
		return IDVerifyPicId;
	}
	public void setIDVerifyPicId(Integer iDVerifyPicId) {
		IDVerifyPicId = iDVerifyPicId;
	}
	public Integer getVerifyType() {
		return verifyType;
	}
	public void setVerifyType(Integer verifyType) {
		this.verifyType = verifyType;
	}
	public Integer getLoveManifestoId() {
		return loveManifestoId;
	}
	public void setLoveManifestoId(Integer loveManifestoId) {
		this.loveManifestoId = loveManifestoId;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getTalkId() {
		return talkId;
	}
	public void setTalkId(String talkId) {
		this.talkId = talkId;
	}
	public String getTalkPassword() {
		return talkPassword;
	}
	public void setTalkPassword(String talkPassword) {
		this.talkPassword = talkPassword;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public Integer getCountyId() {
		return countyId;
	}
	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}
	public Date getLastOnlineTime() {
		return lastOnlineTime;
	}
	public void setLastOnlineTime(Date lastOnlineTime) {
		this.lastOnlineTime = lastOnlineTime;
	}
	
	
}
