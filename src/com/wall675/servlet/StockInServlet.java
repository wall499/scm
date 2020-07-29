package com.wall675.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wall675.dao.PomainDao;
import com.wall675.model.Pomain;

public class StockInServlet extends HttpServlet {

	private static final long serialVersionUID = 3521502961722200049L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PomainDao pmaind=new PomainDao();
		String payType=request.getParameter("payType");
		int pomainCount =pmaind.getPomainCount(payType);
		request.setAttribute("payType", payType);
		int	pageCount=pomainCount%5==0?pomainCount/5:pomainCount/5+1;
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
		List<Pomain> pomains=pmaind.selectPomain(pageNow,payType);
		request.setAttribute("pomains", pomains);
		request.getRequestDispatcher("/stockin.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
