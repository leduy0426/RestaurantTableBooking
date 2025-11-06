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

        // Lấy dữ liệu từ form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Gọi DAO để kiểm tra đăng nhập
        AdminDAO dao = new AdminDAO();
        Admin admin = dao.checkLogin(username, password);
        HttpSession session = request.getSession();
        if (role.equals("admin")) {

            if (admin != null) {
               
                session.setAttribute("admin", admin);
                response.sendRedirect("views/adminDashboard.jsp");
                
            } else {
                request.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
        

        StaffDAO sdao = new StaffDAO();
        Staff staff = sdao.checkLogin(username, password);
        
        if(role.equals("staff") && staff != null){
              session.setAttribute("staff", staff);
                response.sendRedirect("ReservationController");
        }else{
             request.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
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
