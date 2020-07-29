package com.wall675.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wall675.dao.ProductDao;

public class StockReportServlet extends HttpServlet {

	private static final long serialVersionUID = -5653662940788229780L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDao pd=new ProductDao();
		List<String> months=pd.getMonth();
		request.setAttribute("months", months);
		request.getRequestDispatcher("/stockreportsearch.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
