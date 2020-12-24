package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;

public class DetailsBid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productId = request.getParameter("productId");
		ArrayList<String> emailList = new ArrayList<String>();
		ArrayList<String> amountList = new ArrayList<String>();
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/namedisplay?useTimezone=true&serverTimezone=UTC","root","");
			PreparedStatement ps = con.prepareStatement("SELECT * FROM biddingtab WHERE productId='"+productId+"'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				if(rs.getString("userEmail").equals("")) {
					continue;
				}
				else {
					emailList.add(rs.getString("userEmail"));
					amountList.add(rs.getString("amount"));
				}
			}
			if(emailList.size() == 0) {
				/*response.getWriter().println("<html><body>");  
				response.getWriter().println("nobody has bid yet");  
				response.getWriter().println("</body></html>");*/
				request.setAttribute("noDetails", "nobody has bid yet");
				RequestDispatcher rd =request.getRequestDispatcher("detailsbid.jsp");
				rd.forward(request, response);
			}
			else {
				request.setAttribute("emailList", emailList);
				request.setAttribute("amountList", amountList);
				RequestDispatcher rd =request.getRequestDispatcher("detailsbid.jsp");
				rd.forward(request, response);
			}
			
			
			
		}catch(Exception e) {
			response.getWriter().println(e.getMessage());
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
