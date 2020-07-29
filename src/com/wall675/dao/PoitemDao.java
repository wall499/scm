package com.wall675.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.wall675.util.DbUtil;
import com.wall675.model.Poitem;

public class PoitemDao {
	DbUtil dc=new DbUtil();
	Statement stat=null;
	ResultSet rs=null;
	//通过poid取得poitem详情
	public Poitem getPoitem(String poID){
		String sql="select p.poid,pr.name,p.unitprice,p.num,p.unitname,p.itemprice,p.productcode from poitem p,product pr where p.productcode=pr.productcode and p.POID='"+poID+"'";
		stat=dc.getStat();
		Poitem poitem=null;
		try {
			rs=stat.executeQuery(sql);
			if(rs.next()){
				poitem=new Poitem(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getString(5), rs.getFloat(6), rs.getString(7));
			 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dc.close();
		}
		return poitem;
	}
	
	//删除poitem记录
	public void delPoitems(String[] poIDs){
		String sql="";
		for(int i=0;i<poIDs.length;i++){
			sql="delete from poitem where POID='"+poIDs[i]+"'";
			dc.update(sql);
		}
	}
	
	public void delPoitem(String POID){
		String sql="delete from poitem where POID='"+POID+"'";
		dc.update(sql);
	}
	

	public int getPoNum(String productCode){
		stat=dc.getStat();
		String sql="select num from poitem where productcode='"+productCode+"'";
		int poNum=0;
		try {
			rs=stat.executeQuery(sql);
			while(rs.next()){
				poNum+=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dc.close();
		}
		return poNum;
	}
}
