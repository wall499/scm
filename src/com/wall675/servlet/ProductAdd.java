package com.wall675.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.wall675.dao.ProductDao;

public class ProductAdd extends HttpServlet {

	private static final long serialVersionUID = -3470706476298227227L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type=request.getParameter("type");
		String productCode=request.getParameter("productCode");
		String categoryName=request.getParameter("categoryName");
		String name=request.getParameter("name");
		String unitName=request.getParameter("unitName");
		String price=request.getParameter("price");
		String createDate=request.getParameter("createDate");
		String remark=request.getParameter("remark");
		String num=request.getParameter("num");
		String poNum=request.getParameter("poNum");
		String soNum=request.getParameter("soNum");
		ProductDao pd=new ProductDao();
   System.out.println("type   "+type+"CategoryName"+categoryName);
		boolean flag=false;
		if("add".equals(type)) {
			 String categoryId=pd.nameToId(categoryName);
				flag=pd.addToProduct(productCode,categoryId,name,unitName,price,createDate,remark);
			}
		else {
			     String categoryId=pd.nameToId(categoryName);
			flag=pd.updateProduct(productCode,categoryId,name,unitName,price,createDate,remark,num,poNum,soNum);
		}
			System.out.println("flag"+flag);
		if(flag){
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("<script type='text/javascript'>");
			out.print("alert('更新数据成功');");
			out.print("location.href='/200429SCM_BS/servlet/ProductServlet';");
			out.println("</script>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		}else{
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("<script type='text/javascript'>");
			out.print("alert('更新数据失败');");
			out.print("location.href='/200429SCM_BS/servlet/ProductServlet';");
			out.println("</script>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
