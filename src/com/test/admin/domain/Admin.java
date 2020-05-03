/**
 * 
 */
package com.test.admin.domain;

/**
 * TODO
 * 管理员属性
 * @author Prince
 * @date 2020年4月28日
 */
public class Admin {
	private String aid;
	private String username;
	private String password;

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 */
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aid
	 * @param username
	 * @param password
	 */
	public Admin(String aid, String username, String password) {
		super();
		this.aid = aid;
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", username=" + username + ", password=" + password + "]";
	}

}
