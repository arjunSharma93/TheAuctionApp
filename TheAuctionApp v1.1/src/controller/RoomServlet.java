package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import model.User;

public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		ArrayList<Product> productList = new ArrayList<Product>();
		LinkedHashMap<Product, String> productAndMaxAmountMap = new LinkedHashMap<Product, String>();
		LinkedHashMap<Product, String> productAndEmailMap = new LinkedHashMap<Product, String>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/namedisplay?useTimezone=true&serverTimezone=UTC", "root", "");
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT productId FROM biddingtab");
			ResultSet rs = ps.executeQuery();
			if(!rs.isBeforeFirst()) {
				request.setAttribute("msg", "room has not started yet");
			}
			else {
				while(rs.next()){
					Product product = new Product();
					PreparedStatement ps2 = con.prepareStatement("SELECT * FROM biddingtab WHERE productId ='"+rs.getString(1)+"'");
					ResultSet rs2 = ps2.executeQuery();
					rs2.next();
					product.setProductid(rs2.getString("productId"));
					product.setProductName(rs2.getString("productName"));
					product.setProductDescription(rs2.getString("productDescription"));
					product.setMinimumBid(rs2.getString("minimumBid"));
					
					productList.add(product);
					
					ps = con.prepareStatement("SELECT MAX(amount) FROM biddingtab WHERE productId ='"+rs.getString("productid")+"'");
					ResultSet rs3 = ps.executeQuery();
					rs3.next();
					
					productAndMaxAmountMap.put(product, rs3.getString(1));
					
					ps = con.prepareStatement("SELECT userEmail FROM biddingtab WHERE productId ='"+rs.getString("productid")+"' AND amount ='"+rs3.getString(1)+"'");                                     
					ResultSet rs4 = ps.executeQuery();
					rs4.next();
					
					productAndEmailMap.put(product, rs4.getString(1));
				}
			}
			request.setAttribute("productList", productList);
			request.setAttribute("productAndMaxAmountMap", productAndMaxAmountMap);
			request.setAttribute("productAndEmailMap", productAndEmailMap);
			
			if(session.getAttribute("role").equals("admin")) {
				RequestDispatcher rd = request.getRequestDispatcher("/adminRoom.jsp");
				rd.forward(request, response);
			}
			else if(session.getAttribute("role").equals("user")) {
				
				RequestDispatcher rd = request.getRequestDispatcher("/room.jsp");
				rd.forward(request, response);
			}
			else {
				response.sendRedirect("index.html");
			}
			
		}catch(Exception e) {
			response.getWriter().println(e.getMessage());
			e.printStackTrace();
		}
		
	
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		ArrayList<Product> productList = new ArrayList<Product>();
		LinkedHashMap<Product, String> productAndMaxAmountMap = new LinkedHashMap<Product, String>();
		LinkedHashMap<Product, String> productAndEmailMap = new LinkedHashMap<Product, String>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/namedisplay?useTimezone=true&serverTimezone=UTC", "root", "");
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT productId FROM biddingtab");
			ResultSet rs = ps.executeQuery();
			if(!rs.isBeforeFirst()) {
				request.setAttribute("msg", "room has not started yet");
			}
			else {
				while(rs.next()){
					Product product = new Product();
					PreparedStatement ps2 = con.prepareStatement("SELECT * FROM biddingtab WHERE productId ='"+rs.getString(1)+"'");
					ResultSet rs2 = ps2.executeQuery();
					rs2.next();
					product.setProductid(rs2.getString(1));
					product.setProductName(rs2.getString(2));
					product.setProductDescription(rs2.getString(3));
					product.setMinimumBid(rs2.getString(4));
					
					productList.add(product);
					
					ps = con.prepareStatement("SELECT MAX(amount) FROM biddingtab WHERE productId ='"+rs.getString("productid")+"'");
					ResultSet rs3 = ps.executeQuery();
					rs3.next();
					
					productAndMaxAmountMap.put(product, rs3.getString(1));
					
					ps = con.prepareStatement("SELECT userEmail FROM biddingtab WHERE productId ='"+rs.getString("productid")+"' AND amount ='"+rs3.getString(1)+"'");                                     
					ResultSet rs4 = ps.executeQuery();
					rs4.next();
					
					productAndEmailMap.put(product, rs4.getString(1));
				}
			}
			request.setAttribute("productList", productList);
			request.setAttribute("productAndMaxAmountMap", productAndMaxAmountMap);
			request.setAttribute("productAndEmailMap", productAndEmailMap);
			
			if(session.getAttribute("role").equals("admin")) {
				RequestDispatcher rd = request.getRequestDispatcher("/adminRoom.jsp");
				rd.forward(request, response);
			}
			else if(session.getAttribute("role").equals("user")) {
				
				RequestDispatcher rd = request.getRequestDispatcher("/room.jsp");
				rd.forward(request, response);
			}
			else {
				response.sendRedirect("index.html");
			}
			
			
		}catch(Exception e) {
			response.getWriter().println(e.getMessage());
			e.printStackTrace();
		}
		
	}

}
