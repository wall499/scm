package com.wall675.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wall675.util.DbUtil;
import com.wall675.model.Pomain;
//采购单主信息
public class PomainDao {
	DbUtil dc = new DbUtil();
	Statement stat = null;
	ResultSet rs = null;

	public int getPomainCount(String payType) {
		if(payType==null||payType.equals("all")||payType.equals(""))
			return dc.getRowCount("pomain");
		else
			return dc.getRowCount("pomain where payType="+payType);
	}

	public List<Pomain> selectPomain(int pageNow,String payType) {
		List<Pomain> pomains = new ArrayList<Pomain>();
		stat=dc.getStat();
		String sql="";
		if(payType==null||payType.equals("all")||payType.equals(""))
			sql = "select * from pomain limit " + (pageNow - 1) * 5 + ",5";
		else
			sql="select * from pomain where payType='"+payType+"' limit "+(pageNow - 1) * 5 + ",5";
		try {
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Pomain pomain = new Pomain(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getFloat(5),
						rs.getFloat(6), rs.getFloat(7), rs.getString(8),
						rs.getFloat(9), rs.getInt(10), rs.getString(11),
						rs.getString(12), rs.getString(13), rs.getString(14),
						rs.getString(15), rs.getString(16), rs.getString(17),
						rs.getString(18), rs.getString(19));
				pomains.add(pomain);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pomains;
	}
	
	public void delPomains(String[] poIDs){
		String sql="";
		for(int i=0;i<poIDs.length;i++){
			sql="delete from pomain where POID='"+poIDs[i]+"'";
			dc.update(sql);
		}
	}
	
	public void delPomain(String pOID){
		String sql="delete from pomain where POID='"+pOID+"'";
		dc.update(sql);
	}
	
	//入库：处理状态更改为已入库，并修改入库时间，入库用户
	public void updatePomains(String[] poIDs,String name,String dateStr) {
		String sql="";
		for(int i=0;i<poIDs.length;i++){
			sql="update pomain set status=3,stocktime='"+dateStr+"',stockuser='"+name+"' where POID='"+poIDs[i]+"'";
			dc.update(sql);
		}
	}

}
