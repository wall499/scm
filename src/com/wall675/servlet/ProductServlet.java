package com.wall675.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wall675.dao.ProductDao;
import com.wall675.model.Product;

public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 7537532949065087133L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDao pd=new ProductDao();
		List<Product> products=pd.selectProduct();
		request.setAttribute("products", products);
		request.getRequestDispatcher("/servlet/ProductUpdate?pageNow=1").forward(request, response);
		//request.getRequestDispatcher("/product.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
