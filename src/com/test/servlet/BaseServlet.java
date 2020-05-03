/**
 * 
 */
package com.test.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO BaseServlet
 * @author Prince
 * @date 2020年4月18日  
 */
@SuppressWarnings("serial")
public abstract class BaseServlet extends HttpServlet {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		/*
		 * 1.获取参数，来识别用户想要请求的方法
		 */
		String methodName = request.getParameter("method");
		
		if(methodName == null || methodName.trim().isEmpty()) { //没传参数
			throw new RuntimeException("没有传递method参数，无法确定想调用的方法");
		}
		
		/*
		 * 2.得到方法名称
		 */
//		if(methodName.equals("addUser")) {
//			addUser(request, responce);
//		}else if(methodName.equals("editUser")) {
//			editUser(request, responce);
//		}else if(methodName.equals("deleteUser")) {
//			deleteUser(request, responce);
//		}
		
		/*
		 * 3.考虑是否可以通过反射来调用方法
		 * 得到方法名
		 * 通过方法名得到method类的对象
		 * *需要得到Class,然后调用它的方法进行查询，得到method
		 * *我们需要查询的是当前类的方法，所以需要得到当前类的Class
		 */
		Class clazz = this.getClass(); //得到当前类的Class对象
		Method method= null;
		try {
			method = clazz.getMethod(methodName, 
					HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("调用的 " + methodName + " 方法不存在");
		}
		
//		//调用method表示的方法
//		try {
//			method.invoke(this, request, responce);
//		}catch(Exception e) {
//			throw new RuntimeException("调用的方法 " + method + " 内部发现了异常");
//		}
		
		//调用method表示的方法
		try {
			/*
			 * 获取请求处理方法执行后的字符串
			 * 表示转发或者重定向的路径
			 */
			String result = (String)method.invoke(this, request, response);
			/*
			 * 如果返回的字符串为null或者为空值
			 * 什么也不做
			 */
			if(result == null || result.trim().isEmpty()) {
				return;
			}
			/*
			 * 查看返回的字符串是否包含冒号
			 * 根据冒号来拆分出转发或者重定向的要求
			 * 如果没有，就使用默认值
			 * f:表示转发
			 * r:表示重定向
			 * 默认值:f,转发
			 */
			if(result.contains(":")) { //有前缀
				String[] str = result.split(":");
				if(str[0].equalsIgnoreCase("f")) { //转发
					request.getRequestDispatcher(str[1]).forward(request, response);
				}else if(str[0].equalsIgnoreCase("r")) { //重定向
					response.sendRedirect(request.getContextPath() + str[1]);
				}else { //啥也不是
					throw new RuntimeException("自定的操作：" + str[0] + " 当前版本不支持");
				}
			}else { //没有前缀，使用默认值:转发
				request.getRequestDispatcher(result).forward(request, response);
			}
		} catch (Exception e) {
			throw new RuntimeException("调用的方法 " + method + " 内部发现了异常");
		}
		
	}
}
