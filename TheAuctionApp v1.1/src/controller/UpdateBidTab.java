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

public class UpdateBidTab extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userEmail = (String) request.getParameter("userEmail");
		String amount = (String) request.getParameter("amount");
		String productId = (String) request.getParameter("productId");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/namedisplay?useTimezone=true&serverTimezone=UTC","root","");
			PreparedStatement ps2 = con.prepareStatement("SELECT * FROM biddingtab WHERE productId ='"+productId+"'");
			ResultSet rs = ps2.executeQuery();
			rs.next();
			PreparedStatement ps = con.prepareStatement("INSERT INTO biddingtab VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, rs.getString("productId"));
			ps.setString(2, rs.getString("productName"));
			ps.setString(3, rs.getString("productDescription"));
			ps.setString(4, rs.getString("minimumBid"));
			ps.setString(5, userEmail);
			ps.setString(6, amount);
			int i = ps.executeUpdate();
			if(i != 0 ) {
				RequestDispatcher rd = request.getRequestDispatcher("/roomservlet");
				rd.forward(request, response);
			}
		}catch(Exception e) {
			response.getWriter().println(e.getMessage());
			e.printStackTrace();
		}
	}

}
