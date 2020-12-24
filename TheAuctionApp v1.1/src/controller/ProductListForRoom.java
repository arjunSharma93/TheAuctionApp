package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;

public class ProductListForRoom extends ViewItemServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Product>productList = new ArrayList<Product>();
		dataRenderingFromDatabase(request, response, productList);
		request.setAttribute("productList", productList);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int i=0;
		String id = request.getParameter("productId");
		List<Product>productList = new ArrayList<Product>();
		dataRenderingFromDatabase(request, response, productList);
		
		for(i=0; i<productList.size(); i++) {
			Product p = productList.get(i);
			if(p.getProductid().equals(id)) {
				request.setAttribute("selectedProduct", p);
				request.setAttribute("selectedProductId", p.getProductid());
				RequestDispatcher rd = request.getRequestDispatcher("/createroom");
				rd.forward(request, response);
				break;
			}
		}
		if(productList.size() == i) {
			response.sendRedirect("roominfo.jsp");
		}
	}

}
