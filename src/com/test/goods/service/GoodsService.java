/**
 * 
 */
package com.test.goods.service;

import java.util.Map;

import com.test.dao.DaoFactory;
import com.test.dao.GoodsIDao;
import com.test.goods.domain.Goods;
import com.test.goods.domain.PageBean;

/**
 * TODO
 * 
 * @author Prince
 * @date 2020年4月29日
 */
public class GoodsService {

	GoodsIDao dao = DaoFactory.getGoodsDao();

	/**
	 * 保存商品信息到数据库
	 * 
	 * @param form
	 */
	public void add(Goods form) {
		/*
		 * 首先检查数据库中是否存在商品 如果不存在添加 如果存在，报错
		 */
		if (dao.foundByName(form.getGname()) != null) {
			throw new RuntimeException("商品" + form.getGname() + "已存在，不能添加！");
		}
		dao.add(form);

	}

//	/**
//	 * @return
//	 */
//	public List<Goods> findAll() {
//		
//		return dao.findAll();
//	}

	/**
	 * @param parameter
	 */
	public void delete(String gid) {

		dao.delete(gid);
	}

	/**
	 * @param attribute
	 */
	public Goods preEdit(String gid) {

		return dao.preEdit(gid);
	}

	/**
	 * @param form
	 */
	public void edit(Goods form) {

		dao.edit(form);
	}

	/**
	 * @param pc
	 * @param ps
	 * @return
	 */
	public PageBean<Goods> findAll(int pc, int ps) {
		return dao.findAll(pc, ps);
	}

	/**
	 * @param form
	 * @param pc
	 * @param ps
	 * @return
	 */
	public PageBean<Goods> query(Goods form, int pc, int ps) {

		return dao.query(form, pc, ps);
	}

	/**
	 * @param form
	 * @param mins
	 * @param maxs
	 * @param minp
	 * @param maxp
	 * @param pc
	 * @param ps
	 * @return
	 */
	public PageBean<Goods> queryPlus(Goods form, Map<String, String> map, int pc,
			int ps) {

		return dao.queryPlus(form, map, pc, ps);
	}

}
