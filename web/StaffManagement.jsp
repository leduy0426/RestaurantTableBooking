<%-- 
    Document   : StaffManagement
    Created on : Nov 6, 2025, 6:30:34 PM
    Author     : nhtuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="models.Staff" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/staff.css">
    </head>
    <body>
        <form action="AdminController" method="POST">
                <header class="header">
                    <h4>Search by Name: <input type="text" name="name"> <button type="submit">Search</button></h4>
                </header>
            </form>
            <div class="table-container">
                <% List<Staff> staffs = (List<Staff>)request.getAttribute("staff"); %>
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Full Name</th>
                        <th>Phone</th>
                        <th>Birth date</th>
                        <th>Email</th>
                        <th>Position</th>
                        <th>Address</th>
                        <th>Username</th>
                        <th>Password</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for(Staff staff : staffs){ %>
                    <tr>
                        <td><%= staff.id %></td>
                        <td><%= staff.full_name %></td>
                        <td><%= staff.birthdate %></td>
                        <td><%= staff.position %></td>
                        <td><%= staff.phone %></td>
                        <td><%= staff.email %></td>
                        <td><%= staff.address %></td>
                        <td><%= staff.username %></td>
                        <td><%= staff.password %></td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

        <form class="form-section" action="AdminController" method="POST">
                <h4>Current Staff</h4>
                <div class="form-row">
                    <label>ID: <input type="text"></label>
                    <label>Full Name: <input type="text"></label>
                    <label>Phone: <input type="text"></label>
                    <label>Birth Date: <input type="date"></label>

                </div>
                <div class="form-row">
                    <label>Email: <input type="text"></label>
                    <label>Position: <input type="text"></label>
                    <label>Username: <input type="text"></label>
                    <label>Password: <input type="text"></label>
                </div>
                <div>
                    <button type="submit">ADD</button>
                    <button type="submit">EDIT</button>
                    <button type="submit">DELETE</button>
                    <button type="submit">RESET</button>
                </div>
            </form>
        </form>
    </body>
</html>
