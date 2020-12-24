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

public class RemoveItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productid = request.getParameter("productid");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/namedisplay?useTimezone=true&serverTimezone=UTC","root","");
			PreparedStatement ps = con.prepareStatement("DELETE FROM producttab WHERE id='"+productid+"'");
			int i = ps.executeUpdate();
			if(i != 0) {
				request.setAttribute("acknowledge", "item removed");
				RequestDispatcher rd = request.getRequestDispatcher("/viewitem");
				rd.forward(request, response);
			}
			else {
				request.setAttribute("acknowledge", "something worng");
				RequestDispatcher rd = request.getRequestDispatcher("/viewitem");
				rd.forward(request, response);
			}
		}catch(Exception e) {
			response.getWriter().println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/viewitem");
		rd.forward(request, response);
	}

}
