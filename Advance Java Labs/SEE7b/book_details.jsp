<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Entry</title>
</head>
<body>
    <h2>Enter Book Details</h2>
    <form method="post">
        <input type="hidden" name="action" value="insert">
        <input type="text" name="book_no" placeholder="Book No" required><br><br>
        <input type="text" name="title" placeholder="Title" required><br><br>
        <input type="text" name="author" placeholder="Author" required><br><br>
        <input type="text" name="publication" placeholder="Publication" required><br><br>
        <input type="text" name="price" placeholder="Price" required><br><br>
        <button type="submit">Submit</button>
    </form>

    <h2>Search Book by Title</h2>
    <form method="post">
        <input type="hidden" name="action" value="search">
        <input type="text" name="title" placeholder="Enter Title" required><br><br>
        <button type="submit">Search</button>
    </form>

    <%
        String URL = "jdbc:mysql://localhost:3307/bookdb", USER = "root", PASS = "";
        String action = request.getParameter("action");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            Class.forName("com.mysql.jdbc.Driver");

            if ("insert".equals(action)) {
                String sql = "INSERT INTO books VALUES ('" + Integer.parseInt(request.getParameter("book_no")) + "', '" +
                             request.getParameter("title") + "', '" + request.getParameter("author") + "', '" +
                             request.getParameter("publication") + "', '" + Integer.parseInt(request.getParameter("price")) + "')";
                stmt.executeUpdate(sql);
                out.println("<p>Book added successfully!</p>");
            } 
            else if ("search".equals(action)) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM books WHERE Title = '" + request.getParameter("title") + "'");
                if (rs.next()) {
                    out.println("<p>Book No: " + rs.getInt("Book_No") + "</p>");
                    out.println("<p>Title: " + rs.getString("Title") + "</p>");
                    out.println("<p>Author: " + rs.getString("Author") + "</p>");
                    out.println("<p>Publication: " + rs.getString("Publication") + "</p>");
                    out.println("<p>Price: " + rs.getInt("Price") + "</p>");
                } else {
                    out.println("<p>No book found.</p>");
                }
            }
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    %>
</body>
</html>
