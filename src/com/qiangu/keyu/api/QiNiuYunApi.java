package com.qiangu.keyu.api;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class QiNiuYunApi {

	@Autowired
	private ReadXmlApi readXmlApi ;
	
	public String ACCESS_KEY = "";
	public String SECRET_KEY = "";
	public String bucket = "";
	public String url = "";
	public String photoSize = ""; 
	public QiNiuYunApi() {
		
	}
	
	public void setReadXmlApi(ReadXmlApi readXmlApi) {
		this.readXmlApi = readXmlApi;
	}
	
	public String getDownloadUrl(String downloadPicName){
		url = readXmlApi.getQiNiuInfo().get(readXmlApi.QiNiuURL);
		photoSize = readXmlApi.getQiNiuInfo().get(readXmlApi.QiNiuPhotoSize);
		String finalUrl = url + downloadPicName + "?"+photoSize;
		
		
		return finalUrl;
	}
	
	/**
	 * 上传成功返回true，失败返回false
	 * @param key
	 * @param data
	 * @return
	 */
	public boolean pictureUpload(String key, byte[] data) {
		ACCESS_KEY = readXmlApi.getQiNiuInfo().get(readXmlApi.QiNiuACCESS_KEY);
		SECRET_KEY = readXmlApi.getQiNiuInfo().get(readXmlApi.QiNiuSECRET_KEY);
		bucket = readXmlApi.getQiNiuInfo().get(readXmlApi.QiNiuBucket);		
		boolean flag = true;
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		String token = auth.uploadToken(bucket);
		UploadManager upload = new UploadManager();
		try {
			Response res = upload.put(data, key, token);
			if (res.isOK()) {
				// success
				System.out.println(key + " upload successly");
				flag = true;
			} else {
				//
				flag = false;
			}
		} catch (QiniuException e) {
			// TODO: handle exception
			Response r = e.response;
			// 请求失败时简单状态信息

			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
			flag = false;
		}

		return flag;
	}
	
	public boolean pictureUpload(String key, File data) {
		ACCESS_KEY = readXmlApi.getQiNiuInfo().get(readXmlApi.QiNiuACCESS_KEY);
		SECRET_KEY = readXmlApi.getQiNiuInfo().get(readXmlApi.QiNiuSECRET_KEY);
		bucket = readXmlApi.getQiNiuInfo().get(readXmlApi.QiNiuBucket);		
		boolean flag = true;
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		String token = auth.uploadToken(bucket);
		UploadManager upload = new UploadManager();
		try {
			Response res = upload.put(data, key, token);
			if (res.isOK()) {
				// success
				System.out.println(key + " upload successly");
				flag = true;
			} else {
				//
				flag = false;
			}
		} catch (QiniuException e) {
			// TODO: handle exception
			Response r = e.response;
			// 请求失败时简单状态信息

			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
			flag = false;
		}

		return flag;
	}
	/**
	 * 上传成功返回true，失败返回false
	 * @param key
	 * @param data
	 * @return
	 */
	public boolean pictureUpdate(String key, byte[] data) {
		ACCESS_KEY = readXmlApi.getQiNiuInfo().get(readXmlApi.QiNiuACCESS_KEY);
		SECRET_KEY = readXmlApi.getQiNiuInfo().get(readXmlApi.QiNiuSECRET_KEY);
		bucket = readXmlApi.getQiNiuInfo().get(readXmlApi.QiNiuBucket);	
		boolean flag = false;
		Auth dummyAuth = Auth.create(ACCESS_KEY, SECRET_KEY);
		BucketManager bucketManager = new BucketManager(dummyAuth);
		try {
			bucketManager.delete(bucket, key);
			if (pictureUpload(key, data)) {
				flag = true;
				System.out.println(key+" update successly");
			} else {
				flag = false;
			}
		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			Response r = e.response;
			// 请求失败时简单状态信息

			try {
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	public boolean pictureUpdate(String key, File data) {
		ACCESS_KEY = readXmlApi.getQiNiuInfo().get(readXmlApi.QiNiuACCESS_KEY);
		SECRET_KEY = readXmlApi.getQiNiuInfo().get(readXmlApi.QiNiuSECRET_KEY);
		bucket = readXmlApi.getQiNiuInfo().get(readXmlApi.QiNiuBucket);	
		boolean flag = false;
		Auth dummyAuth = Auth.create(ACCESS_KEY, SECRET_KEY);
		BucketManager bucketManager = new BucketManager(dummyAuth);
		try {
			bucketManager.delete(bucket, key);
			if (pictureUpload(key, data)) {
				flag = true;
				System.out.println(key+" update successly");
			} else {
				flag = false;
			}
		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			Response r = e.response;
			// 请求失败时简单状态信息

			try {
				System.out.println(key + " : "+r.bodyString());
			} catch (QiniuException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
