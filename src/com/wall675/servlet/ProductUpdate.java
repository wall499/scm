package com.wall675.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wall675.dao.CategoryDao;
import com.wall675.dao.ProductDao;
import com.wall675.model.Category;
import com.wall675.model.Product;

public class ProductUpdate extends HttpServlet {
	
	private static final long serialVersionUID = 176727114895474072L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String proMess=request.getParameter("proMess");
		ProductDao pd=new ProductDao();
		if(proMess==null) {
			//判断一共有几页，每页5项
		      int productCount = pd.getProductCount();
				int	pageCount=productCount%5==0?productCount/5:productCount/5+1;
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
				//每一页显示的产品类
				List<Product> products=pd.selectProduct(pageNow);
				request.setAttribute("products", products);
				request.getRequestDispatcher("/product.jsp").forward(request, response);
		}else {
			String[] message=proMess.split(",");
			for(int j=0;j<message.length;j++) {
				System.out.println(j+"message   "+"   i   "+message[j]);
			}
			request.setAttribute("message", message);
			//categoryNames
			List<String> categoryNames=pd.getCategoryName();
			System.out.println("ddddd");
			for(int j=0;j<categoryNames.size();j++) {
				System.out.println(j+"categoryNames   "+"   我是   "+categoryNames.get(j));
			}
			request.setAttribute("categoryNames", categoryNames);
			request.getRequestDispatcher("/th_detail.jsp").forward(request, response);
		}

		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
