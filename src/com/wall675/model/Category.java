package com.wall675.model;
//产品分类表
public class Category {
	private int categoryID;  //类别序列号
	private String name;  //名称
	private String remark;//描述

	public Category() {
	}

	public Category(int category, String name, String remark) {
		categoryID = category;
		this.name = name;
		this.remark = remark;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int category) {
		categoryID = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
