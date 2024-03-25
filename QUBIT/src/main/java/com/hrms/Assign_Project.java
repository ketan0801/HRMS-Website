package com.hrms;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/AssignProject")

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2,  // 2 MB
	    		maxFileSize = 1024 * 1024 * 16,   // 16 MB
	    maxRequestSize = 1024 * 1024 * 50     // 50 MB
	)
public class Assign_Project extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parse form data
        String projectName = request.getParameter("projectName");
        String projectDescription = request.getParameter("projectDescription");
        Part documentPart = request.getPart("document");
        String employee = request.getParameter("employee");

        InputStream documentStream = null;

        if (documentPart != null) {
            documentStream = documentPart.getInputStream();
        }

        // Database connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/Qubit";
        String dbUsername = "root";
        String dbPassword = "root";

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            // SQL query to insert data into AssignProject table
            String sql = "INSERT INTO AssignProject (ProjectName, ProjectDescription, Document, employee) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, projectName);
            statement.setString(2, projectDescription);
            statement.setBlob(3, documentStream);
            statement.setString(4, employee);

            // Execute the query
            statement.executeUpdate();

            // Close database connection
            statement.close();
            connection.close();

            // Redirect user to a success page
            RequestDispatcher rd = request.getRequestDispatcher("/AssignProjects.jsp");
			RequestDispatcher rd1 = request.getRequestDispatcher("/ProjectSuccess.jsp");
        	rd.include(request, response);
        	rd1.include(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle any errors by redirecting to the same page with an error parameter
            RequestDispatcher rd = request.getRequestDispatcher("/AssignProjects.jsp");
			RequestDispatcher rd1 = request.getRequestDispatcher("/ProjectError.jsp");
        	rd.include(request, response);
        	rd1.include(request, response);
        }
    }
}

