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
        
        <div class="order-info">
            <form action="CreateOrder" method="POST" id="orderForm">
                <div class="form-group">
                    <label>Reservation ID:</label>
                    <input type="number" name="reservation_id" id="reservation_id" required>
                </div>
                
                <div class="form-group">
                    <label>Staff ID:</label>
                    <input type="number" name="staff_id" id="staff_id" required>
                </div>
                
                <div class="form-group">
                    <label>Status:</label>
                    <select name="status" id="status">
                        <option value="pending">Pending</option>
                        <option value="served">Served</option>
                        <option value="paid">Paid</option>
                        <option value="cancelled">Cancelled</option>
                    </select>
                </div>
            </form>
        </div>

        <div class="menu-section">
            <h3>Select Menu Items</h3>
            <div class="menu-grid">
                <c:forEach var="item" items="${menuItems}">
                    <div class="menu-item-card" data-id="${item.id}" data-name="${item.name}" data-price="${item.price}">
                        <c:if test="${not empty item.imageUrl}">
                            <img src="${pageContext.request.contextPath}/${item.imageUrl}" alt="${item.name}" class="item-image">
                        </c:if>
                        <div class="item-info">
                            <h4>${item.name}</h4>
                            <p class="item-description">${item.description}</p>
                            <p class="item-price">${item.price} VNĐ</p>
                            <div class="item-controls">
                                <button type="button" class="btn-decrease" onclick="decreaseQuantity(${item.id})">-</button>
                                <input type="number" id="qty_${item.id}" value="0" min="0" class="quantity-input" readonly>
                                <button type="button" class="btn-increase" onclick="increaseQuantity(${item.id})">+</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="cart-section">
            <h3>Order Summary</h3>
            <div id="cart-items"></div>
            <div class="cart-total">
                <strong>Total: <span id="total-amount">0</span> VNĐ</strong>
            </div>
            <input type="hidden" name="total_amount" id="total_amount" form="orderForm">
            <input type="hidden" name="order_items" id="order_items" form="orderForm">
            <div class="cart-actions">
                <button type="button" onclick="clearCart()" class="btn-clear">Clear Cart</button>
                <button type="submit" form="orderForm" class="btn-submit" onclick="return submitOrder()">Create Order</button>
            </div>
        </div>
    </div>

  <script>
    let cart = {};

    // 🟢 Tăng số lượng món
    function increaseQuantity(itemId) {
        itemId = parseInt(itemId);
        
        // ✅ CÚ PHÁP ĐÚNG: Sử dụng biến itemId để tìm phần tử
        const selector = `[data-id="${itemId}"]`; 
        const itemCard = document.querySelector(selector);
        const qtyInput = document.getElementById(`qty_${itemId}`); 
        
        if (!itemCard || !qtyInput) {
            console.error(`❌ Không tìm thấy phần tử cho itemId = ${itemId}`);
            return;
        }

        const itemName = itemCard.getAttribute('data-name');
        const itemPrice = parseFloat(itemCard.getAttribute('data-price'));
        
        let currentQty = parseInt(qtyInput.value) || 0;
        currentQty++;
        qtyInput.value = currentQty; // Giá trị tăng lên trên ô input

        // Cập nhật đối tượng 'cart'
        if (cart[itemId]) {
            cart[itemId].quantity = currentQty;
        } else {
            cart[itemId] = {
                name: itemName,
                price: itemPrice,
                quantity: currentQty
            };
        }
        
        updateCart(); 
    }

    // 🔴 Giảm số lượng món
    function decreaseQuantity(itemId) {
        
        // ✅ CÚ PHÁP ĐÚNG: Sử dụng biến itemId để tìm phần tử
        const qtyInput = document.getElementById(`qty_${itemId}`);
        
        if (!qtyInput) {
            console.error(`❌ Không tìm thấy input cho itemId = ${itemId}`);
            return;
        }

        let currentQty = parseInt(qtyInput.value) || 0;

        if (currentQty > 0) {
            currentQty--;
            qtyInput.value = currentQty;

            if (currentQty === 0) {
                delete cart[itemId];
            } else if (cart[itemId]) {
                cart[itemId].quantity = currentQty;
            }

            updateCart();
        }
    }

    // 🧮 Cập nhật giỏ hàng
    function updateCart() {
        const cartItemsDiv = document.getElementById('cart-items');
        const totalAmountSpan = document.getElementById('total-amount');
        const totalAmountInput = document.getElementById('total_amount');

        let total = 0;
        cartItemsDiv.innerHTML = '';

        if (Object.keys(cart).length === 0) {
            cartItemsDiv.innerHTML = '<p class="empty-cart">No items selected</p>';
            totalAmountSpan.textContent = '0';
            totalAmountInput.value = '0';
            return;
        }

        const cartList = document.createElement('ul');
        cartList.className = 'cart-list';

        for (let itemId in cart) {
            const item = cart[itemId];
            const itemTotal = item.price * item.quantity;
            total += itemTotal;

            const li = document.createElement('li');
            li.className = 'cart-item';
            
            // ✅ SỬA LỖI: Thêm biến vào chuỗi HTML để hiển thị chi tiết
            li.innerHTML = `
                <span class="item-name">${item.name}</span>
                <span class="item-qty">x${item.quantity}</span>
                <span class="item-total">${itemTotal.toLocaleString('vi-VN')} VNĐ</span>
            `;
            cartList.appendChild(li);
        }

        cartItemsDiv.appendChild(cartList);
        totalAmountSpan.textContent = total.toLocaleString('vi-VN');
        totalAmountInput.value = total;
    }

    // 🧹 Xóa toàn bộ giỏ hàng
    function clearCart() {
        if (confirm('Are you sure you want to clear the cart?')) {
            cart = {};
            document.querySelectorAll('.quantity-input').forEach(input => {
                input.value = '0';
            });
            updateCart();
        }
    }

    // 📦 Chuẩn bị dữ liệu trước khi submit
    function submitOrder() {
        if (Object.keys(cart).length === 0) {
            alert('Please select at least one item');
            return false;
        }

        const orderItems = [];
        for (let itemId in cart) {
            const item = cart[itemId];
            orderItems.push({
                itemId: parseInt(itemId),
                itemName: item.name,
                quantity: item.quantity,
                price: item.price
            });
        }

        document.getElementById('order_items').value = JSON.stringify(orderItems);
        return true;
    }
</script>

</body>
</html>

