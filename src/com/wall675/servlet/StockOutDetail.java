package com.wall675.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wall675.dao.SoitemDao;
import com.wall675.model.Soitem;

public class StockOutDetail extends HttpServlet {

	private static final long serialVersionUID = -663834904116533855L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String strSOID=request.getParameter("strSOID");
		String[] soIDs=strSOID.split(",");
		List<Soitem> soitems = new ArrayList<Soitem>();
		SoitemDao sd = new SoitemDao();
		for(int i=0;i<soIDs.length;i++){
			Soitem soitem=sd.getSoitem(soIDs[i]);
			soitems.add(soitem);
		}
		request.setAttribute("soitems", soitems);
		request.getRequestDispatcher("/soitemdetail.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
