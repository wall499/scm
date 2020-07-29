package com.wall675.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.wall675.util.DbUtil;
import com.wall675.model.Soitem;

public class SoitemDao {
	DbUtil dc = new DbUtil();
	Statement stat = null;
	ResultSet rs = null;
	
	public Soitem getSoitem(String soID) {
		String sql="select p.SOid,pr.name,p.unitprice,p.num,p.unitname,p.itemprice,p.productcode from soitem p,product pr where p.productcode=pr.productcode and p.SOID='"+soID+"'";
		stat=dc.getStat();
		Soitem soitem=null;
		try {
			rs=stat.executeQuery(sql);
			if(rs.next()){
				soitem=new Soitem(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getString(5), rs.getFloat(6), rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dc.close();
		}
		return soitem;
	}
	
	public void delSoitems(String[] soIDs){
		String sql="";
		for(int i=0;i<soIDs.length;i++){
			sql="delete from soitem where SOID='"+soIDs[i]+"'";
			dc.update(sql);
		}
	}
	
	
	public int getSoNum(String productCode){
		stat=dc.getStat();
		String sql="select num from soitem where ProductCode='"+productCode+"'";
		int soNum=0;
		try {
			rs=stat.executeQuery(sql);
			while(rs.next()){
				soNum+=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dc.close();
		}
		return soNum;
	}
}
