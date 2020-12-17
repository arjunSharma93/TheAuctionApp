package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;

public class ViewItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void dataRenderingFromDatabase(HttpServletRequest request, HttpServletResponse response, List<Product>productList) throws ServletException, IOException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/namedisplay?useTimezone=true&serverTimezone=UTC","root","");
			PreparedStatement ps = con.prepareStatement("select * from producttab");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				
				product.setProductName(rs.getString("productName"));
				product.setProductDescription(rs.getString("productDescription"));
				product.setMinimumBid(rs.getString("minimumBid"));
				product.setProductid(Integer.parseInt(rs.getString(1)));
				
				productList.add(product);
			}
		} catch (Exception e) {
			response.getWriter().println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		List<Product>productList = new ArrayList<Product>();
		
		dataRenderingFromDatabase(request, response, productList);
		request.setAttribute("productList", productList);
		
		if(session.getAttribute("adminName")!=null && session.getAttribute("adminPassword")!=null) {
			RequestDispatcher rd = request.getRequestDispatcher("/adminViewItem.jsp");
			rd.forward(request, response);
		}
		else if(session.getAttribute("email")!=null||session.getAttribute("password")!=null) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/viewItem.jsp");
			rd.forward(request, response);
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		List<Product>productList = new ArrayList<Product>();
		
		dataRenderingFromDatabase(request, response, productList);
		request.setAttribute("productList", productList);
		
		if(session.getAttribute("adminName")!=null||session.getAttribute("adminPassword")!=null) {
			RequestDispatcher rd = request.getRequestDispatcher("/adminViewItem.jsp");
			rd.forward(request, response);
		}
		
		
		
	}

}
