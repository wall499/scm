package com.wall675.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wall675.dao.CategoryDao;
import com.wall675.model.Category;

public class CategoryDelete extends HttpServlet {
	
	private static final long serialVersionUID = -9102290900375003171L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category category=(Category) request.getSession().getAttribute("category");
		int rowCount=0;
		if(category==null){
			String categoryID=request.getParameter("categoryID");
			CategoryDao cd=new CategoryDao();
			rowCount=cd.delCategory(categoryID);
		}
		if(rowCount>0){
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("<script type='text/javascript'>");
			out.print("alert('删除成功');");
			out.print("location.href='/200429SCM_BS/servlet/CategoryServlet';");
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
			out.print("alert('删除失败');");
			out.print("location.href='/200429SCM_BS/servlet/CategoryServlet';");
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
