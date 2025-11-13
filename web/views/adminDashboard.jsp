<%-- 
    Document   : adminDashboard
    Created on : Nov 6, 2025, 12:26:36 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admindashboard.css">
    </head>
    <body>
        <form action="AdminController" method="Post">
            <a href="${pageContext.request.contextPath}/AdminController">Staff Management</a>
            <a href="${pageContext.request.contextPath}/ReservationController">Customer Management</a>
            <a href="${pageContext.request.contextPath}/login.jsp">Log Out</a>
        </form>
    </body>
</html>
