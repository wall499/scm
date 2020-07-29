package com.wall675.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wall675.util.DbUtil;
import com.wall675.model.Somain;
//销售单主信息
public class SomainDao {
	DbUtil dc = new DbUtil();
	Statement stat = null;
	ResultSet rs = null;
	
	public int getSomainCount(String payType) {
		if(payType==null||payType.equals("all")||payType.equals(""))
		    //返回somain表(销售单主信息)中销售单的数量
			return dc.getRowCount("somain");
		else
			//返回somain表(销售单主信息)中具体某支付方式的销售单数量
			return dc.getRowCount("somain where payType="+payType);
	}
    
	//分页显示，销售在途的产品
	public List<Somain> selectSomain(int pageNow,String payType) {
		List<Somain> somains = new ArrayList<Somain>();
		stat=dc.getStat();
		String sql="";
		if(payType==null||payType.equals("all")||payType.equals(""))
			sql = "select * from somain limit " + (pageNow - 1) * 5 + ",5";
		else
			sql="select * from somain where payType='"+payType+"' limit "+(pageNow - 1) * 5 + ",5";
		try {
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Somain somain = new Somain(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getFloat(5),
						rs.getFloat(6), rs.getFloat(7), rs.getString(8),
						rs.getFloat(9), rs.getInt(10), rs.getString(11),
						rs.getString(12), rs.getString(13), rs.getString(14),
						rs.getString(15), rs.getString(16), rs.getString(17),
						rs.getString(18), rs.getString(19));
				somains.add(somain);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return somains;
	}
	
	public void delSomains(String[] soIDs){
		String sql="";
		for(int i=0;i<soIDs.length;i++){
			sql="delete from somain where SOID='"+soIDs[i]+"'";
			dc.update(sql);
		}
	}
	
	
	//出库：处理状态更改为已出库，并修出库时间，出库用户
		public void updateSomains(String[] soIDs,String name,String dateStr) {
			String sql="";
			for(int i=0;i<soIDs.length;i++){
				sql="update somain set status=3,stocktime='"+dateStr+"',stockuser='"+name+"' where SOID='"+soIDs[i]+"'";
				dc.update(sql);
			}
		}
}
