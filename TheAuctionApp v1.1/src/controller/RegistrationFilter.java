package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import model.User;




public class RegistrationFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter invoke");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		  /*String email = request.getParameter("email");
		  String mobile = request.getParameter("mobile");*/
		
		User user = (User) request.getAttribute("user");
		
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
				if(user.getEmail().equals(rs.getString("email"))||user.getMobile().equals(rs.getString("mobile"))) {
					response.getWriter().println("<html><body>");  
					response.getWriter().println("<a href = "+"index.html>"+"email or mobile already exist, please login</a>");  
					response.getWriter().println("</body></html>");
					break;
				}
				else {
					loopCount +=1;
				}
			}
			if(loopCount == rowCount) {
				chain.doFilter(request, response);
			}
		}catch(Exception e) {
			response.getWriter().println(e.getMessage());
			e.printStackTrace();
		}
		
	}

	@Override
	public void destroy() {
		System.out.println("filter destroy");
	}
}
