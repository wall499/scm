package com.wall675.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wall675.dao.ProductDao;

public class ProductDelete extends HttpServlet {

	private static final long serialVersionUID = -7968904264076595958L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productCode=request.getParameter("productCode");
		System.out.println("要删除的产品编号:"+productCode);
		ProductDao pd=new ProductDao();
		boolean is=pd.delProduct(productCode);
		if(is){
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("<script type='text/javascript'>");
			out.print("alert('删除产品成功');");
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
			out.print("alert('该产品已经购买、销售或者有存库数不能删除');");
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
