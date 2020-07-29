package com.wall675.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wall675.dao.StockRecordDao;
import com.wall675.model.StockRecordInfo;

public class StockMessage extends HttpServlet {

	private static final long serialVersionUID = 4215475754338166813L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String productCode=request.getParameter("productCode");
		request.setAttribute("productCode", productCode);
		StockRecordDao srd=new StockRecordDao();
		int inRecordCount=srd.getInRecordCount(productCode);
		int inPageCount=inRecordCount%5==0?inRecordCount/5:inRecordCount/5+1;
		session.setAttribute("inPageCount",inPageCount);
		int inPageNow=0;
		if(request.getParameter("inPageNow")!=null){
			inPageNow=Integer.parseInt(request.getParameter("inPageNow"));
			if(inPageNow<=0){
				inPageNow=1;
			}else if(inPageNow>inPageCount){
				inPageNow=inPageCount;
			}
		}else{
			inPageNow=1;
		}
		session.setAttribute("inPageNow", inPageNow);
		List<StockRecordInfo> inStockRecords=srd.getInStockRecords(productCode);
		request.setAttribute("inStockRecords", inStockRecords);
		int outRecordCount=srd.getOutRecordCount(productCode);
		int outPageCount=outRecordCount%5==0?outRecordCount/5:outRecordCount/5+1;
		session.setAttribute("outPageCount",outPageCount);
		int outPageNow=0;
		if(request.getParameter("outPageNow")!=null){
			outPageNow=Integer.parseInt(request.getParameter("outPageNow"));
			if(outPageNow<=0){
				outPageNow=1;
			}else if(outPageNow>outPageCount){
				outPageNow=outPageCount;
			}
		}else{
			outPageNow=1;
		}
		session.setAttribute("outPageNow", outPageNow);
		List<StockRecordInfo> outStockRecords=srd.getOutStockRecords(productCode);
		request.setAttribute("outStockRecords", outStockRecords);
		request.getRequestDispatcher("/stockrecord.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
