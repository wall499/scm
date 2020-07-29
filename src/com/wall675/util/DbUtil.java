package com.wall675.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {
	private Connection conn;
	private Statement stat;
	private ResultSet rs;
	private PreparedStatement ps;
	private String url="jdbc:mysql://localhost:3306/scm";
	private String user="root";
	private String password="wanli499";
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized Connection getCon(){
		try {
			conn=DriverManager.getConnection(url, user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public Statement getStat(){
		getCon();
		try {
			stat=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stat;
	}
	
	public PreparedStatement getPs(String sql){
		try{
			conn=getCon();
			ps=conn.prepareStatement(sql);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return ps;
	}
	
	public int getRowCount(String tableName){
		int count=0;
		stat=getStat();
		try {
			rs=stat.executeQuery("select count(*) from "+tableName);
			if(rs.next()){
				//获取第一列的值
				count=rs.getInt(1);
			}else{
				count=-1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return count;
	}
	
	public void close(){
		try {
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(ps!=null){
				ps.close();
				ps=null;
			}
			if(stat!=null){
				stat.close();
				stat=null;
			}
			if(conn!=null){
				conn.close();
				conn=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//返回受影响的行数
	public int update(String sql){
		int k=0;
		try {
			k=getStat().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return k;
	}
	
	public Date StrConvertDate(String strdate)
	{		
		Date convertdate=null;
		try{     
			convertdate= Date.valueOf(strdate);
	        System.out.print(convertdate.toString());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	    return convertdate;
	}
}
