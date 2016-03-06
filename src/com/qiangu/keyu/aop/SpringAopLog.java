package com.qiangu.keyu.aop;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qiangu.keyu.api.LoggerApi;
import com.qiangu.keyu.controller.Keys;

/**
 * 可以使用 @order(2) 注解指定切面的优先级,值越小优先级越高
 * 
 * @author lyf
 *
 */
@Aspect
@Component
public class SpringAopLog {

	/**
	 * 定义一个方法,用于声明切入点表达式,一般的,该方法中再不需要填入其他代码 后面的其他通知直接使用方法名来引用当前的切入点表达式.其他类引用时加上
	 * 类名 或 包名
	 */
	@Pointcut("execution(* com.qiangu.keyu.controller.*.*(..))")
	public void declareJointPointExpression() {
	};

	// @Before("declareJointPointExpression()")
	// public void beforeMethod(JoinPoint joinPoint) {
	// String methodName = joinPoint.getSignature().getName();
	// Object[] args = joinPoint.getArgs();
	// System.out.println("the method begins.");
	// }
	//
	// /**
	// * 在方法执行之后执行的代码，无论该方法是否出现异常
	// *
	// * @param joinPoint
	// */
	// @After("declareJointPointExpression()")
	// public void afterMethod(JoinPoint joinPoint) {
	//
	// }
	//
	// /**
	// * 在方法正常结束后执行的代码 返回通知是可以访问到方法的返回值的
	// */
	// @AfterReturning(value = "declareJointPointExpression()", returning =
	// "result")
	// public void afterReturning(JoinPoint joinPoint, Object result) {
	//
	// }

	@AfterThrowing(value = "declareJointPointExpression()", throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint,Exception ex) {
		Object[] args = joinPoint.getArgs();
		HttpServletRequest request = (HttpServletRequest) args[0];
		String userId = "0";
		String ip = request.getRemoteAddr();
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if(isMultipartContent){
			
		}else{
			Map<String, String[]> parameters = request.getParameterMap();
			userId = parameters.get(Keys.userId)[0];
		}
		StackTraceElement[] st = ex.getStackTrace();
		StringBuilder fullError = new StringBuilder("ip 为 "+ip+" ;userId 为 " + userId +" 的用户发生异常\n");
		for(StackTraceElement s : st){
			String exclass = s.getClassName();
			String method = s.getMethodName();
			Integer lineNum = s.getLineNumber();
			fullError.append(exclass + "."+ method + " : 第 " +lineNum + " 行 \n");
		}
		LoggerApi.error(this, fullError.toString());
	}

	/**
	 * 环绕通知需要携带 ProceedingJoinPoint 类型的参数 环绕通知类似于动态代理的全过程: ProceedingJoinPoint
	 * 类型的参数可以决定是否执行目标方法 且环绕通知必须有返回值,返回值即为目标方法的返回值
	 * 
	 * @param pjd
	 */
//	@Around("execution(* com.qiangu.keyu.controller.UserController.*(..))")
//	public Object aroundMethod(ProceedingJoinPoint pjd) {
//
//		Object result = null;
//		String methodName = pjd.getSignature().getName();
//		Object[] args = pjd.getArgs();
//		HttpServletRequest request = (HttpServletRequest) args[0];
//		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
//		
//		try {
//			// 前置通知
//			System.out.println("the method begins");
//
//			// 执行目标方法
//			result = pjd.proceed();
//
//			// 返回通知
//			System.out.println("the method ends with" + result);
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// 后置通知
//		System.out.println("the method ends ");
//		return "123456";
//	}
}
