package com.wall675.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wall675.util.DbUtil;
import com.wall675.model.StockRecordInfo;

//未建表 instockrecord
public class StockRecordDao {
	DbUtil dc=new DbUtil();
	Statement stat = null;
	ResultSet rs = null;
	
	public int getInRecordCount(String productCode){
		return dc.getRowCount("instockrecord where ProductCode='"+productCode+"'");
	}
	
	public int getOutRecordCount(String productCode){
		return dc.getRowCount("outstockrecord where ProductCode='"+productCode+"'");
	}
	
	public List<StockRecordInfo> getInStockRecords(String productCode) {
		List<StockRecordInfo> inStockRecords=new ArrayList<StockRecordInfo>();
		StockRecordInfo stockRecord=null;
		stat=dc.getStat();
		String sql="select * from instockrecord where ProductCode='"+productCode+"'";
		try {
			rs=stat.executeQuery(sql);
			while(rs.next()){
				stockRecord=new StockRecordInfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
				inStockRecords.add(stockRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return inStockRecords;
	}
	
	public List<StockRecordInfo> getOutStockRecords(String productCode) {
		List<StockRecordInfo> outStockRecords=new ArrayList<StockRecordInfo>();
		StockRecordInfo stockRecord=null;
		stat=dc.getStat();
		String sql="select * from outstockrecord where ProductCode='"+productCode+"'";
		try {
			rs=stat.executeQuery(sql);
			while(rs.next()){
				stockRecord=new StockRecordInfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
				outStockRecords.add(stockRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dc.close();
		}
		return outStockRecords;
	}
	
}
