<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Reservation</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/editreservation.css">

</head>
<body>
    <h2>Edit Reservation</h2>

    <form action="EditReservation" method="POST">
        <input type="hidden" name="id" value="${reservation.id}">

        <label>Full Name:</label>
        <input type="text" name="customer_name" value="${reservation.customerName}" required><br>

        <label>Phone:</label>
        <input type="text" name="phone" value="${reservation.phone}" required><br>

        <label>Table Number:</label>
        <input type="number" name="table_number" value="${reservation.tableNumber}" required><br>

        <label>Reservation Time:</label>
        <input type="datetime-local" name="reservation_time"
               value="${reservation.reservationTime}" required><br>

        <label>Number of People:</label>
        <input type="number" name="num_people" value="${reservation.numPeople}" required><br>

        <label>Note:</label>
        <input type="text" name="note" value="${reservation.note}"><br>

        <button type="submit">Save</button>
        <a href="ReservationController">Cancel</a>
    </form>
        
                <footer>
  <p>© 2025 Restaurant Table Booking System | Staff Portal</p>
  <p>Group 1 - Staff Portal</p>
</footer>
</body>
</html>
