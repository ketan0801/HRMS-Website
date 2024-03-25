package com.hrms;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.servlet.annotation.MultipartConfig;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
@WebServlet("/EmployeeRegister")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2,  // 2 MB
	    maxFileSize = 1024 * 1024 * 10,       // 10 MB
	    maxRequestSize = 1024 * 1024 * 50     // 50 MB
	)
public class Employee_Register extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String myname  = request.getParameter("name");
		String myemail = request.getParameter("email");
		String mypassword = request.getParameter("password");
		String myage = request.getParameter("age");
		String mycontact = request.getParameter("contact");
		
	    Part filePart = request.getPart("image");
	    InputStream inputStream = filePart.getInputStream();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Qubit","root","root");
			PreparedStatement ps = con.prepareStatement("INSERT INTO emplregis (name, email, password, age, contact, image) VALUES (?, ?, ?, ?, ?, ?)");
			
			ps.setString(1, myname);
			ps.setString(2, myemail);
			ps.setString(3, mypassword);
			ps.setString(4, myage);
			ps.setString(5, mycontact);
			
		    ps.setBlob(6, inputStream);
		    
		    
	        int count = ps.executeUpdate();
	        
	        if(count>0) {
	        	response.setContentType("text/html");
	        	//out.print("<h3 style='color:green'>user register successfully </h3> ");
	        	RequestDispatcher rd = request.getRequestDispatcher("/EmployeeLogin.jsp");
	        	rd.include(request, response);
	        	
	        }
	        
	        else {
	        	response.setContentType("text/html");
	        	//out.print("<h3 style='color:red'>user not register due to some error </h3> ");
	        	RequestDispatcher rd = request.getRequestDispatcher("/EmployeeRegister.jsp");
	        	RequestDispatcher rd1 = request.getRequestDispatcher("/EmployeeRegisterError.jsp");
	        	rd.include(request, response);
	        	rd1.include(request, response);
	        }
	        con.close();
		}
		catch(Exception e){
			e.printStackTrace();
			response.setContentType("text/html");
        	//out.print("<h3 style='color:red'> Exception occured : "+e.getMessage()+" </h3> ");
			RequestDispatcher rd = request.getRequestDispatcher("/EmployeeRegister.jsp");
        	RequestDispatcher rd1 = request.getRequestDispatcher("/EmployeeRegisterError.jsp");
        	rd.include(request, response);
        	rd1.include(request, response);
		}
		 finally {
	            try {
	                if (inputStream != null) {
	                    inputStream.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		
		}

}
