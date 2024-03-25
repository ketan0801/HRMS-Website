<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page import="java.util.Base64"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-t6/A/E7IvNUqko9kq+yND3VSipg0fZd1R7LAtG0V1wP2uw6Z1c7GkxQQ5/1z10AVkOnB3VKyCpRQVh1gqdm5lw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        body {
            background-color: #fff;
        }
        .profile-header-container {
            margin: 50px auto;
            text-align: center;
        }
        .profile-header-img {
            padding: 54px;
        }
        .profile-header-img img {
            width: 160px;
            height: 160px;
            border-radius: 60%;
        }
        .profile-header-info {
            margin-top: 20px;
        }
        .profile-header-buttons {
            margin-top: 30px;
        }
        .profile-header-buttons .btn {
            margin: 0 10px;
            border-radius: 20px;
        }
        
         .button {
    display: inline-block;
    width: 100px;
    height: 100px;
    background-color: #4CAF50;
    border-radius: 50%;
    color: white;
    text-align: center;
    line-height: 100px;
    margin: 10px;
    font-size: 16px;
    border: none;
    cursor: pointer;
  }
  /* Additional styles for different button colors */
  .button-red { background-color: #f44336; }
  .button-blue { background-color: #008CBA; }

        
    </style>
    <%-- Add cache-control headers --%>
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
    %>
<script>
    // Detect browser back button action
    window.onload = function () {
        if (window.history && window.history.pushState) {
            window.history.pushState('forward', null, './#forward');
            window.onpopstate = function (event) {
                // Check if the session has expired
                if (!event.state || event.state !== 'forward') {
                    // Display message and redirect
                    alert("Server session has expired. Please login again.");
                    window.location.href = "EmployeeLogin.jsp";
                } else {
                    // Push another state to prevent continuous alert on back button
                    window.history.pushState('forward', null, './#forward');
                }
            };
        }
    };
</script>

</head>
<body>
<%
    if (session != null) {
        String userEmail = (String) session.getAttribute("email"); // Assuming the email is stored in the session
        if (userEmail != null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qubit", "root", "root");
                PreparedStatement ps = con.prepareStatement("SELECT name,email,image FROM emplregis WHERE email = ?");
                ps.setString(1, userEmail);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String userName = rs.getString("name");
                    String Email = rs.getString("email");
                    
                    
                 // Retrieve image data
                    Blob imageBlob = rs.getBlob("image");
                    byte[] imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);                    
%>
                  
                  
  <nav class="navbar navbar-expand-lg" style="background-color: #103358;">
  <div class="container-fluid">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto"> <!-- Shifted links to the left side -->
        <li class="nav-item">
          <a class="nav-link active text-white" aria-current="page" href="index.jsp">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active text-white" aria-current="page" href="#">Employee List</a>
        </li>
      </ul>
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link text-white" href="logout">Logout</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
  
   
    
                  
                  
                    <div class="container">
                        <div class="profile-header-container">
                            <div class="profile-header-img">
                                <img src="data:image/jpeg;base64,<%= base64Image %>" alt="Profile Image">
                            </div>
                            <div class="profile-header-info">
                                <h1>Hello, <%= userName %>!</h1>
                                <h1>Hello, <%= Email %>!</h1>
                                 <!-- Populate user's name here -->
                                
                                
                            </div>
                            <a href="#"><button class="button button-red">Salary Details</button></a>
                            <a href="#"><button class="button button-blue">Project Details</button></a>
                            <a href="#"><button class="button">Request Leave</button> </a>
                        </div>
                    </div>
                   
  <footer class="bg-dark text-white py-4">
    <div class="container text-center">
        <a href="#"><i class="fab fa-instagram text-white mx-3"  aria-hidden="true"></i></a>
        <a href="#"><i class="fab fa-linkedin-in text-white mx-3"></i></a>
        <a href="#"><i class="fab fa-github text-white mx-3"></i></a>
    </div>
</footer>
                   
<%
                } else {
                    // User not found in the database
                    response.sendRedirect("index.jsp"); // Redirect to login page
                }
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<h3 style='color:red'> Exception occurred: " + e.getMessage() + "</h3>");
            }
        } else {
            // User email not found in the session
            response.sendRedirect("index.jsp"); // Redirect to login page
        }
    } else {
        // Session not found
        response.sendRedirect("index.jsp"); // Redirect to login page
    }
%>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
