package com.test.goods.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.goods.domain.Goods;
import com.test.goods.domain.PageBean;
import com.test.goods.domain.Types;
import com.test.goods.service.TypesService;
import com.test.servlet.BaseServlet;

/**
 * Servlet implementation class GoodsServlet
 */
@WebServlet("/TypesServlet")
public class TypesServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	// 依赖Service
	TypesService typesService = new TypesService();

	/**
	 * 添加分类
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 请求编码（POST）
		response.setContentType("text/html;charset=UTF-8"); // 相应编码

		Types form = new Types();
		form.setGtype(request.getParameter("gtype"));
		form.setRemarks(request.getParameter("remarks"));

		form = encoding(form);
		try {
			// 直接保存
			typesService.add(form);
			request.setAttribute("msg", "添加成功！");
			// 然后转发
			return "f:/Admin/add.jsp";
		} catch (RuntimeException e) { // 如果存在
			// 保存错误信息
			request.setAttribute("msg", e.getMessage());
			// 回显数据
			request.setAttribute("types", form);
			// 转发到login界面
			// request.getRequestDispatcher("/Admin/login.jsp").forward(request, response);
			return "f:/Admin/add.jsp";
		}
	}

	/**
	 * 查找所有分类
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 请求编码（POST）
		response.setContentType("text/html;charset=UTF-8"); // 相应编码

		request.getSession().setAttribute("typesList", typesService.findAll());

		return "f:/Admin/left.jsp";
	}

	/**
	 * 查找之前保存分类
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String preQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 请求编码（POST）
		response.setContentType("text/html;charset=UTF-8"); // 相应编码

		request.getSession().setAttribute("gtype", request.getParameter("gtype"));

		request.getSession().setAttribute("mainFlag", "other");

		return "f:/TypesServlet?method=findAll";
	}

	/**
	 * 按照分类查找商品
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */

	public String query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 请求编码（POST）
		response.setContentType("text/html;charset=UTF-8"); // 相应编码

		String gtype = (String) request.getSession().getAttribute("gtype");
		/*
		 * 获取页面传递的page code 给定page size的值 使用pc和ps调用service方法，得到pageBean 保存到request域中
		 * 转发到list.jsp
		 */
		int pc = getPc(request);
		int ps = 6; // 给定page size，每页10行
		PageBean<Goods> pb = typesService.query(gtype, pc, ps);

		// 将得到的url保存在pb中
		pb.setUrl(getUrl(request));

		request.setAttribute("pb", pb); // 保存到request域中

		return "f:/Admin/main.jsp";
	}

	/**
	 * 获取page code参数
	 * 
	 * @param request
	 * @return
	 */
	private int getPc(HttpServletRequest request) {
		/*
		 * 参数不存在，则为1 存在，转换为Int
		 */
		String value = request.getParameter("pc");
		if (value == null || value.trim().isEmpty()) {
			return 1;
		} else {
			return Integer.parseInt(value);
		}
	}

	/**
	 * 截取url /项目名/Servlet路径？参数字符串
	 * 
	 * @param request
	 * @return
	 */
	private String getUrl(HttpServletRequest request) {
		String contextPath = request.getContextPath(); // 项目名
		String servletPath = request.getServletPath(); // servlet路径
		String queryString = request.getQueryString(); // 参数字符串

		if (queryString.contains("&pc=")) {
			int index = queryString.lastIndexOf("&pc="); // 获取&pc=的位置
			queryString = queryString.substring(0, index); // 截取参数直到&pc=处

		}

		return contextPath + servletPath + "?" + queryString;
	}

	/**
	 * 处理表单的数据
	 * 
	 * @param c
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private Types encoding(Types c) throws UnsupportedEncodingException {
		String type = c.getGtype();
		String remaeks = c.getRemarks();

		if (remaeks != null && !remaeks.trim().isEmpty()) {
			remaeks = new String(remaeks.getBytes("ISO-8859-1"), "UTF-8");
			c.setRemarks(remaeks);
		}

		if (type != null && !type.trim().isEmpty()) {
			type = new String(type.getBytes("ISO-8859-1"), "UTF-8");
			c.setGtype(type);
		}

		return c;
	}

	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 请求编码（POST）
		response.setContentType("text/html;charset=UTF-8"); // 相应编码

		/*
		 * 先判断此分类是不是未分类 如果是，返回，并弹窗“不能删除” 如果不是，先将所有分类中的数据变为“未分类”的type 然后再删除分类type
		 */
		String type = request.getParameter("gtype");
		if (type.equals("未分类")) {
			String mess = "不能删除默认的分类！";
			request.setAttribute("msg", mess);
			return "f:/TypesServlet?method=findAll";
		} else {
			// 变数据
			typesService.change(type);
			// 删数据
			typesService.delete(type);
			return "r:/TypesServlet?method=findAll";
		}
	}
}
