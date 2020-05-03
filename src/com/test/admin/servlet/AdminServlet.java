package com.test.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.admin.domain.Admin;
import com.test.admin.service.AdminService;
import com.test.servlet.BaseServlet;

/**
 * TODO 管理员 servlet
 * @author Prince
 * @date 2020年4月28日
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//依赖Service
	AdminService adminService = new AdminService();
	
	/**
	 * 登录功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8"); // 请求编码（POST）
		response.setContentType("text/html;charset=UTF-8"); // 相应编码
		
		//获取表单数据
		//Admin form = CommonUtils.toBean(request.getParameterMap(), Admin.class);
		Admin form = new Admin();
		form.setUsername(request.getParameter("username"));
		form.setPassword(request.getParameter("password"));
		
		//检查是否是管理员
		try { //如果是管理员
			//封装数据，此数据为数据库中的数据
			Admin admin = adminService.login(form);
			//保存在session中
			request.getSession().setAttribute("sessionAdmin", admin);
			//转发到管理员主页面
			//response.sendRedirect(request.getContextPath() + "/Admin/index.jsp");
			return "r:/Admin/index.jsp";
		}catch(RuntimeException e) { //如果不是管理员
			//保存错误信息
			request.setAttribute("msg", e.getMessage());
			//回显数据
			request.setAttribute("admin", form);
			//转发到login界面
			//request.getRequestDispatcher("/Admin/login.jsp").forward(request, response);
			return "f:/Admin/login.jsp";
		}
	}
	
	/**
	 * 退出功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8"); // 请求编码（POST）
		response.setContentType("text/html;charset=UTF-8"); // 相应编码
		
		request.getSession().invalidate(); //直接销毁session
		return "r:/Admin/login.jsp";
	}
	
	/**
	 * 跳转 添加 页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8"); // 请求编码（POST）
		response.setContentType("text/html;charset=UTF-8"); // 相应编码
		
		String addMessage = (String) request.getParameter("type");
		request.getSession().setAttribute("addMessage", addMessage);
		request.getSession().setAttribute("addGtype", request.getParameter("gtype"));
		return "r:/Admin/add.jsp";
	}
	
	public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8"); // 请求编码（POST）
		response.setContentType("text/html;charset=UTF-8"); // 相应编码
		
		response.getWriter().print(request.getParameter("type"));
	}
	
}
