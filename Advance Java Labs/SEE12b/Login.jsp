<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Retrieve username and password from request
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    // Hardcoded valid credentials
    String validUsername = "admin";
    String validPassword = "1234";

    // Validate user
    if (username != null && username.equals(validUsername) && password != null && password.equals(validPassword)) {
        // Store username in session
        session.setAttribute("username", username);

        // Display welcome message
%>

    <h1>Welcome, <%= username %>!</h1>
    <p>You have successfully registered.</p>
<%
    } else {
        // Redirect back to index.html if invalid input
        response.sendRedirect("index.html");
    }
%>
