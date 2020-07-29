package com.wall675.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.wall675.util.DbUtil;
import com.wall675.model.SCMUser;

public class SCMUserDao {
	DbUtil dc = new DbUtil();
	Statement stat=null;
	ResultSet rs=null;
	
	public SCMUser getUser(String account){
		stat=dc.getStat();
		SCMUser user=new SCMUser();
		String sql="select * from scmuser where account='"+account+"'";
		try {
			rs=stat.executeQuery(sql);
			if(rs.next()){
				user.setAccount(rs.getString("account"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setCreateDate(rs.getString("createDate"));
				user.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dc.close();
		}
		return user;
	}
	
	public boolean isContain(String account,String password){
		stat=dc.getStat();
		String sql="select * from scmuser where account='"+account+"'";
		try {
			rs=stat.executeQuery(sql);
			while(rs.next()){
				String passwd=rs.getString("password");
				if(password!=null&&password.equals(passwd)){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			dc.close();
		}
		return false;
	}
}
