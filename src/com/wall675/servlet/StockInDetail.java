package com.wall675.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wall675.dao.PoitemDao;
import com.wall675.model.Poitem;

public class StockInDetail extends HttpServlet {

	private static final long serialVersionUID = -499033478168166051L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String strPOID=request.getParameter("strPOID");
		String[] poIDs=strPOID.split(",");
		List<Poitem> poitems = new ArrayList<Poitem>();
		PoitemDao pd = new PoitemDao();
		for(int i=0;i<poIDs.length;i++){
			Poitem poitem=pd.getPoitem(poIDs[i]);
			poitems.add(poitem);
		}
		request.setAttribute("poitems", poitems);
		request.getRequestDispatcher("/poitemdetail.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
