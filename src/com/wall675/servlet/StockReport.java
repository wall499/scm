package com.wall675.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wall675.dao.ProductDao;
import com.wall675.model.Product;
public class StockReport extends HttpServlet {

	private static final long serialVersionUID = -2238145360585060397L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String month=request.getParameter("month");
		request.setAttribute("month", month);
		ProductDao pd=new ProductDao();
		List<Product> products=pd.productStock(month);
		request.setAttribute("products", products);
		request.getRequestDispatcher("/productinstock.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
