/**
 * 
 */
package com.test.goods.domain;

/**
 * TODO
 * 
 * @author Prince
 * @date 2020年4月29日
 */
public class Goods {
	private String gid;
	private String gname;
	private int gnum;
	private String gtype;
	private double gsale;
	private double gprice;
	private String remarks;
	private String param;

	/**
	 * 
	 */
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param gid
	 * @param gname
	 * @param gnum
	 * @param gtype
	 * @param gsale
	 * @param gprice
	 * @param remarks
	 * @param param
	 */
	public Goods(String gid, String gname, int gnum, String gtype, double gsale, double gprice, String remarks,
			String param) {
		super();
		this.gid = gid;
		this.gname = gname;
		this.gnum = gnum;
		this.gtype = gtype;
		this.gsale = gsale;
		this.gprice = gprice;
		this.remarks = remarks;
		this.param = param;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public int getGnum() {
		return gnum;
	}

	public void setGnum(int gnum) {
		this.gnum = gnum;
	}

	public String getGtype() {
		return gtype;
	}

	public void setGtype(String gtype) {
		this.gtype = gtype;
	}

	public double getGsale() {
		return gsale;
	}

	public void setGsale(double gsale) {
		this.gsale = gsale;
	}

	public double getGprice() {
		return gprice;
	}

	public void setGprice(double gprice) {
		this.gprice = gprice;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return "Goods [gid=" + gid + ", gname=" + gname + ", gnum=" + gnum + ", gtype=" + gtype + ", gsale=" + gsale
				+ ", gprice=" + gprice + ", remarks=" + remarks + ", param=" + param + "]";
	}

}
