package com.wall675.model;

//用户表
public class SCMUser {
	private String account;   //用户账号
	private String password;   //密码
	private String name;   //姓名
	private String createDate;   //添加日期
	private String status;  //锁定状态
	
	public SCMUser(){}

	public SCMUser(String account, String password, String name,
			String createDate, String status) {
		this.account = account;
		this.password = password;
		this.name = name;
		this.createDate = createDate;
		this.status = status;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
