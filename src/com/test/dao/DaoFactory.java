/**
 * 
 */
package com.test.dao;

import java.io.InputStream;
import java.util.Properties;

/**
 * TODO 工厂类
 * @author Prince
 * @date 2020年4月15日  
 */
public class DaoFactory {
	private static Properties props = null;
	static {
		//加载配置文件内容到props对象中
		try {
			InputStream in = DaoFactory.class
					.getClassLoader().getResourceAsStream("dao.properties");
			props = new Properties();
			props.load(in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	/**
	 * 使用反射，创建对象
	 * @param className
	 * @return
	 */
	public static AdminIDao getAdminDao() {
		String daoClassName = props.getProperty("com.test.dao.IDao.Admin");
		AdminIDao instance = null;
		try {
			instance = (AdminIDao)Class.forName(daoClassName).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} 
		return instance;
	}
	
	/**
	 * 使用反射，创建对象
	 * @param className
	 * @return
	 */
	public static GoodsIDao getGoodsDao() {
		String daoClassName = props.getProperty("com.test.dao.IDao.Goods");
		GoodsIDao instance = null;
		try {
			instance = (GoodsIDao)Class.forName(daoClassName).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} 
		return instance;
	}
	
	/**
	 * 使用反射，创建对象
	 * @param className
	 * @return
	 */
	public static TypesIDao getTypesDao() {
		String daoClassName = props.getProperty("com.test.dao.IDao.Types");
		TypesIDao instance = null;
		try {
			instance = (TypesIDao)Class.forName(daoClassName).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} 
		return instance;
	}

}
