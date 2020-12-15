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

public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		System.out.println("admin login servlet invoke");
	}
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminName = request.getParameter("adminName");
		String adminPassword = request.getParameter("adminPassword");
		int rowCount = 0;
		int loopCount = 0;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/namedisplay?useTimezone=true&serverTimezone=UTC","root","");
			PreparedStatement ps = con.prepareStatement("SELECT COUNT(adminname) FROM admintab");
			ResultSet rs = ps.executeQuery();
			rs.next();
			rowCount = rs.getInt(1);
			ps = con.prepareStatement("select * from admintab");
			rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString("adminname").equals(adminName) && rs.getString("adminpassword").equals(adminPassword)) {
					
					HttpSession session = request.getSession();
					session.setAttribute("adminName", adminName);
					session.setAttribute("adminPassword", adminPassword);
					response.sendRedirect("adminHome.jsp");
					break;
				}
				else {
					loopCount += 1;
				}
			}
			if(loopCount == rowCount) {
				response.getWriter().println("<html><body>");  
				response.getWriter().println("Email or Unique Code wrong, <a href = "+"adminLogin.html>"+"Try again</a>");  
				response.getWriter().println("</body></html>");
			}
			
			
			
		}catch(Exception e) {
			response.getWriter().println(e.getMessage());
			e.printStackTrace();
		}
	}

}
