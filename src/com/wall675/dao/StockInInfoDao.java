package com.wall675.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wall675.util.DbUtil;
import com.wall675.model.Poitem;
import com.wall675.model.StockRecordDetail;
import com.wall675.model.StockRecordMain;

//月度入库报表
public class StockInInfoDao {
	DbUtil dc = new DbUtil();
	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	//判断是否能入库
	public boolean stockIn(String[] poIDs, String name) {
		conn=dc.getCon();
		stat=dc.getStat();
		PoitemDao pitemd = new PoitemDao();
		PomainDao pmaind = new PomainDao();
		Poitem poitem = null;
		List<Poitem> poitems = new ArrayList<Poitem>();
		StockDao sd = new StockDao();
		boolean flag = false;
		int stockID = 0;
		String sql = "";
		//String stockTime = DateFormat.getDateInstance().format(new Date());
		 SimpleDateFormat stockTime=new SimpleDateFormat("yyyy-MM-dd");
		 Date date = new Date();//创建一个date对象保存当前时间
		 String dateStr = stockTime.format(date);
		System.out.println("入库时间"+dateStr);
		for (int i = 0; i < poIDs.length; i++) {
			poitem = pitemd.getPoitem(poIDs[i]);
			poitems.add(poitem);
		}
		flag = sd.addToStock(poitems);
		if (flag) {
			try {
				conn.setAutoCommit(false);
				for (int i = 0; i < poIDs.length; i++) {
					/*
					 * sql = "select * from stockrecord order by StockID desc limit 1"; rs =
					 * stat.executeQuery(sql); while (rs.next()) { stockID =
					 * Integer.parseInt(rs.getString("StockID")); } if (stockID > 0) { stockID += 1;
					 * } else { stockID = 10000 + 1; }
					 */
					sql = "insert into stockrecord(StockID,ProductCode,OrderCode,StockNum,StockType,StockTime,CreateUser) value(?,?,?,?,?,?,?)";
					poitem = pitemd.getPoitem(poIDs[i]);
					ps = dc.getPs(sql);
					ps.setInt(1, stockID);
					ps.setString(2, poitem.getProductCode());
					ps.setString(3, poitem.getPOID());
					ps.setInt(4, poitem.getNum());
					ps.setInt(5, 1);
					ps.setString(6, dateStr);
					ps.setString(7, name);
					ps.executeUpdate();
				}
				//pitemd.delPoitems(poIDs);
				//pmaind.delPomains(poIDs);
				pmaind.updatePomains(poIDs,name,dateStr);
				
				conn.commit();
			} catch (NumberFormatException e) {
				try {
					conn.rollback();//打回
					conn.setAutoCommit(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				e.printStackTrace();
				
			} catch (SQLException e) {
				
				try {
					conn.rollback();//打回
					conn.setAutoCommit(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				
			}finally {
				
				dc.close();
			}
			
		}
		return flag;
	}
	
	//取得入库月份(从采购单信息中)
	public List<String> getMonth(){
		List<String> months=new ArrayList<String>();
		stat=dc.getStat();
		//入库stocktype 为1
		String sql="select StockTime from stockrecord where StockType=1";
		try {
			rs=stat.executeQuery(sql);
			while(rs.next()){
				String month=rs.getString("stockTime").substring(0, 7);
				if(months.contains(month))
					continue;
				else
					months.add(month);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dc.close();
		}
		return months;
	}
	 
	public StockRecordMain getRecordMain(String month){
		StockRecordMain recordMain=null;
		int orderNum=0; //订单数量
		int totalNum=0; //总入库数量
		float totalPrice=0; //总价
		stat=dc.getStat();
		
		String sql="select count(*) from stockrecord where stockTime like '"+month+"%'";
		try {
			rs=stat.executeQuery(sql);
			if(rs.next()){
				orderNum=rs.getInt(1);
			}
			//库存数量  产品价格
			sql="select stockrecord.stockNum,product.price  from stockrecord,product where stockrecord.productCode=product.productCode and stockrecord.stockTime like '"+month+"%'";
			rs=stat.executeQuery(sql);
			//入库产品的总价和总数
			while(rs.next()){
				float price=rs.getInt("stockNum")*rs.getFloat("price");
				totalPrice+=price;
				totalNum+=rs.getInt("stockNum");
			}
			recordMain=new StockRecordMain(orderNum, totalNum, totalPrice);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dc.close();
		}
		return recordMain;
	}
	
	
	//product 产品名字 单价 
	public List<StockRecordDetail> getRecordDetails(String month){
		List<StockRecordDetail> recordDetails=new ArrayList<StockRecordDetail>();
		StockRecordDetail recordDetail=null;
		float itemPrice=0;
		stat=dc.getStat();
		String sql="select stockrecord.*,product.name,product.price FROM stockrecord,product where stockrecord.ProductCode=product.productcode and stockrecord.StockTime like '"+month+"%'";
		try {
			rs=stat.executeQuery(sql);
			while(rs.next()){
				itemPrice=rs.getInt("StockNum")*rs.getFloat("price");
				recordDetail=new StockRecordDetail(rs.getString("orderCode"), rs.getString("stockTime"), rs.getString("productCode"), rs.getString("name"), rs.getInt("stockNum"), itemPrice);
				recordDetails.add(recordDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dc.close();
		}
		return recordDetails;
		
	}
	
}
