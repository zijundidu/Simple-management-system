/**
 * 
 */
package com.test.admin.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.test.admin.domain.Admin;
import com.test.dao.AdminIDao;
import com.test.jdbc.utils.TxQueryRunner;

/**
 * TODO 
 * @author Prince
 * @date 2020年4月28日  
 */
public class AdminDao implements AdminIDao {
	
	QueryRunner qr = new TxQueryRunner();
	@Override
	public Admin login(Admin form) {
		
		//sql语句
		String sql = "select * from admin where username=?";
		Object[] params = {form.getUsername()};
		try {
			//返回根据传过来的对象的用户名查找的对象
			Admin admin = qr.query(sql, new BeanHandler<Admin>(Admin.class), params);
			if(admin != null) { //如果用户名存在
				return admin; //返回数据库中的对象
			}else { //不存在
				return null; //返回null
			}
		} catch (SQLException e) {
			throw new RuntimeException("用户不存在！");
		}
		
	}

}
