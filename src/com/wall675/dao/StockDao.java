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
import com.wall675.model.Poitem;
import com.wall675.model.Product;
import com.wall675.model.Soitem;

//入库登记   stockinfo 表 全部包含在product表中
public class StockDao {
	DbUtil dc = new DbUtil();
	Connection conn=null;
	Statement stat = null;
	ResultSet rs = null;
	PreparedStatement ps=null;
	
	public int getStockCount(String condition,String keywords,String min,String max){
		if(condition==null||condition.equals("")){
			return dc.getRowCount("product");
		}else if(condition.equals("productName")){
			return dc.getRowCount("product where name like '%"+keywords+"%'");
		}else if(condition.equals("productCode")){
			return dc.getRowCount("product where ProductCode like '%"+keywords+"%'");
		}else if(condition.equals("range")){
			if(max.equals("infinity"))
				return dc.getRowCount("product where num >"+min);
			else
				return dc.getRowCount("product where num between "+min+" and "+max);
		}
		return -1;
	}
	//产品入库
	public boolean addToStock(List<Poitem> poitems){
		stat=dc.getStat();
		String sql="";
		Poitem poitem=null;
		boolean flag=true;
		try {
			
			for(int i=0;i<poitems.size();i++){
				poitem=poitems.get(i);
				System.out.println("poitems    "+poitem);
				//判断此产品编号是否存在于product产品表中
				boolean is=isContain(poitem.getProductCode());
				if(is){
					//通过产品编号查询产品信息
					Product stock=getStockInfo(poitem.getProductCode());
					//采购在途数目(poitem表)+产品库存数目(product表)
					int num=stock.getNum()+poitem.getNum();
					
					//总的采购在途数目(product表)-已入库的采购在途数目(poitem表)
					int poNum=stock.getPoNum()-poitem.getNum();
					System.out.println("poitem.getNum   "+poitem.getNum()+"   stocknum  "+stock.getNum()+"   num  "+num+" ponum "+poNum);
					System.out.println("stock.getPoNum()   "+stock.getPoNum()+"   poitem.getNum()  "+poitem.getNum());
					//product产品表中的存库数修改,即入库成功
					sql="update product set Num="+num+",PONum="+poNum+" where ProductCode="+poitem.getProductCode()+"";
					dc.update(sql);
				}else{
					//一条库存表的信息
					Product product=new ProductDao().getProduct(poitem.getProductCode());
					System.out.println("product    "+product);
					//在product产品表中插入一条新信息
					sql="insert into product values(?,?,?,?,?,?,?,?,?,?)";
					ps=dc.getPs(sql);
					ps.setString(1,poitem.getProductCode());
					ps.setInt(2, product.getCategoryID());
					ps.setString(3, product.getName());
					ps.setString(4, poitem.getUnitName());
					ps.setFloat(5, product.getPrice());
					ps.setString(6, product.getCreateDate());
					ps.setString(7, product.getRemark());
					ps.setInt(8, poitem.getNum());
					ps.setInt(9, product.getPoNum());
					ps.setInt(10, product.getSoNum());
					ps.executeUpdate();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dc.close();
		}
		return flag;
	}
	
	//判断此产品编号是否存在于product产品表中
	public boolean isContain(String productCode){
		stat=dc.getStat();
		String sql="select count(*) from product where ProductCode='" +productCode+"'";
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
	
	//通过产品编号从product(产品表)查询产品信息
	public Product getStockInfo(String productCode){
		Product stock=null;
		stat=dc.getStat();
		String sql="select * from product where ProductCode='"+productCode+"'";
		try {
			rs=stat.executeQuery(sql);
			if(rs.next()){
				stock=new Product();
				stock.setProductCode(rs.getString(1));
				stock.setName(rs.getString(3));
				stock.setUnitName(rs.getString(4));
				stock.setNum(rs.getInt(8));
				stock.setPoNum(rs.getInt(9)); 
				stock.setSoNum(rs.getInt(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dc.close();
		}
		return stock;
	}

	//产品库存数是否大于要销售的数量 出库
	public boolean removeFromStock(List<Soitem> soitems) {
		stat=dc.getStat();
		String sql="";
		//销售单明细
		Soitem soitem=null;
		boolean flag=true;
		try {
			
			for(int i=0;i<soitems.size();i++){
				soitem=soitems.get(i);
				if(soitem==null) {
					JOptionPane.showMessageDialog(null, "错误", "缺少销售单明细,出现系统错误", JOptionPane.ERROR_MESSAGE);
					System.out.println("缺少销售单明细");
					return false;
				}else {
					//判断此产品编号是否存在于product产品表中
					boolean is=isContain(soitem.getProductCode());
					if(is){
						//通过产品编号查询产品信息
						Product stock=getStockInfo(soitem.getProductCode());
						int num=stock.getNum()-soitem.getNum();
						
						//预售销售相应减少
						int Sonum=stock.getSoNum()-soitem.getNum();
						//如果产品库存数大于或等于要出售的数目，则库存数量等于库存数-销售的数目
						if(num>=0){
							sql="update product set num="+num+",sonum="+Sonum+" where ProductCode='"+soitem.getProductCode()+"'";
							System.out.println(sql);
							dc.update(sql);
						}else
						{
							JOptionPane.showMessageDialog(null, "错误", "库存不足", JOptionPane.ERROR_MESSAGE);
							return false;
						}
						
						
					}else{
						
						flag=false;
					}
				}
				
			}
			
		}finally{
			dc.close();
		}
		return flag;
	}

	//库存查询  
	public List<Product> selectStockInfos(int pageNow,String condition,String keywords,String min,String max) {
		stat=dc.getStat();
		List<Product> stockInfos=null;
		String sql="";
		if(condition==null||condition.equals("")){
			sql="select * from product limit "+(pageNow-1)*5+",5";
			stockInfos=addToList(sql);
		}else if(condition.equals("productCode")){
			//根据产品编号查询
			sql="select * from product where productCode like '%"+keywords+"%' limit "+(pageNow-1)*5+",5";
			stockInfos=addToList(sql);
		}else if(condition.equals("productName")){
			//根据产品名称查询
			sql="select * from product where name like '%"+keywords+"%' limit "+(pageNow-1)*5+",5";
			stockInfos=addToList(sql);
		}else if(condition.equals("range")){
			//根据库存数量范围查询
			if(max.equals("infinity"))
				sql="select * from product where num >"+min;
			else
				sql="select * from product where num between "+min+" and "+max;
			stockInfos=addToList(sql);
		}
		return stockInfos;
	}
	
	public List<Product> addToList(String sql){
		stat=dc.getStat();
		List<Product> stockInfos=new ArrayList<Product>();
		Product stockInfo=null;
		String productCode=null;
		SoitemDao sd=new SoitemDao();
		PoitemDao pd=new PoitemDao();
		try {
			rs=stat.executeQuery(sql);
			while(rs.next()){
				productCode=rs.getString("productCode");
				int poNum=pd.getPoNum(productCode);
				int soNum=sd.getSoNum(productCode);
				stockInfo=new Product(rs.getString(1), rs.getString(3), rs.getString(4), rs.getInt(8), poNum, soNum);
				stockInfos.add(stockInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stockInfos;
	}
}
