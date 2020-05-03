/**
 * 
 */
package com.test.goods.service;

import java.util.List;

import com.test.dao.DaoFactory;
import com.test.dao.TypesIDao;
import com.test.goods.domain.Goods;
import com.test.goods.domain.PageBean;
import com.test.goods.domain.Types;

/**
 * TODO
 * 
 * @author Prince
 * @date 2020年4月29日
 */
public class TypesService {

	TypesIDao dao = DaoFactory.getTypesDao();

	/**
	 * 保存商品信息到数据库
	 * 
	 * @param form
	 */
	public void add(Types form) {
		/*
		 * 首先检查数据库中是否存在商品 如果不存在添加 如果存在，报错
		 */
		if (dao.foundByName(form.getGtype()) != null) {
			throw new RuntimeException("分类'" + form.getGtype() + "'已存在，不能添加！");
		}
		dao.add(form);

	}

	/**
	 * @return
	 */
	public List<Types> findAll() {

		return dao.findAll();
	}

	/**
	 * @param gtype
	 * @param pc
	 * @param ps
	 * @return
	 */
	public PageBean<Goods> query(String gtype, int pc, int ps) {

		return dao.query(gtype, pc, ps);
	}

	/**
	 * @param type
	 */
	public void change(String type) {

		dao.change(type);
	}

	/**
	 * @param type
	 */
	public void delete(String type) {

		dao.delete(type);
	}

}
