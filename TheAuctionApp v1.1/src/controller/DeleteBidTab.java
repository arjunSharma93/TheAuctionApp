package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;

public class DeleteBidTab extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userEmail = request.getParameter("userEmail");
		String productId = (String) request.getParameter("productId");
		String amount = request.getParameter("amount");
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/namedisplay?useTimezone=true&serverTimezone=UTC","root","");
			PreparedStatement ps3 = con.prepareStatement("SELECT * FROM biddingtab WHERE productId='"+productId+"'");
			ResultSet rs = ps3.executeQuery();
			rs.next();
			PreparedStatement ps = con.prepareStatement("DELETE FROM biddingtab WHERE productId='"+productId+"'");
			int i = ps.executeUpdate();
			if(i==0) {
				response.getWriter().println("<html><body>");  
				response.getWriter().println("<a href= "+"registration.html>"+"Something went worng, try again</a>");  
				response.getWriter().println("</body></html>");
				con.close();
			}
			
			PreparedStatement ps2 = con.prepareStatement("INSERT INTO soldout VALUES (?, ?, ?, ?, ?, ?)");
			ps2.setString(1, rs.getString("productId"));
			ps2.setString(2, rs.getString("productName"));
			ps2.setString(3, rs.getString("productDescription"));
			ps2.setString(4, rs.getString("minimumBid"));
			ps2.setString(5, userEmail);
			ps2.setString(6, amount);
			int k = ps2.executeUpdate();
			if(k != 0) {
				RequestDispatcher rd = request.getRequestDispatcher("/roomservlet");
				rd.forward(request, response);
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
