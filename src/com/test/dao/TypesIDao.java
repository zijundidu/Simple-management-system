/**
 * 
 */
package com.test.dao;

import java.sql.SQLException;
import java.util.List;

import com.test.goods.domain.Goods;
import com.test.goods.domain.PageBean;
import com.test.goods.domain.Types;

/**
 * TODO管理 Goods Dao接口
 * @author Prince
 * @date 2020年4月29日  
 */
public interface TypesIDao {

	/**
	 * 通过名称查找分类
	 * @param gname
	 * @return
	 * @throws SQLException 
	 */
	Types foundByName(String gtype);

	/**
	 * 添加商品
	 * @param form
	 */
	void add(Types form);

	/**
	 * @return
	 */
	List<Types> findAll();

	/**
	 * @param gtype
	 * @param pc
	 * @param ps
	 * @return
	 */
	PageBean<Goods> query(String gtype, int pc, int ps);

	/**
	 * @param type
	 */
	void change(String type);

	/**
	 * @param type
	 */
	void delete(String type);

}
