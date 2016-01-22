package com.qiangu.keyu.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qiangu.keyu.api.QiNiuYunApi;
import com.qiangu.keyu.api.UtilsApi;
import com.qiangu.keyu.api.YunPianWangApi;
import com.qiangu.keyu.result.Result;

import net.sf.json.JSONObject;




@Controller
public class ServiceController {

	@Autowired
	private YunPianWangApi yunpianwang;
	
	@Autowired
	private UtilsApi utilsApi;
	
	@Autowired
	private Result result;

	@Autowired
	private QiNiuYunApi qiniu; 
	
	@RequestMapping("/keYuService.do")
	public void keYuService(HttpServletRequest request, HttpServletResponse response){
		System.out.println("\n keYuService.do " + utilsApi.getCurrentTime()
		+ " *****************");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String contentType = request.getContentType();
		System.out.println("ContentType = "+ contentType);
		Map<String,String> parameters = new HashMap<>();
		
		System.out.println(ServletFileUpload.isMultipartContent(request));
		
		if(contentType.equals("application/x-www-form-urlencoded")){
			Map<String, String[]> map = request.getParameterMap();
			for (String keySet : map.keySet()) {
				parameters.put(keySet, request.getParameter(keySet));
			}
		}else{
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			List items = null;
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e1) {
				e1.printStackTrace();
			}
			for (Object object : items) {
				FileItem fileItem = (FileItem) object;
				if (fileItem.isFormField()) {
					String key = fileItem.getFieldName();
					String value = null;
					try {
						value = fileItem.getString("utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					parameters.put(key, value);// 如果你页面编码是utf-8的
					
				} else {
					//找出要上传的文件的名字
					String fileName = fileItem.getName();
					fileName = fileName+"." + fileItem.getContentType().split("/")[1];
					parameters.put(fileItem.getFieldName(), fileName);
//					qiniu.pictureUpload(fileName, fileItem.get());
					//上传
					try {
						fileItem.write(new File("F:/"
								+ fileName));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		for (Map.Entry<String, String> entry : parameters.entrySet()) {  		  
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());    
		}
//		yunpianwang.sendSms("123987", "18857117310");
		JSONObject object = new JSONObject();
		object.accumulate("123", "你好吗nihao");
		String resultStr =object.toString() ;
		System.out.println("resultStr ========= " + resultStr);
		java.io.ObjectOutputStream ot = null;
		OutputStreamWriter dos = null;
		try {
			dos = new java.io.OutputStreamWriter(response.getOutputStream());
			dos.write(resultStr);
			dos.flush();
			dos.close();
			dos = null;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (dos != null) {
					dos.close();
					dos = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
}
