package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		int rowCount = 0;
		int loopCount = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/namedisplay?useTimezone=true&serverTimezone=UTC","root","");
			PreparedStatement ps = con.prepareStatement("SELECT COUNT(name) FROM tab");
			ResultSet rs = ps.executeQuery();
			rs.next();
			rowCount = rs.getInt(1);
			ps = con.prepareStatement("select * from tab");
			rs = ps.executeQuery();
			while(rs.next()) {
				if(email.equals(rs.getString("email")) && password.equals(rs.getString("password"))) {
					
					HttpSession session = request.getSession();
					session.setAttribute("email", email);
					session.setAttribute("password", password);
					session.setAttribute("name", rs.getString("name"));
					response.sendRedirect("home.jsp");
					break;
				}
				else {
					loopCount += 1;
				}
			}
			if(loopCount == rowCount) {
				response.getWriter().println("<html><body>");  
				response.getWriter().println("Email or Unique Code wrong, <a href = "+"login.html>"+"Try again</a>");  
				response.getWriter().println("</body></html>");
			}
			con.close();
		}catch(Exception e) {
			response.getWriter().println(e.getMessage());
			e.printStackTrace();
		}
	
	
	}

}
