package com.wall675.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wall675.dao.SomainDao;
import com.wall675.model.Somain;
//出库登记
public class StockOutServlet extends HttpServlet {

	private static final long serialVersionUID = -4071158718216968667L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String payType=request.getParameter("payType");
		SomainDao smaind=new SomainDao();
		int somainCount =smaind.getSomainCount(payType);
		request.setAttribute("payType", payType);
		int	pageCount=somainCount%5==0?somainCount/5:somainCount/5+1;
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
		
		List<Somain> somains=smaind.selectSomain(pageNow,payType);
		request.setAttribute("somains", somains);
		request.getRequestDispatcher("/stockout.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
