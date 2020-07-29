package com.wall675.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wall675.dao.StockOutInfoDao;

public class StockOutSearchServlet extends HttpServlet {

	private static final long serialVersionUID = -543869837504500099L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StockOutInfoDao soid=new StockOutInfoDao();
		List<String> months=soid.getMonth();
		request.setAttribute("months", months);
		request.getRequestDispatcher("/stockoutsearch.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
