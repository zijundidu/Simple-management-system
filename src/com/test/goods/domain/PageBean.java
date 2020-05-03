/**
 * 
 */
package com.test.goods.domain;

import java.util.List;

/**
 * TODO 分页数据
 * @author Prince
 * @date 2020年4月30日  
 * @param <T>
 */
public class PageBean<T> {
	private int pc; // 当前页码 page code
//	private int tp; // 总页数 total pages
	private int tr; // 总记录数 total record
	private int ps; // 每页记录数 page size
	private List<T> beanList; // 当前页记录

	private String url; // url后的条件，用于搜索时超链接的参数传递

	/**
	 * 
	 */
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pc
	 * @param tr
	 * @param ps
	 * @param beanList
	 * @param url
	 */
	public PageBean(int pc, int tr, int ps, List<T> beanList, String url) {
		super();
		this.pc = pc;
		this.tr = tr;
		this.ps = ps;
		this.beanList = beanList;
		this.url = url;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public int getTp() {
		int tp = tr / ps;
		return tr % ps == 0 ? tp : tp + 1;
	}

//	public void setTp(int tp) {
//		this.tp = tp;
//	}

	public int getTr() {
		return tr;
	}

	public void setTr(int tr) {
		this.tr = tr;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}