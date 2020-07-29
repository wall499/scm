package com.wall675.model;

public class StockRecordMain {
	private int orderNum; //订单数量
	private int totalNum;  //总入库数量
	private float totalPrice;  //入库总额
	
	public StockRecordMain() {
	}

	public StockRecordMain(int orderNum, int totalNum, float totalPrice) {
		this.orderNum = orderNum;
		this.totalNum = totalNum;
		this.totalPrice = totalPrice;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
