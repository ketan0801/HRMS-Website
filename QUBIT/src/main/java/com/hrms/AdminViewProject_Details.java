package com.hrms;

import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.sql.*;
import java.util.Base64;

@WebServlet("/Project")
public class AdminViewProject_Details extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Qubit ";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Project> projects = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT ProjectName, ProjectDescription, employee FROM AssignProject";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Project project = new Project();
                    project.setProjectName(resultSet.getString("ProjectName"));
                    project.setProjectDescription(resultSet.getString("ProjectDescription"));
                    project.setEmployee(resultSet.getString("employee"));
                    projects.add(project);
                }
            }
        } 
        catch (SQLException e) {
            log("Error occurred while fetching project details", e);
            // You can also forward to an error page here
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
            return;
        }

        request.setAttribute("projects", projects);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminViewProjectDetails.jsp");
        dispatcher.forward(request, response);
    }
}


	                    // Convert Blob to base64-encoded string
	                 /*   Blob documentBlob = resultSet.getBlob("Document");
	                    if (documentBlob != null) {
	                        InputStream inputStream = documentBlob.getBinaryStream();
	                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	                        byte[] buffer = new byte[8192];
	                        int bytesRead;
	                        while ((bytesRead = inputStream.read(buffer)) != -1) {
	                            outputStream.write(buffer, 0, bytesRead);
	                        }
	                        byte[] documentBytes = outputStream.toByteArray();
	                        String documentBase64 = Base64.getEncoder().encodeToString(documentBytes);
	                        project.setDocumentBase64(documentBase64);
	                    }*/
	      
