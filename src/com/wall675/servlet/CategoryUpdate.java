package com.wall675.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.wall675.dao.CategoryDao;

public class CategoryUpdate extends HttpServlet {

	private static final long serialVersionUID = -1256956541697248914L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryID=request.getParameter("categoryID");
		String name=request.getParameter("name");
		String remark=request.getParameter("remark");
		String flag=request.getParameter("flag");
		CategoryDao cd = new CategoryDao();
		//System.out.println(categoryID);
		//category表中含有此category 为true
		boolean is=cd.isContainCategory(categoryID)&&("add".equals(flag));
		System.out.println("is    "+is);
		if(is){
			JOptionPane.showMessageDialog(null, "错误", "表中已经含有此categoryID", JOptionPane.ERROR_MESSAGE);
		}else if("update".equals(flag)) {
		
			cd.update(categoryID,name,remark);
		}else if("add".equals(flag)) {
		
			cd.addCategory(categoryID,name,remark);
		}
		
		response.sendRedirect("/200429SCM_BS/servlet/CategoryServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
