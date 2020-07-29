package com.wall675.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wall675.util.DbUtil;
import com.wall675.model.Category;

public class CategoryDao {
	DbUtil dc = new DbUtil();
	Statement stat=null;
	ResultSet rs=null;
	PreparedStatement ps=null;
	
	//有几类产品
	public int getCategoryCount(){
		return dc.getRowCount("category");
	}
	
	//删除一类
	public int delCategory(String categoryID){
		String sql="delete from category where categoryID='" +categoryID+"'";
		return dc.update(sql);
	}
	
	//是否有此类
	public boolean isContainCategory(String categoryID){
		stat=dc.getStat();
		String sql="select count(*) from category where categoryid='" +categoryID+"'";
		try {
			rs=stat.executeQuery(sql);
			while(rs.next()){
				if(rs.getInt(1)<=0)
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dc.close();
		}
		return true;
	}
	
	//每一页显示的产品类
	public List<Category> selectCategory(int pageNow) {
		stat=dc.getStat();
		return addToList(pageNow);
	}
	
	//category分页
	public List<Category> addToList(int pageNow){
		List<Category> categories= new ArrayList<Category>();
		String sql="select * from category limit "+(pageNow-1)*5+",5";
		try {
			rs=stat.executeQuery(sql);
			while(rs.next()){
				Category category = new Category(rs.getInt("categoryid"),rs.getString("name"),rs.getString("remark"));
				categories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}
    
	//修改
	public void update(String categoryID, String name, String remark) {
		//String sql="update category set name=?,remark=? where categoryid='"+categoryID+"'";
		String sql="update category set name='"+name+"',remark='"+remark+"' where categoryid='" +categoryID+"'";
		stat=dc.getStat();
		try {
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dc.close();
		}
	}
   
	
	//添加category
	public void addCategory(String categoryID, String name, String remark) {
		String sql="insert into category(categoryid,name,remark) values(?,?,?)";
		ps=dc.getPs(sql);
		try {
			ps.setString(1, categoryID);
			ps.setString(2, name);
			ps.setString(3, remark);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dc.close();
		}
	}
	
}
