package com.wall675.model;
//盘点表 checkstock表
public class CheckStockInfo {
	private int stockID; //序列号
	private String productCode;//产品编号
	private int originNum;  //原始数量
	private int realNum;  //实际数量
	private String stockTime;  //盘点时间
	private String createUser;   //经手人
	private String Description;  //损益原因
	private String Type;  //损益类型
	
	public CheckStockInfo(){}

	public CheckStockInfo(int stockID, String productCode, int originNum,
			int realNum, String stockTime, String createUser,
			String description, String type) {
		this.stockID = stockID;
		this.productCode = productCode;
		this.originNum = originNum;
		this.realNum = realNum;
		this.stockTime = stockTime;
		this.createUser = createUser;
		Description = description;
		Type = type;
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

	public int getOriginNum() {
		return originNum;
	}

	public void setOriginNum(int originNum) {
		this.originNum = originNum;
	}

	public int getRealNum() {
		return realNum;
	}

	public void setRealNum(int realNum) {
		this.realNum = realNum;
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

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}
	
	
}
