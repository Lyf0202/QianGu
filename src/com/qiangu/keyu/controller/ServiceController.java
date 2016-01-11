package com.qiangu.keyu.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qiangu.keyu.api.UtilsApi;
import com.qiangu.keyu.result.Result;

@Controller
public class ServiceController {

	@Autowired
	private UtilsApi utilsApi;
	
	@Autowired
	private Result result;

	@RequestMapping("/infoService.do")
	public void infoService(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, String[]> map = request.getParameterMap();
		for (String keySet : map.keySet()) {
			System.out.println("\n请求时间  : " + utilsApi.getCurrentTime());
			System.out.println("request key = " + keySet + "   value = " + request.getParameter(keySet));
			parameters.put(keySet, request.getParameter(keySet));
		}

		String resultStr = result.getResult(parameters);
		OutputStreamWriter dos = null;
		try {
			System.out.println("result : " + resultStr);
			dos = new java.io.OutputStreamWriter(response.getOutputStream());
			dos.write(resultStr);
			dos.flush();
			dos.close();
			dos = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (dos != null) {
					dos.close();
					dos = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
