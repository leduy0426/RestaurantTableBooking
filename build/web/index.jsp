<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant Table Booking</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index.css">
</head>
<body>
<div class="header">
    Royal Dragon Restaurant
    <a href="login.jsp">Login</a>
</div>
<div class="content-wrapper">
    <img src="https://images.unsplash.com/photo-1552566626-52f8b828add9?auto=format&fit=crop&w=800&q=60" class="side-img">
    <div class="container">
        <h2>Book a Table</h2>
        <form action="abc" method="post">
            <input type="text" name="customerName" placeholder="Full Name" required>
            <input type="text" name="phone" placeholder="Phone Number" required>
            <input type="number" name="tableNumber" placeholder="Table Number" required>
            <input type="datetime-local" name="reservationTime" required>
            <input type="number" name="numPeople" placeholder="Number of People" required>
            <textarea name="note" placeholder="Note"></textarea>
            <button type="submit">Book Table</button>
        </form>
    </div>
    <img src="https://images.unsplash.com/photo-1541544741938-0af808871cc0?auto=format&fit=crop&w=800&q=60" class="side-img">
</div>
</body>
</html>
