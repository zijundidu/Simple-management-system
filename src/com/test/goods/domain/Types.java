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
public class Types {
	private String gtype;
	private String remarks;

	/**
	 * 
	 */
	public Types() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param gtype
	 * @param remarks
	 */
	public Types(String gtype, String remarks) {
		super();
		this.gtype = gtype;
		this.remarks = remarks;
	}

	public String getGtype() {
		return gtype;
	}

	public void setGtype(String gtype) {
		this.gtype = gtype;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Types [gtype=" + gtype + ", remarks=" + remarks + "]";
	}

}
