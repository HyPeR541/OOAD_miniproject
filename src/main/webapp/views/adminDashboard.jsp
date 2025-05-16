<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ooad6.ecommerce.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminDashboard.css">

</head>
<body>
<h1>Admin Dashboard</h1>

<a href="${pageContext.request.contextPath}/adminDashboard/order-history">
    <button>View Order History</button>
</a>



<h2>All Users</h2>
<%
    List<User> users = (List<User>) request.getAttribute("users");
%>

<% if (users != null && !users.isEmpty()) { %>
<table border="1">
    <tr>
        <th>User ID</th>
        <th>Name</th>
        <th>Action</th>
    </tr>
    <% for(User user : users) { %>
    <tr>
        <td><%= user.getUserId() %></td>
        <td><%= user.getName() %></td>
        <td>
            <% if (!user.getName().toLowerCase().contains("admin")) { %>
            <form action="${pageContext.request.contextPath}/adminDashboard/deleteUser" method="post">
                <input type="hidden" name="userid" value="<%= user.getUserId() %>">
                <button type="submit">Delete</button>
            </form>
            <% } else { %>
            <span>Admin (Cannot Delete)</span>
            <% } %>
        </td>
    </tr>
    <% } %>
</table>
<% } else { %>
<p>No users found.</p>
<% } %>
</body>
</html>
