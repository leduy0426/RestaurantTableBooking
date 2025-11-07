<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Order</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/createOrder.css">
</head>
<body>

<div class="navigation-tabs">
    <a href="ReservationController" class="nav-tab">Reservations</a>
    <a href="CreateOrder" class="nav-tab active">Create Order</a>
    <a href="OrderController" class="nav-tab">Order List</a>
</div>

<div class="container">
    <h2>Create New Order</h2>

    <form action="CreateOrder" method="POST">

        <div class="form-group">
            <label>Reservation ID:</label>
            <input type="number" name="reservation_id" required>
        </div>

       <div class="form-group">
    <label for="staffId">Staff ID:</label>
    <input type="text" id="staffId" name="staffId" value="${staff.id}" readonly>
</div>

        <div class="form-group">
            <label>Status:</label>
            <select name="status">
                <option value="pending">Pending</option>
                <option value="served">Served</option>
                <option value="paid">Paid</option>
                <option value="cancelled">Cancelled</option>
            </select>
        </div>

        <h3>Select Menu Items</h3>

        <div class="menu-grid">
            <c:forEach var="item" items="${menuItems}">
                <div class="menu-item-card">
                    <c:if test="${not empty item.imageUrl}">
                        <img src="${pageContext.request.contextPath}/${item.imageUrl}" 
                             alt="${item.name}" 
                             class="item-image">
                    </c:if>
                    <div class="item-info">
                        <h4>${item.name}</h4>
                        <p class="item-description">${item.description}</p>
                        <p class="item-price">${item.price} VNĐ</p>

                        <div class="item-controls">
                            <label>Quantity:</label>
                            <input type="number" name="qty_${item.id}" min="0" value="0" style="width: 60px;">
                            <input type="hidden" name="price_${item.id}" value="${item.price}">
                            <input type="hidden" name="name_${item.id}" value="${item.name}">
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="cart-actions">
            <button type="submit" class="btn-submit">Create Order</button>
        </div>

    </form>
</div>
        <footer>
  <p>© 2025 Restaurant Table Booking System | Staff Portal</p>
  <p>Group 1 - Staff Portal</p>
</footer>
</body>
</html>
