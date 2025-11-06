package controllers;

import dal.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Admin;
import java.io.IOException;

//@WebServlet("/LoginServlet")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy dữ liệu từ form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Gọi DAO để kiểm tra đăng nhập
        AdminDAO dao = new AdminDAO();
        Admin admin = dao.checkLogin(username, password);

        if (admin != null) {
            // Nếu đúng tài khoản → tạo session và chuyển hướng
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);
            response.sendRedirect("views/adminDashboard.jsp");
        } else {
            // Sai tài khoản hoặc mật khẩu
            request.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
            request.getRequestDispatcher("view/login.jsp").forward(request, response);
        }
    }

    // Nếu có truy cập bằng GET, ta chuyển hướng về login.jsp luôn
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}
