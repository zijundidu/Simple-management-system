/**
 * 
 */
package com.test.admin.service;

import com.test.admin.domain.Admin;
import com.test.dao.AdminIDao;
import com.test.dao.DaoFactory;

/**
 * TODO
 * @author Prince
 * @date 2020年4月28日  
 */
public class AdminService {
	private AdminIDao dao = DaoFactory.getAdminDao();

	/**
	 * @param form
	 */
	public Admin login(Admin form) {
		Admin admin = dao.login(form);
		if(admin == null) {
			throw new RuntimeException("用户名不存在！");
		}else if(!form.getPassword().equals(admin.getPassword())) {
			throw new RuntimeException("密码错误");
		}
		return admin;
	}
}
