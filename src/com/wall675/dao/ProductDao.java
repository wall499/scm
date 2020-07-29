package com.wall675.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.wall675.util.DbUtil;
import com.wall675.model.Product;

public class ProductDao {
	DbUtil dc = new DbUtil();
	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	//有多少商品
	public int getProductCount(){
		return dc.getRowCount("product");
	}
	
	
	//每一页显示的产品类
		public List<Product> selectProduct(int pageNow) {
			stat=dc.getStat();
			return addToList(pageNow);
		}
	
		public List<Product> addToList(int pageNow){
			List<Product> products= new ArrayList<Product>();
			String sql="select p.Productcode,c.name CategoryName,p.name,p.unitname,p.price,p.createdate,p.remark,p.num,p.PONum,p.SONum,p.categoryid from product p,category c where p.CategoryID=c.CategoryID limit "+(pageNow-1)*5+",5";
			try {
				rs=stat.executeQuery(sql);
				while(rs.next()){
					Product product = new Product(rs.getString("ProductCode"),rs.getString("categoryName"),rs.getString("name"),rs.getString("unitname"),rs.getFloat("price"),rs.getString("createdate"),rs.getString("remark"),rs.getInt("num"),rs.getInt("ponum"),rs.getInt("sonum"),rs.getInt("categoryID"));
					products.add(product);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return products;
		}	
		

  //product表中是否包含此productcode
	public boolean isContain(String productCode){
		stat=dc.getStat();
		String sql="select count(*) from product where productCode='" +productCode+"'";
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
	
	//通过productcode获取对应的product对象
	public Product getProduct(String productCode) {
		Product product = null;
		stat = dc.getStat();
		String sql = "select p.Productcode,c.name CategoryName,p.name,p.unitname,p.price,p.createdate,p.remark,p.num,p.PONum,p.SONum,p.categoryid from product p,category c where p.CategoryID=c.CategoryID and p.ProductCode='" + productCode
				+ "'";
		try {
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				product = new Product(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getFloat(5),
						rs.getString(6), rs.getString(7), rs.getInt(8),
						rs.getInt(9),rs.getInt(10),rs.getInt(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dc.close();
		}
		return product;
	}

	//列出所有product对象
	public List<Product> selectProduct() {
		stat=dc.getStat();
		String sql="select p.Productcode,c.name categoryName,p.name,p.unitname,p.price,p.createdate,p.remark,p.num,p.PONum,p.SONum,p.categoryID from product p,category c where p.CategoryID=c.CategoryID";
		List<Product> products=new ArrayList<Product>();
		Product product=null;
		try {
			rs=stat.executeQuery(sql);
			while(rs.next()){
				product=new Product(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getFloat(5),rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9),rs.getInt(10),rs.getInt(11));
				System.out.println("product   "+product);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dc.close();
		}
		return products;
	}

	//增加一个product对象
	public boolean addToProduct(String productCode, String CategoryID,
			String name, String unitName, String price, String createDate,
			String remark) {
		int num=0;
		int PONum=0;
		int SONum=0;
		
		if(isContain(productCode)){
			JOptionPane.showMessageDialog(null, "错误", "产品编号重复，请重新输入", JOptionPane.ERROR_MESSAGE);
			return false;
		}else{
			stat=dc.getStat();
			
			String sql="insert into product values('"+productCode+"','"+CategoryID+"','"+name+"','"+unitName+"','"+price+"','"+createDate+"','"+remark+"','"+num+"','"+PONum+"','"+SONum+"')";
			try {
				stat.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				dc.close();
			}
			return true;
		}
		
	}

	//删除一个product对象，要保证 num,ponum,sonum 都为0
	public boolean delProduct(String productCode) {
		stat=dc.getStat();
		String sql="select num,poNum,soNum from product where productCode='"+productCode+"'";
		try {
			rs=stat.executeQuery(sql);
			//该产品已经购买、销售或者有存库数不能删除
			if(rs.next()){
				if(rs.getInt("poNum")>0||rs.getInt("soNum")>0||rs.getInt("num")>0){
					return false;
				}
			}
			sql="delete from product where productCode='"+productCode+"'";
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dc.close();
		}
		return true;
	}
 
	//列出所有的产品的添加日期
	public List<String> getMonth() {
		List<String> months=new ArrayList<String>();
		stat=dc.getStat();
		String sql="select createDate from product";
		try {
			rs=stat.executeQuery(sql);
			while(rs.next()){
				String month=rs.getString("createDate").substring(0, 7);
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
	
	
	//列出所有的产品的产品类别categoryID
		public List<String> getCategoryID() {
			List<String> categoryIDs=new ArrayList<String>();
			stat=dc.getStat();
			String sql="select CategoryID from category";
			try {
				rs=stat.executeQuery(sql);
				while(rs.next()){
					String categoryID=rs.getString("CategoryID");
					if(categoryIDs.contains(categoryID))
						continue;
					else
						categoryIDs.add(categoryID);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				dc.close();
			}
			return categoryIDs;
		}
		
		
		//列出所有的product产品表中产品品名
				public List<String> getCategoryName() {
					List<String> categoryNames=new ArrayList<String>();
					stat=dc.getStat();
					String sql="select Name from category";
					try {
						rs=stat.executeQuery(sql);
						while(rs.next()){
							String categoryName=rs.getString("Name");

                 if(categoryNames.contains(categoryName)) 
	                    continue; 
                  else
                	  categoryNames.add(categoryName);

						}
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						dc.close();
					}
					return categoryNames;
				}
				
				//根据categoryName找出相对应的categoryID
				public String nameToId(String categoryName) {
					String categoryID="";
					stat=dc.getStat();
					String sql="select categoryid from category c where c.name='"+categoryName+"'";
					try {
						rs=stat.executeQuery(sql);
						while(rs.next()){
							categoryID=rs.getString("categoryid");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						dc.close();
					}
					return categoryID;
				}		
				
		
	
          // 根据productcode更新product信息
	public boolean updateProduct(String productCode, String categoryId,
			String name, String unitName, String price, String createDate,
			String remark,String num, String poNum, String soNum) {
		System.out.println("categoryID     "+categoryId);
		String sql="update product set categoryID=?,name=?,unitName=?,price=?,createDate=?,remark=?,num=?,poNum=?,soNum=? where productCode='"+productCode+"'";
		ps=dc.getPs(sql);
		try {
			System.out.println("categoryID     "+Integer.parseInt(categoryId));
			ps.setInt(1, Integer.parseInt(categoryId));
			ps.setString(2, name);
			ps.setString(3, unitName);
			ps.setFloat(4,Float.parseFloat(price));
			ps.setString(5, createDate);
			ps.setString(6, remark);
			ps.setInt(7, Integer.parseInt(num));
			ps.setInt(8, Integer.parseInt(poNum));
			ps.setInt(9, Integer.parseInt(soNum));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			dc.close();
		}
		return true;
	}

	//根据生成月份列出涉及到的product项
	public List<Product> productStock(String month) {
		List<Product> products=new ArrayList<Product>();
		Product product=null;
		stat=dc.getStat();
		String sql="select p.Productcode,c.name CategoryName,p.name,p.unitname,p.price,p.createdate,p.remark,p.num,p.PONum,p.SONum,p.categoryID from product p,category c where p.CategoryID=c.CategoryID and CreateDate like '"+month+"%'";
		try {
			rs=stat.executeQuery(sql);
			while(rs.next()){
				product=new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	

}
