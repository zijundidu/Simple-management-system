package com.test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.test.admin.domain.Admin;

@WebFilter(
		urlPatterns = { 
				"/Admin/*"
		}, 
		servletNames = { "AdminServlet", "GoodsServlet", "TypesnServlet" })
public class AdminFilter implements Filter {
	
    public AdminFilter() {
    	
    }
    
	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * 从session获取用户信息
		 * 如果session中存在，放行
		 * 否则保存错误信息msg并转发到login.jsp
		 */
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		Admin admin = (Admin)httpRequest.getSession().getAttribute("sessionAdmin");
		
		if(admin != null) { //如果存在用户信息
			chain.doFilter(request, response); //放行
		}else { //如果不存在
//			httpRequest.setAttribute("msg", "您还没有登录");
			httpRequest.getRequestDispatcher("/Admin/login.jsp").forward(httpRequest, response);
		}
	}
	
	/**
	 * Filter创建之后马上执行
	 * 用来做初始化
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
