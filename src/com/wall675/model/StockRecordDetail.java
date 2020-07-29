package com.wall675.model;
//库存记录 未建表
public class StockRecordDetail {
	private String orderCode;  //序列号
	private String stockTime;  //库存变动时间
	private String productCode;  //产品编号
	private String productName;  //产品名称
	private int num;  //库存数
	private float itemPrice;  //明细总价
	
	public StockRecordDetail(){}

	public StockRecordDetail(String orderCode, String stockTime,
			String productCode, String productName, int num, float itemPrice) {
		this.orderCode = orderCode;
		this.stockTime = stockTime;
		this.productCode = productCode;
		this.productName = productName;
		this.num = num;
		this.itemPrice = itemPrice;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getStockTime() {
		return stockTime;
	}

	public void setStockTime(String stockTime) {
		this.stockTime = stockTime;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public float getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	
}
