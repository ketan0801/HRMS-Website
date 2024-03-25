package com.hrms;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class Employee_Login
 */
@WebServlet("/LoginForm")
public class Employee_Login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
	
		String myemail = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Qubit","root","root");
			PreparedStatement ps = con.prepareStatement("select * from emplregis where email = ? and password = ? ");
			ps.setString(1,myemail);
			ps.setString(2,password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				 String userEmail = rs.getString("email"); // Get user's email from the result set
				    HttpSession session = request.getSession(); // Retrieve or create a session
				    session.setAttribute("email", userEmail);
				
				response.setContentType("text/html");
	        	//out.print("<h3 style='color:green'>user Login successfully </h3> ");
	        	RequestDispatcher rd = request.getRequestDispatcher("/EmployeeProfile.jsp");
	        	rd.include(request, response);
			}
			else {
				 
				response.setContentType("text/html");
				//out.print("<h3 style='color:red'>email id and password didn't matched </h3> ");
				RequestDispatcher rd = request.getRequestDispatcher("/EmployeeLogin.jsp");
				RequestDispatcher rd1 = request.getRequestDispatcher("/EmployeeLoginError.jsp");
	        	rd.include(request, response);
	        	rd1.include(request, response);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			response.setContentType("text/html");
        	//out.print("<h3 style='color:red'> Exception occured : "+e.getMessage()+" </h3> ");
			RequestDispatcher rd = request.getRequestDispatcher("/EmployeeLogin.jsp");
			RequestDispatcher rd1 = request.getRequestDispatcher("/EmployeeLoginError.jsp");
        	rd.include(request, response);
        	rd1.include(request, response);
		}
	}

}
