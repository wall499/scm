package com.wall675.model;
//产品表
public class Product {
	private String productCode;  //产品编号
	private int categoryID;  //类别序列号
	private String categoryName;  //类别名字
	private String name;  //产品名称
	private String unitName;  //数量单位
	private float price;  //销售价
	private String createDate;  //添加日期
	private String remark;  //产品描述
	private int num; //库存数
	private int poNum;  //采购在途数
	private int soNum; //销售待发数

	public Product(){}
	
	/*
	 * public Product(String productCode, int categoryID, String name,String
	 * unitName, float price, String createDate, String remark, int num,int poNum,
	 * int soNum) { this.productCode = productCode; this.categoryID = categoryID;
	 * this.name = name; this.unitName = unitName; this.price = price;
	 * this.createDate = createDate; this.remark = remark; this.num=num; this.poNum
	 * = poNum; this.soNum = soNum; }
	 */
	public Product(String productCode, String CategoryName, String name,String unitName, float price, String createDate, String remark,
			int num,int poNum, int soNum,int categoryID) {
		this.productCode = productCode;
		this.categoryName = CategoryName;
		this.name = name;
		this.unitName = unitName;
		this.price = price;
		this.createDate = createDate;
		this.remark = remark;
		this.num=num;
		this.poNum = poNum;
		this.soNum = soNum;
		this.categoryID = categoryID;
	}

	public Product(String productCode, String name, String unitName, int num,int poNum,int soNum) {
		this.productCode = productCode;
		this.name = name;
		this.unitName = unitName;
		this.num = num;
		this.poNum = poNum;
		this.soNum = soNum;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getPoNum() {
		return poNum;
	}

	public void setPoNum(int poNum) {
		this.poNum = poNum;
	}

	public int getSoNum() {
		return soNum;
	}

	public void setSoNum(int soNum) {
		this.soNum = soNum;
	}

	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", categoryID=" + categoryID + ", categoryName=" + categoryName
				+ ", name=" + name + ", unitName=" + unitName + ", price=" + price + ", createDate=" + createDate
				+ ", remark=" + remark + ", num=" + num + ", poNum=" + poNum + ", soNum=" + soNum + "]";
	}
	

}
