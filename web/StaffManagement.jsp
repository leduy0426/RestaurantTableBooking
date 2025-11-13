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
        <title>Staff Management</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/staff.css">
    </head>
    <body>
        <form action="AdminController" method="POST">
            <%String search = (String)request.getAttribute("search");%>
            <%String id = (String)request.getAttribute("id");%>
            <%if(search == null || search.isBlank()) search = "";%>
            <%if(id == null || id.isBlank()) id = "";%>
            <header class="header">
                <h4>Search by Name: <input type="text" name="search" value="<%= search %>">
                    <input type="hidden" name="id" value="<%= id%>">
                    <button type="submit">Search</button>
                </h4>
            </header>
        </form>
        <div class="table-container">
            <% List<Staff> staffs = (List<Staff>)request.getAttribute("staffs"); %>
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
                        <td>
                            <form action="AdminController" method="GET">
                                <input type="hidden" name="id" value="<%=staff.id%>">
                                <input type="hidden" name="search" value="<%= search %>">
                                <button type="submit" class="buttons"><%= staff.full_name %></button>
                            </form>
                        </td>
                        <td><%= staff.phone %></td>
                        <td><%= staff.birthdate %></td>
                        <td><%= staff.email %></td>
                        <td><%= staff.position %></td>
                        <td><%= staff.address %></td>
                        <td><%= staff.username %></td>
                        <td><%= staff.password %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

        <form id="staffForm" class="form-section" action="AdminController" method="GET">
            <%Staff staff = (Staff)request.getAttribute("staff");%>
            <%String role = (staff != null) ? staff.getPosition() : "";%>
            <%
            if(staff.full_name == null) staff.full_name ="";
            if(staff.phone == null) staff.phone ="";
            if(staff.email == null) staff.email ="";
            if(staff.username == null) staff.username ="";
            if(staff.password == null) staff.password ="";
            if(staff.address == null) staff.address ="";
            %>
            <h4>Current Staff</h4>
            <div class="form-row">
                <label>ID: <input type="text" value="<%=(staff.id == 0) ? "":staff.id %>" readonly name="id"></label>
                <label>Full Name: <input type="text" value="<%= staff.full_name %>" name="full_name"></label>
                <label>Phone: <input type="text" value="<%= staff.phone %>" name="phone"></label>
                <label>Birth Date: <input type="date" value="<%= staff.birthdate %>" name="birthdate"></label>
                <label>Address: <input type="text" value="<%= staff.address %>" name="address"></label>
            </div>
            <div class="form-row">
                <label>Email: <input type="email" value="<%= staff.email %>" name="email"></label>
                <label>Position: <select name="position">
                        <option value="" disabled selected>-- Select --</option>
                        <option value="Manager" <%= "Manager".equals(role) ? "selected" : "" %>>Manager</option>
                        <option value="Waiter" <%= "Waiter".equals(role) ? "selected" : "" %>>Waiter</option>
                        <option value="Chef" <%= "Chef".equals(role) ? "selected" : "" %>>Chef</option>
                        <option value="Receptionist" <%= "Receptionist".equals(role) ? "selected" : "" %>>Receptionist</option>
                        <option value="Cashier" <%= "Cashier".equals(role) ? "selected" : "" %>>Cashier</option>
                        <option value="Cleaner" <%= "Cleaner".equals(role) ? "selected" : "" %>>Cleaner</option>
                        <option value="Security" <%= "Security".equals(role) ? "selected" : "" %>>Security</option>
                    </select>
                </label>
                <label>Username: <input type="text" value="<%= staff.username%>"name="username"></label>
                <label>Password: <input type="text" value="<%= staff.password %>"name="password"></label>
            </div>
            <div>
                <button type="submit" name="action" value="add">ADD</button>
                <button type="submit" name="action" value="update">EDIT</button>
                <button type="submit" name="action" value="delete">DELETE</button>
            </div>
            <div>
                <%String message = (String)request.getAttribute("message");%>
                <%if(message == null) message="";%>
                <h5><%=message%></h5>
            </div>
        </form>
        <form action="AdminController" method="POST">
            <div class="flex">
                <button type="submit" class="buttons">RESET</button>
            </div>
        </form>
    </body>
</html>
