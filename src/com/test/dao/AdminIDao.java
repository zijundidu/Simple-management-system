/**
 * 
 */
package com.test.dao;

import com.test.admin.domain.Admin;

/**
 * TODO 管理 AdminDAO接口
 * @author Prince
 * @date 2020年4月28日  
 */
public interface AdminIDao {

	/**
	 * @param form
	 * @return
	 */
	public Admin login(Admin form);

}
