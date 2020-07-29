package com.wall675.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wall675.dao.SCMUserDao;
import com.wall675.model.SCMUser;

public class LoginCheckServlet extends HttpServlet {

	private static final long serialVersionUID = -8537771279282047626L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String account=request.getParameter("TxtUserName");
		String password=request.getParameter("TxtPassword");
		HttpSession session=request.getSession();
		SCMUserDao sud = new SCMUserDao();
		SCMUser user = sud.getUser(account);
		boolean is=sud.isContain(account,password);
		if(is){
			session.setAttribute("user", user);
			//产品类别
			response.sendRedirect("/200429SCM_BS/servlet/CategoryServlet");
			//入库登记
//			response.sendRedirect("/200429SCM_BS/servlet/StockInServlet"); 
			//出库登记
//			response.sendRedirect("/200429SCM_BS/servlet/StockOutServlet");
			//库存查询
//			response.sendRedirect("/200429SCM_BS/servlet/StockServlet");
			//产品类别
//			response.sendRedirect("/200429SCM_BS/servlet/ProductServlet");
		}else{
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("<script type='text/javascript'>");
			out.print("alert('账号或者密码不正确');");
			out.print("location.href='/200429SCM_BS/login.jsp';");
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
