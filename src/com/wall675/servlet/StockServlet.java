package com.wall675.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wall675.dao.StockDao;
import com.wall675.model.Product;

//库存查询
public class StockServlet extends HttpServlet {

	private static final long serialVersionUID = 7663923918435884013L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String condition=request.getParameter("condition");
		String keywords=request.getParameter("keywords");
		String min=request.getParameter("min");
		String max=request.getParameter("max");
//System.out.println("condition:"+condition+"  keywords:"+keywords+"  min:"+min+"  max="+max);
		StockDao sd=new StockDao();
		int stockCount=sd.getStockCount(condition,keywords,min,max);
		int pageCount=stockCount%5==0?stockCount/5:stockCount/5+1;
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
		List<Product> stockInfos=sd.selectStockInfos(pageNow,condition,keywords,min,max);
		request.setAttribute("stockInfos", stockInfos);
		request.getRequestDispatcher("/stocksearch.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
