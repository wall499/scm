package com.wall675.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wall675.dao.StockInInfoDao;

//获得入库的月份
public class StockInSearchServlet extends HttpServlet {

	private static final long serialVersionUID = 2605086178362091153L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StockInInfoDao siid=new StockInInfoDao();
		List<String> months=siid.getMonth();
		request.setAttribute("months", months);
		request.getRequestDispatcher("/stockinsearch.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
