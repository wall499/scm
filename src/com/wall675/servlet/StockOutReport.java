package com.wall675.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wall675.dao.StockOutInfoDao;
import com.wall675.model.StockRecordDetail;
import com.wall675.model.StockRecordMain;

public class StockOutReport extends HttpServlet {

	private static final long serialVersionUID = -2151465908277864023L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StockOutInfoDao soid=new StockOutInfoDao();
		String month=request.getParameter("month");
		request.setAttribute("month", month);
		StockRecordMain recordMain=soid.getRecordMain(month);
		request.setAttribute("recordMain", recordMain);
		List<StockRecordDetail> recordDetails=soid.getRecordDetails(month);
		request.setAttribute("recordDetails", recordDetails);
		request.getRequestDispatcher("/stockoutrecord.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
