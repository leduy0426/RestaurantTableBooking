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
        <form action="ReservationController" method="POST">
                <header class="header">
                    <h4>Search by Name: <input type="text" name="name"> <button type="submit">Search</button></h4>
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
           
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        <form class="form-section" action="ReservationController" method="POST">
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