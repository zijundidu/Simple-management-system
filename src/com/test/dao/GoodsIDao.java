/**
 * 
 */
package com.test.dao;

import java.sql.SQLException;
import java.util.Map;

import com.test.goods.domain.Goods;
import com.test.goods.domain.PageBean;

/**
 * TODO管理 Goods Dao接口
 * 
 * @author Prince
 * @date 2020年4月29日
 */
public interface GoodsIDao {

	/**
	 * 通过名称查找商品
	 * 
	 * @param gname
	 * @return
	 * @throws SQLException
	 */
	Goods foundByName(String gname);

	/**
	 * 添加商品
	 * 
	 * @param form
	 */
	void add(Goods form);

	/**
	 * @return
	 */
//	List<Goods> findAll();

	/**
	 * @param gid
	 */
	void delete(String gid);

	/**
	 * @param gid
	 * @return
	 */
	Goods preEdit(String gid);

	/**
	 * @param form
	 * @return
	 */
	void edit(Goods form);

	/**
	 * @param pc
	 * @param ps
	 * @return
	 */
	PageBean<Goods> findAll(int pc, int ps);

	/**
	 * @param form
	 * @param pc
	 * @param ps
	 * @return
	 */
	PageBean<Goods> query(Goods form, int pc, int ps);

	/**
	 * @param form
	 * @param map
	 * @param pc
	 * @param ps
	 * @return
	 */
	PageBean<Goods> queryPlus(Goods form, Map<String, String> map, int pc, int ps);

}
