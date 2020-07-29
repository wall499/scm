package com.wall675.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wall675.dao.StockOutInfoDao;
import com.wall675.model.SCMUser;

public class StockOutSave extends HttpServlet {

	private static final long serialVersionUID = -3186779605819229692L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SCMUser user=(SCMUser) request.getSession().getAttribute("user");
		String strSOID=request.getParameter("strSOID");
		String[] soIDs=strSOID.split(",");
		StockOutInfoDao soid=new StockOutInfoDao();
		boolean flag=soid.stockOut(soIDs,user.getName());
		if(flag){
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("<script type='text/javascript'>");
			out.print("alert('出库成功');");
			out.print("location.href='/200429SCM_BS/servlet/StockOutServlet';");
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
			out.print("alert('出库失败');");
			out.print("location.href='/200429SCM_BS/servlet/StockOutServlet';");
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
