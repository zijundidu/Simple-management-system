/**
 * 
 */
package com.test.goods.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.test.dao.GoodsIDao;
import com.test.goods.domain.Goods;
import com.test.goods.domain.PageBean;
import com.test.jdbc.utils.TxQueryRunner;

/**
 * TODO
 * 
 * @author Prince
 * @date 2020年4月29日
 */
public class GoodsDao implements GoodsIDao {
	QueryRunner qr = new TxQueryRunner();

	@Override
	public Goods foundByName(String gname) {
		String sql = "select * from goods where gname=?";
		Object[] params = { gname };
		try {
			return qr.query(sql, new BeanHandler<Goods>(Goods.class), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void add(Goods form) {
		String sql = "insert into goods values(?,?,?,?,?,?,?,?)";
		Object[] params = { form.getGid(), form.getGname(), form.getGnum(), form.getGsale(), form.getGprice(),
				form.getRemarks(), form.getGtype(), form.getParam() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException("添加错误，请输入正确的数据！");
		}
	}

//	@Override
//	public List<Goods> findAll() {
//		String sql = "select * from goods";
//		try {
//			return qr.query(sql, new BeanListHandler<Goods>(Goods.class));
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}

	@Override
	public void delete(String gid) {
		String sql = "delete from goods where gid=?";
		Object[] params = { gid };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Goods preEdit(String gid) {
		String sql = "select * from goods where gid=?";
		Object[] params = { gid };
		try {
			return qr.query(sql, new BeanHandler<Goods>(Goods.class), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void edit(Goods form) {
		String sql = "update goods set gname=?,gnum=?,gsale=?,gprice=?,remarks=?,gtype=?,param=? where gid=?";
		Object[] params = { form.getGname(), form.getGnum(), form.getGsale(), form.getGprice(), form.getRemarks(),
				form.getGtype(), form.getGid(),form.getParam() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageBean<Goods> findAll(int pc, int ps) {
		// TODO 创建PageBean对象，设置pc,ps,pb，得到tr,beanList，设置给pb，返回pb
		PageBean<Goods> pb = new PageBean<Goods>();
		pb.setPc(pc);
		pb.setPs(ps);

		try {
			// 得到tr
			String sql = "select count(*) from goods";
			Number num = (Number) qr.query(sql, new ScalarHandler()); // ScalarHandler单行单列
			int tr = num.intValue();
			pb.setTr(tr);

			// 得到beanList
			sql = "select * from goods order by gname limit ?,?";
			Object[] params = { ((pc - 1) * ps), ps };

			List<Goods> beanList = qr.query(sql, new BeanListHandler<Goods>(Goods.class), params);
			pb.setBeanList(beanList);
			return pb;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageBean<Goods> query(Goods form, int pc, int ps) {
		/*
		 * 创建pageBean对象 设置已有参数，pc和ps 得到Tr和beanList
		 */
		PageBean<Goods> pb = new PageBean<Goods>();
		pb.setPc(pc);
		pb.setPs(ps);

		try {
			StringBuilder presql = new StringBuilder("select count(*) from goods ");
			StringBuilder wheresql = new StringBuilder(" where 1=1 ");

			List<Object> params = new ArrayList<Object>();

			String name = form.getGname();
			if (name != null && !name.trim().isEmpty()) {
				wheresql.append(" and gname like ? ");
				params.add("%" + form.getGname() + "%");
			}

			Number num = (Number) qr.query(presql.append(wheresql).toString(), new ScalarHandler(), params.toArray());
			int tr = num.intValue();
			pb.setTr(tr);

			// 得到beanList
			StringBuilder sql = new StringBuilder("select * from goods ");
			StringBuilder limitsql = new StringBuilder(" limit ?,?");
			params.add((pc - 1) * ps);
			params.add(ps);

			List<Goods> beanList = qr.query(sql.append(wheresql).append(limitsql).toString(),
					new BeanListHandler<Goods>(Goods.class), params.toArray());
			pb.setBeanList(beanList);

			return pb;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.test.dao.GoodsIDao#queryPlus(com.test.goods.domain.Goods, int, int,
	 * int, int, int, int)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageBean<Goods> queryPlus(Goods form, Map<String, String> map, int pc, int ps) {
		/*
		 * 创建pageBean对象 设置已有参数，pc和ps 得到Tr和beanList
		 */
		PageBean<Goods> pb = new PageBean<Goods>();
		pb.setPc(pc);
		pb.setPs(ps);

		try {
			StringBuilder presql = new StringBuilder("select count(*) from goods ");
			StringBuilder wheresql = new StringBuilder(" where 1=1 ");

			List<Object> params = new ArrayList<Object>();

			String name = form.getGname();
			if (name != null && !name.trim().isEmpty()) {
				wheresql.append(" and gname like ? ");
				params.add("%" + form.getGname() + "%");
			}

			String remarks = form.getRemarks();
			if (remarks != null && !remarks.trim().isEmpty()) {
				wheresql.append(" and remarks like ? ");
				params.add("%" + form.getRemarks() + "%");
			}
			
			String param = form.getParam();
			if (param != null && !param.trim().isEmpty()) {
				wheresql.append(" and param like ? ");
				params.add("%" + form.getParam() + "%");
			}

			if (map.get("minn") != null && !map.get("minn").trim().isEmpty() && map.get("maxn") != null && !map.get("maxn").trim().isEmpty()) {
				wheresql.append(" and gnum>? and gnum<? ");
				params.add(Integer.valueOf(map.get("minn")));
				params.add(Integer.valueOf(map.get("maxn")));
			}

			if (map.get("mins") != null && !map.get("mins").trim().isEmpty() && map.get("maxs") != null
					&& !map.get("maxs").trim().isEmpty()) {
				wheresql.append(" and gsale>? and gsale<? ");
				params.add(Integer.valueOf(map.get("mins")));
				params.add(Integer.valueOf(map.get("maxs")));
			}

			if (map.get("minp") != null && !map.get("minp").trim().isEmpty() && map.get("maxp") != null
					&& !map.get("maxp").trim().isEmpty()) {
				wheresql.append(" and gprice>? and gprice<? ");
				params.add(Integer.valueOf(map.get("minp")));
				params.add(Integer.valueOf(map.get("maxp")));
			}

			Number nums = (Number) qr.query(presql.append(wheresql).toString(), new ScalarHandler(), params.toArray());
			int tr = nums.intValue();
			pb.setTr(tr);

			// 得到beanList
			StringBuilder sql = new StringBuilder("select * from goods ");
			StringBuilder limitsql = new StringBuilder(" limit ?,?");
			params.add((pc - 1) * ps);
			params.add(ps);

			List<Goods> beanList = qr.query(sql.append(wheresql).append(limitsql).toString(),
					new BeanListHandler<Goods>(Goods.class), params.toArray());
			pb.setBeanList(beanList);

			return pb;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
