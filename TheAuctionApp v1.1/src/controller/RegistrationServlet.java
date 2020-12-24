package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;


public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 /* String name = request.getParameter("name");
		  String email = request.getParameter("email"); 
		  String mobile = request.getParameter("mobile"); 
		  String password = request.getParameter("password");
		  String role = request.getParameter("role");*/
		
		User user = (User) request.getAttribute("user");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/namedisplay?useTimezone=true&serverTimezone=UTC","root","");
			PreparedStatement ps =con.prepareStatement("INSERT INTO tab VALUES(?, ?, ?, ?, ?)");
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getMobile());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getRole());
			int i = ps.executeUpdate();
			if(i!=0) {
				HttpSession session = request.getSession();
				session.setAttribute("email", user.getEmail());
				session.setAttribute("password", user.getPassword());
				session.setAttribute("name", user.getName());
				session.setAttribute("role", user.getRole());
				response.sendRedirect("home.jsp");
				con.close();
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
