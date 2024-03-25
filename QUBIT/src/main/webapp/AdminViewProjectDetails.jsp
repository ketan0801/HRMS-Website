<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hrms.Project" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Project Details</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">View Project Details</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>Project Name</th>
                    <th>Project Description</th>
                    <th>Employee</th>
                </tr>
            </thead>
            <tbody>
            <% 
                List<Project> projects = (List<Project>) request.getAttribute("projects");
                if (projects != null && !projects.isEmpty()) {
                    for (Project project : projects) {
            %>
                <tr>
                    <td><%= project.getProjectName() %></td>
                    <td><%= project.getProjectDescription() %></td>
                    <td><%= project.getEmployee() %></td>
                </tr>
            <% 
                    }
                } else {
                    // No projects found
                    out.println("<tr><td colspan='3'>No projects found.</td></tr>");
                }
            %>
            </tbody>
        </table>
    </div>
</body>
</html>
