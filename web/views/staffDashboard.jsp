<%-- 
    Document   : StaffManagement
    Created on : Nov 6, 2025, 6:30:34 PM
    Author     : nhtuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="models.Reservation" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/staff.css">
    </head>
    <body>
        <div class="main-container">
        <div class="navigation-tabs">
            <a href="ReservationController" class="nav-tab active">Reservations</a>
            <a href="CreateOrder" class="nav-tab">Create Order</a>
            <a href="OrderController" class="nav-tab">Order List</a>
              <a href="LogoutController" class="nav-tab logout">Logout</a>
        </div>
        
        <form action="ReservationController" method="POST">
                <header class="header">
                    <h4>Search by Name: <input type="text" name="name" value="${param.name}"> <button type="submit">Search</button></h4>
                </header>
            </form>
            <div class="table-container">
               
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Full Name</th>
                        <th>Phone</th>
                        <th>Table Number</th>
                        <th>Reservation Time</th>
                        <th>Number Of People</th>
                        <th>Note</th>
                        <th>Action</th>    
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="customer" items="${customers}">
                            
                       
                    <tr>
                        <td>${customer.id}</td>
                        <td>${customer.customerName}</td>
                        <td>${customer.phone}</td>
                        <td>${customer.tableNumber}</td>
                        <td>${customer.reservationTime}</td>
                        <td>${customer.numPeople}</td>
                        <td>${customer.note}</td>
                          <td>
                  
                    <a href="EditReservation?id=${customer.id}" class="btn btn-edit">Edit</a>

                  
                    <a href="DeleteReservation?id=${customer.id}" 
                       class="btn btn-delete" 
                       onclick="return confirm('Are you sure you want to delete this reservation?');">
                       Delete
                    </a>
                </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        <form class="form-section" action="AddReservation" method="POST">
    <h4>Manage Reservations</h4>

    <div class="form-row">
        <label>Full Name:
            <input type="text" name="customer_name" required>
        </label>

        <label>Phone:
            <input type="text" name="phone" required>
        </label>

        <label>Table Number:
            <input type="number" name="table_number" required>
        </label>

        <label>Reservation Time:
            <input type="datetime-local" name="reservation_time" required>
        </label>
    </div>

    <div class="form-row">
        <label>Number of People:
            <input type="number" name="num_people" required>
        </label>

        <label>Note:
            <input type="text" name="note">
        </label>
    </div>

    <div class="form-row">
        <input type="hidden" name="action" value="add"> 
        <button type="submit" name="action" value="add">ADD</button>
        <button type="reset">RESET</button>
    </div>
</form>
        </div>
        <footer>
  <p>© 2025 Restaurant Table Booking System | Staff Portal</p>
  <p>Group 1 - Staff Portal</p>
</footer>
    </body>
</html>