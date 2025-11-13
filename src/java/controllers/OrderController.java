package controllers;

import dal.OrderDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Order;
import models.Staff;

public class OrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Staff staff = (Staff) request.getSession().getAttribute("staff");
        Object admin = request.getSession().getAttribute("admin"); // có thể là model Admin

        if (staff == null && admin == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        OrderDAO sdao = new OrderDAO();
        List<Order> orders = sdao.getAllOrders();

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("views/orderList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            Staff staff = (Staff) request.getSession().getAttribute("staff");
            Object admin = request.getSession().getAttribute("admin"); // có thể là model Admin

            if (staff == null && admin == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            int reservationId = Integer.parseInt(request.getParameter("reservation_id"));
            int staffId = Integer.parseInt(request.getParameter("staff_id"));
            double totalAmount = Double.parseDouble(request.getParameter("total_amount"));
            String status = request.getParameter("status");

            Order o = new Order();
            o.setReservationId(reservationId);
            o.setStaffId(staffId);
            o.setTotalAmount(totalAmount);
            o.setStatus(status);

            OrderDAO sdao = new OrderDAO();
            sdao.addOrder(o);

            response.sendRedirect("OrderController"); // quay lại danh sách order
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi thêm đơn hàng: " + e.getMessage());
            request.getRequestDispatcher("views/error.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Order Controller";
    }
}
