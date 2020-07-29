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
import com.wall675.model.Soitem;
import com.wall675.model.StockRecordDetail;
import com.wall675.model.StockRecordMain;

//未建表  月度出库报表
public class StockOutInfoDao {
	DbUtil dc=new DbUtil();
	Connection conn = null;
	Statement stat=null;
	ResultSet rs = null;
	PreparedStatement ps=null;
	
	//判断是否能出库
	public boolean stockOut(String[] soIDs,String name){
		conn=dc.getCon();
		stat=dc.getStat();
		SoitemDao sitemd=new SoitemDao();
		SomainDao smaind=new SomainDao();
		Soitem soitem=null;
		List<Soitem> soitems=new ArrayList<Soitem>();
		StockDao sd=new StockDao();
		boolean flag=false;
		int stockID=0;
		String sql="";
		//String stockTime = DateFormat.getDateInstance().format(new Date());
		 SimpleDateFormat stockTime=new SimpleDateFormat("yyyy-MM-dd");
		 Date date = new Date();//创建一个date对象保存当前时间
		 String dateStr = stockTime.format(date);
		for (int i = 0; i < soIDs.length; i++) {
			soitem = sitemd.getSoitem(soIDs[i]);
			soitems.add(soitem);
		}
		
		flag = sd.removeFromStock(soitems);
		if (flag) {
			try {
				conn.setAutoCommit(false);
				for (int i = 0; i < soIDs.length; i++) {
					
					sql = "insert into stockrecord(StockID,ProductCode,OrderCode,StockNum,StockType,StockTime,CreateUser) value(?,?,?,?,?,?,?)";
					soitem = sitemd.getSoitem(soIDs[i]);
					ps = dc.getPs(sql);
					ps.setInt(1, stockID);
					ps.setString(2, soitem.getProductCode());
					ps.setString(3, soitem.getSoID());
					ps.setInt(4, soitem.getNum());
					//销售出库，标记为0
					ps.setInt(5, 0);
					ps.setString(6, dateStr);
					ps.setString(7, name);
					ps.executeUpdate();
				}
//				sitemd.delSoitems(soIDs);
//				smaind.delSomains(soIDs);
				smaind.updateSomains(soIDs, name, dateStr);
				conn.commit();
			}  catch (NumberFormatException e) {
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
    //出库的月份
	public List<String> getMonth() {
		List<String> months=new ArrayList<String>();
		stat=dc.getStat();
		//入库stocktype 为0
				String sql="select StockTime from stockrecord where StockType=0";
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

	//主信息 简略  
	public StockRecordMain getRecordMain(String month) {
		StockRecordMain recordMain=null;
		int orderNum=0;
		int totalNum=0;
		float totalPrice=0;
		stat=dc.getStat();
		//取出
		String sql="select count(*) from stockrecord where stockTime like '"+month+"%'";
		try {
			rs=stat.executeQuery(sql);
			if(rs.next()){
				orderNum=rs.getInt(1);
			}
			sql="select stockrecord.stockNum,product.price  from stockrecord,product where stockrecord.productCode=product.productCode and stockrecord.stockTime like '"+month+"%'";
			rs=stat.executeQuery(sql);
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
	
   //明细 详细
	public List<StockRecordDetail> getRecordDetails(String month) {
		List<StockRecordDetail> recordDetails=new ArrayList<StockRecordDetail>();
		StockRecordDetail recordDetail=null;
		float itemPrice=0;
		stat=dc.getStat();
		String sql="select stockrecord.*,product.name,product.price from stockrecord,product where stockrecord.ProductCode=product.productcode and stockrecord.StockTime like '"+month+"%'";
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
