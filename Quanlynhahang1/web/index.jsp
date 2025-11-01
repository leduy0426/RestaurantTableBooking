<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant Table Booking</title>
    <style>
        body { 
            font-family: Arial; 
            background-color: #f7f7f7; 
            margin: 0; 
        }

        .header {
            background: #b30000;
            padding: 25px;
            text-align: center;
            color: white;
            font-size: 30px;
            font-weight: bold;
        }

        .content-wrapper {
            display: flex;
            justify-content: center;
            align-items: stretch;
            width: 100%;
            margin-top: 0;
        }

        .side-img {
            width: 35%;               /* make image big */
            height: calc(100vh - 80px);
            object-fit: cover;
        }

        .container {
            width: 400px;
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 0 12px #999;
            margin: auto;
        }

        input, textarea {
            width: 100%; padding: 10px; margin: 10px 0;
            border-radius: 5px; border: 1px solid #ccc;
        }

        button {
            width: 100%; padding: 12px;
            background: #28a745; color: white; border: none;
            font-size: 16px; font-weight: bold;
            border-radius: 5px;
        }

        button:hover { background: #218838; }
    </style>
</head>
<body>

<div class="header">
    Royal Dragon Restaurant
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
