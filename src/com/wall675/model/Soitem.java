package com.wall675.model;

//销售单明细
public class Soitem {
	private String soID;  //销售单编号
	private String productCode;  //产品编号
	private String name;//产品名称 -关联查询用
	private float unitPrice;  //产品单价
	private int num;  //产品数量
	private String unitName;  //数量单位
	private float itemPrice;  //明细总价
	
	public Soitem() {
	}

	public Soitem(String soID, String name, float unitPrice, int num,
			String unitName, float itemPrice,String productCode) {
		this.soID = soID;
		this.name = name;
		this.unitPrice = unitPrice;
		this.num = num;
		this.unitName = unitName;
		this.itemPrice = itemPrice;
		this.productCode = productCode;
	}

	public String getSoID() {
		return soID;
	}

	public void setSoID(String soID) {
		this.soID = soID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public float getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Override
	public String toString() {
		return "Soitem [soID=" + soID + ", productCode=" + productCode + ", unitPrice=" + unitPrice + ", num=" + num
				+ ", unitName=" + unitName + ", itemPrice=" + itemPrice + "]";
	}
	
	
}
