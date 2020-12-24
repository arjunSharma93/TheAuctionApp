package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Types;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;

public class CreateRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Product p = (Product) request.getAttribute("selectedProduct");
		String pid = (String) request.getAttribute("selectedProductId");
		/*
		 * String upwd = (String) request.getAttribute("userPassword"); String amnt =
		 * (String) request.getAttribute("bidding amount");
		 */
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/namedisplay?useTimezone=true&serverTimezone=UTC","root","");
			PreparedStatement ps = con.prepareStatement("insert into biddingtab values (?, ?, ?, ?, ?, ?)");
			ps.setString(1, p.getProductid());
			ps.setString(2, p.getProductName());
			ps.setString(3, p.getProductDescription());
			ps.setString(4, p.getMinimumBid());
			ps.setString(5, "");
			ps.setString(6, "");
			int i = ps.executeUpdate();
			if(i != 0) {
				PreparedStatement ps2 = con.prepareStatement("DELETE FROM producttab WHERE id ='"+p.getProductid()+"'");
				int j = ps2.executeUpdate();
				if(j != 0) {
					RequestDispatcher rd = request.getRequestDispatcher("/roomservlet");
					rd.forward(request, response);
					con.close();
				}
				else {
					response.getWriter().println("<html><body>");  
					response.getWriter().println("<a href= "+"registration.html>"+"Something went worng, try again</a>");  
					response.getWriter().println("</body></html>");
					con.close();
				}
				
			}
			else {
				response.getWriter().println("<html><body>");  
				response.getWriter().println("<a href= "+"registration.html>"+"Something went worng, try again</a>");  
				response.getWriter().println("</body></html>");
				con.close();
			}
		}catch(Exception e) {
			response.getWriter().println(e.getMessage());
			e.printStackTrace();
		}
	}

}
