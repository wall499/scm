package com.wall675.model;


//库存记录 表名stockrecord
public class StockRecordInfo {
	private int stockID; //序列号
	private String productCode; //产品编号
	private String orderCode;  //相关单据号
	private int stockNum;  //库存变化数量
	private int stockType;  //库存变化类型
	private String stockTime; //库存变化时间
	private String createUser; //经手人
	
	public StockRecordInfo(){}

	public StockRecordInfo(int stockID, String productCode, String orderCode,int stockNum, int stockType, String stockTime, String createUser) {
		this.stockID = stockID;
		this.productCode = productCode;
		this.orderCode = orderCode;
		this.stockNum = stockNum;
		this.stockType = stockType;
		this.stockTime = stockTime;
		this.createUser = createUser;
	}

	public int getStockID() {
		return stockID;
	}

	public void setStockID(int stockID) {
		this.stockID = stockID;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public int getStockNum() {
		return stockNum;
	}

	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}

	public int getStockType() {
		return stockType;
	}

	public void setStockType(int stockType) {
		this.stockType = stockType;
	}

	public String getStockTime() {
		return stockTime;
	}

	public void setStockTime(String stockTime) {
		this.stockTime = stockTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
}
