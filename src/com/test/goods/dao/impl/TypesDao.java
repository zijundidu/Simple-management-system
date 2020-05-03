/**
 * 
 */
package com.test.goods.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.test.dao.TypesIDao;
import com.test.goods.domain.Goods;
import com.test.goods.domain.PageBean;
import com.test.goods.domain.Types;
import com.test.jdbc.utils.TxQueryRunner;

/**
 * TODO
 * @author Prince
 * @date 2020年4月29日  
 */
public class TypesDao implements TypesIDao {
	QueryRunner qr = new TxQueryRunner();

	
	@Override
	public Types foundByName(String gtype){
		String sql = "select * from types where gtype=?";
		Object [] params = {gtype};
		try {
			return qr.query(sql, new BeanHandler<Types>(Types.class), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	@Override
	public void add(Types form) {
		String sql = "insert into types values(?,?)";
		Object [] params = {form.getGtype(), form.getRemarks()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Types> findAll() {
		String sql = "select * from types";
		try {
			return qr.query(sql, new BeanListHandler<Types>(Types.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean<Goods> query(String gtype, int pc, int ps) {
		// TODO 创建PageBean对象，设置pc,ps,pb，得到tr,beanList，设置给pb，返回pb
				PageBean<Goods> pb = new PageBean<Goods>();
				pb.setPc(pc);
				pb.setPs(ps);

				try {
					// 得到tr
					String sql = "select count(*) from goods where gtype=?";
					Object [] params = {gtype};
					Number num = (Number) qr.query(sql, new ScalarHandler(), params); // ScalarHandler单行单列
					int tr = num.intValue();
					pb.setTr(tr);

					// 得到beanList
					sql = "select * from goods where gtype=? order by gname limit ?,?";
					Object[] params1 = {gtype, ((pc - 1) * ps), ps };

					List<Goods> beanList = qr.query(sql, new BeanListHandler<Goods>(Goods.class), params1);
					pb.setBeanList(beanList);
					return pb;
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
	}

	@Override
	public void change(String type) {
		String sql = "update goods set gtype='未分类' where gtype=?";
		Object [] params = {type};
		
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public void delete(String type) {
		String sql = "delete from types where gtype=?";
		Object [] params = {type};
		
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
