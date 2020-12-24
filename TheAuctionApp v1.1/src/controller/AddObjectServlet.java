package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;

public class AddObjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Product product = (Product) request.getAttribute("product");
		System.out.println(product);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/namedisplay?useTimezone=true&serverTimezone=UTC","root","");
			PreparedStatement ps = con.prepareStatement("insert into productTab (productName, productDescription, minimumBid) values(?, ?, ?)");
			ps.setString(1, product.getProductName());
			ps.setString(2, product.getProductDescription());
			ps.setString(3, product.getMinimumBid());
			int i = ps.executeUpdate();
			if(i!=0) {
				
				request.setAttribute("acknowledge", "product added successfully");
				RequestDispatcher rd = request.getRequestDispatcher("/viewitem");
				rd.forward(request, response);
				con.close();
			}
			else {
				request.setAttribute("acknowledge", "something wrong, try again");
				RequestDispatcher rd = request.getRequestDispatcher("/viewitem");
				rd.forward(request, response);
				con.close();
			}
			
		}catch(Exception e) {
			response.getWriter().println(e.getMessage());
			e.printStackTrace();
		}
		
	}

}
