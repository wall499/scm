package com.wall675.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wall675.dao.StockInInfoDao;
import com.wall675.model.StockRecordDetail;
import com.wall675.model.StockRecordMain;

public class StockInReport extends HttpServlet {

	private static final long serialVersionUID = 1756139959429515466L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StockInInfoDao siid=new StockInInfoDao();
		String month=request.getParameter("month");
		request.setAttribute("month", month);
		StockRecordMain recordMain=siid.getRecordMain(month);
		request.setAttribute("recordMain", recordMain);
		List<StockRecordDetail> recordDetails=siid.getRecordDetails(month);
		request.setAttribute("recordDetails", recordDetails);
		request.getRequestDispatcher("/stockinrecord.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
