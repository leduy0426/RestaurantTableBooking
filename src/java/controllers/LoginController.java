package controllers;

import dal.AdminDAO;
import dal.StaffDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Admin;
import java.io.IOException;
import models.Staff;

//@WebServlet("/LoginServlet")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String role = request.getParameter("role");

    HttpSession session = request.getSession();

    // ---- Admin ----
    if ("admin".equals(role)) {
        AdminDAO dao = new AdminDAO();
        Admin admin = dao.checkLogin(username, password);

        if (admin != null) {
            session.setAttribute("admin", admin);
            response.sendRedirect("views/adminDashboard.jsp");
            return; 
        } else {
            request.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return; 
        }
    }

    // ---- Staff ----
    if ("staff".equals(role)) {
        StaffDAO sdao = new StaffDAO();
        Staff staff = sdao.checkLogin(username, password);

        if (staff != null) {
            session.setAttribute("staff", staff);
            response.sendRedirect("ReservationController");
            return; 
        } else {
            request.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return; 
        }
    }
}


    // Nếu có truy cập bằng GET, ta chuyển hướng về login.jsp luôn
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }
}
