package com.test.goods.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.commons.CommonUtils;
import com.test.goods.domain.Goods;
import com.test.goods.domain.PageBean;
import com.test.goods.service.GoodsService;
import com.test.servlet.BaseServlet;

/**
 * Servlet implementation class GoodsServlet
 */
@WebServlet("/GoodsServlet")
public class GoodsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	// 依赖Service
	GoodsService goodsService = new GoodsService();

	/**
	 * 添加商品
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

		Goods form = new Goods();
		form.setGid(CommonUtils.uuid());
		form.setGname(request.getParameter("gname"));
		form.setGnum(Integer.valueOf(request.getParameter("gnum")));
		form.setGtype(request.getParameter("gtype"));
		form.setGsale(Double.valueOf(request.getParameter("gsale")));
		form.setGprice(Double.valueOf(request.getParameter("gprice")));
		form.setRemarks(request.getParameter("remarks"));
		form.setParam(request.getParameter("param"));
		
		form = encoding(form);
		try {
			// 直接保存
			goodsService.add(form);
			request.setAttribute("msg", "添加成功！");
			// 然后转发
			return "f:/Admin/add.jsp";
		} catch (RuntimeException e) { // 如果存在
			// 保存错误信息
			request.setAttribute("msg", e.getMessage());
			form = encoding(form);
			// 回显数据
			request.setAttribute("goods", form);
			// 转发到login界面
			// request.getRequestDispatcher("/Admin/login.jsp").forward(request, response);
			return "f:/Admin/add.jsp";
		}
	}

	/**
	 * 查询所有商品
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
		/*
		 * 获取页面传递的page code 给定page size的值 使用pc和ps调用service方法，得到pageBean 保存到request域中
		 * 转发到list.jsp
		 */
		int pc = getPc(request);
		int ps = 10; // 给定page size，每页10行
		PageBean<Goods> pb = goodsService.findAll(pc, ps);
		
		// 将得到的url保存在pb中
		pb.setUrl(getUrl(request));

		request.setAttribute("pb", pb); // 保存到request域中

		return "f:/Admin/goods.jsp";
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
	 * 删除商品数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 请求编码（POST）
		response.setContentType("text/html;charset=UTF-8"); // 相应编码
		
		goodsService.delete(request.getParameter("gid"));
		request.setAttribute("msg", "商品已成功删除");
		
		if(request.getParameter("type").equals("goodsDelete")) {
			return "f:/GoodsServlet?method=findAll";
		}else if(request.getParameter("type").equals("typesDelete")) {
			return "f:/TypesServlet?method=query";
		}else {
			return "";
		}
		
	}

	/**
	 * 跳转 编辑 页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String preEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 请求编码（POST）
		response.setContentType("text/html;charset=UTF-8"); // 相应编码

		request.setAttribute("editGoods", goodsService.preEdit(request.getParameter("gid")));
		
		request.getSession().setAttribute("editMessage", request.getParameter("type"));
		return "f:/Admin/edit.jsp";
	}

	/**
	 * 编辑商品
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 请求编码（POST）
		response.setContentType("text/html;charset=UTF-8"); // 相应编码

		Goods form = new Goods();
		form.setGid(request.getParameter("gid"));
		form.setGname(request.getParameter("gname"));
		form.setGnum(Integer.valueOf(request.getParameter("gnum")));
		form.setGtype(request.getParameter("gtype"));
		form.setGsale(Double.valueOf(request.getParameter("gsale")));
		form.setGprice(Double.valueOf(request.getParameter("gprice")));
		form.setRemarks(request.getParameter("remarks"));
		form.setParam(request.getParameter("param"));

		form = encoding(form);
		try {
			// 直接保存
			goodsService.edit(form);
			request.setAttribute("msg", "修改成功！");
			// 然后转发
			if(request.getSession().getAttribute("editMessage").equals("goodsEdit")) {
				return "f:/GoodsServlet?method=findAll";
			}else if(request.getSession().getAttribute("editMessage").equals("typesEdit")) {
				return "f:/TypesServlet?method=query";
			}else {
				return "";
			}
			
		} catch (RuntimeException e) { // 如果存在
			// 保存错误信息
			request.setAttribute("msg", e.getMessage());
			// 回显数据
			request.setAttribute("editGoods", form);
			// 转发到login界面
			// request.getRequestDispatcher("/Admin/login.jsp").forward(request, response);
			return "f:/Admin/edit.jsp";
		}
	}

	/**
	 * 处理表单的数据
	 * 
	 * @param c
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private Goods encoding(Goods c) throws UnsupportedEncodingException {
		String name = c.getGname();
		String remaeks = c.getRemarks();
		String type = c.getGtype();
		String param = c.getParam();

		if (name != null && !name.trim().isEmpty()) {
			name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
			c.setGname(name);
		}

		if (remaeks != null && !remaeks.trim().isEmpty()) {
			remaeks = new String(remaeks.getBytes("ISO-8859-1"), "UTF-8");
			c.setRemarks(remaeks);
		}

		if (type != null && !type.trim().isEmpty()) {
			type = new String(type.getBytes("ISO-8859-1"), "UTF-8");
			c.setGtype(type);
		}
		
		if (param != null && !param.trim().isEmpty()) {
			param = new String(param.getBytes("ISO-8859-1"), "UTF-8");
			c.setParam(param);
		}

		return c;
	}

	/**
	 * 查询商品，分页处理
	 * 
	 * @param request
	 * @param responce
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 请求编码（POST）
		response.setContentType("text/html;charset=UTF-8"); // 相应编码

		/*
		 * 把条件封装到Goods对象中 得到pc，给出ps，使用pc和ps调用service方法得到pageBean 把PageBean保存到request域中
		 * 转发到list.jsp
		 */
		// 将表单数据封装到一个Goods对象中
		Goods form = new Goods();
		form.setGname(request.getParameter("gname"));

		/*
		 * 处理请求GET方式编码的问题
		 */
		form = encoding(form);

		int pc = getPc(request);
		int ps = 10; // 给定page size，每页10行

		PageBean<Goods> pb = goodsService.query(form, pc, ps);

		// 将得到的url保存在pb中
		pb.setUrl(getUrl(request));

		request.setAttribute("pb", pb); // 保存到request域中

		return "f:/Admin/goods.jsp";
	}
	
	/**
	 * 高级查询，分页处理
	 * 
	 * @param request
	 * @param responce
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String queryPlus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 请求编码（POST）
		response.setContentType("text/html;charset=UTF-8"); // 相应编码

		/*
		 * 把条件封装到Goods对象中 得到pc，给出ps，使用pc和ps调用service方法得到pageBean 把PageBean保存到request域中
		 * 转发到list.jsp
		 */
		// 将表单数据封装到一个Goods对象中
		Goods form = new Goods();
		form.setGname(request.getParameter("gname"));
		form.setRemarks(request.getParameter("remarks"));
		form.setParam(request.getParameter("param"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("minn", request.getParameter("minNum"));
		map.put("maxn", request.getParameter("maxNum"));
		map.put("mins", request.getParameter("minSale"));
		map.put("maxs", request.getParameter("maxSale"));
		map.put("minp", request.getParameter("minprice"));
		map.put("maxp", request.getParameter("maxPrice"));
		/*
		 * 处理请求GET方式编码的问题
		 */
		form = encoding(form);

		int pc = getPc(request);
		int ps = 10; // 给定page size，每页10行

		PageBean<Goods> pb = goodsService.queryPlus(form, map, pc, ps);

		// 将得到的url保存在pb中
		pb.setUrl(getUrl(request));

		request.setAttribute("pb", pb); // 保存到request域中

		return "f:/Admin/goods.jsp";
	}
}
