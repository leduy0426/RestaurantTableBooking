package controllers;

import dal.MenuDAO;
import dal.OrderDAO;
import dal.StaffDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.MenuItem;
import models.Order;
import models.Staff;

@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MenuDAO menuDAO = new MenuDAO();
    request.setAttribute("menuItems", menuDAO.getAllMenuItems());

  Staff staff = (Staff) request.getSession().getAttribute("staff");
if (staff == null) {
    response.sendRedirect("login.jsp"); // hoặc servlet LoginController
    return;
}

    request.getRequestDispatcher("views/createOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
Staff staff = (Staff) request.getSession().getAttribute("staff");
if (staff == null) {
    response.sendRedirect("login.jsp"); // hoặc servlet LoginController
    return;
}
            int reservationId = Integer.parseInt(request.getParameter("reservation_id"));
            int staffId = Integer.parseInt(request.getParameter("staffId"));
            String status = request.getParameter("status");

            
            MenuDAO menuDAO = new MenuDAO();
            List<MenuItem> menuItems = menuDAO.getAllMenuItems();

            double totalAmount = 0;
            OrderDAO oDAO = new OrderDAO();

            
            Order order = new Order();
            order.setReservationId(reservationId);
            order.setStaffId(staffId);
            order.setStatus(status);
            order.setTotalAmount(0); // sẽ cập nhật sau khi tính tổng

            int orderId = oDAO.addOrderWithItems(order);

            // lấy món trong menu để kiểm tra input
            for (MenuItem item : menuItems) {
                String qtyStr = request.getParameter("qty_" + item.getId());
                if (qtyStr != null && !qtyStr.isEmpty()) {
                    int quantity = Integer.parseInt(qtyStr);
                    if (quantity > 0) {
                        double price = item.getPrice();
                        double itemTotal = price * quantity;
                        totalAmount += itemTotal;

                        // Lưu món
                        oDAO.addOrderItem(orderId, item.getId(), item.getName(), quantity, price);
                    }
                }
            }

         //tính tiền
            oDAO.updateOrderTotal(orderId, totalAmount);

            response.sendRedirect("OrderController");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("CreateOrder?error=1");
        }
    }
}
