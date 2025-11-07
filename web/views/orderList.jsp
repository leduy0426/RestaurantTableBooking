<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/staff.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/order.css">
</head>
<body>
    <div class="navigation-tabs">
        <a href="ReservationController" class="nav-tab">Reservations</a>
        <a href="CreateOrder" class="nav-tab">Create Order</a>
        <a href="OrderController" class="nav-tab active">Order List</a>
    </div>
    
    <div class="container">
    <h2>Order List</h2>
    <div class="table-container">
<table class="order-table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Reservation ID</th>
            <th>Staff ID</th>
            <th>Order Time</th>
            <th>Total Amount</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="o" items="${orders}">
            <tr>
                <td>${o.id}</td>
                <td>${o.reservationId}</td>
                <td>${o.staffId}</td>
                <td>${o.orderTime}</td>
                <td>${o.totalAmount}</td>
                <td>${o.status}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
    </div>

    <div style="text-align: center; margin-top: 20px;">
        <a href="CreateOrder" style="padding: 10px 20px; background-color: #2a9fd6; color: white; text-decoration: none; border-radius: 4px; font-weight: bold;">
            Create New Order
        </a>
    </div>
    </div>
</body>
</html>
