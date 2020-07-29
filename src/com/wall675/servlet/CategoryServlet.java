package com.wall675.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wall675.dao.CategoryDao;
import com.wall675.model.Category;


public class CategoryServlet extends HttpServlet {

	private static final long serialVersionUID = 8616395329607258038L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDao cd=new CategoryDao();
		int categoryCount = cd.getCategoryCount();
		//判断一共有几页，每页5项
		int	pageCount=categoryCount%5==0?categoryCount/5:categoryCount/5+1;
		request.setAttribute("pageCount", pageCount);
		int pageNow=0;
		if(request.getParameter("pageNow")!=null){
			pageNow=Integer.parseInt(request.getParameter("pageNow"));
			if(pageNow<=0){
				pageNow=1;
			}else if(pageNow>pageCount){
				pageNow=pageCount;
			}
		}else{
			pageNow=1;
		}
		request.setAttribute("pageNow", pageNow);
		//每一页显示的产品类
		List<Category> categories=cd.selectCategory(pageNow);
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/category.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
