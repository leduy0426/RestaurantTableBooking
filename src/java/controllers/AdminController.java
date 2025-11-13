/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.AdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import models.Staff;

/**
 *
 * @author nhtuy
 */
public class AdminController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String search = request.getParameter("search");
        String action = request.getParameter("action");
        String full_name = request.getParameter("full_name");
        String birthdate = request.getParameter("birthdate");
        String position = request.getParameter("position");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        AdminDAO addao = new AdminDAO();
        Staff staff = new Staff();
        List<Staff> staffs;
        
        if ("delete".equals(action)) {
                int ID = Integer.parseInt(id);
                boolean result = addao.deleteStaff(ID);

                if (result) {
                    request.setAttribute("message", "Success!");
                } else {
                    request.setAttribute("message", "Delete failed!");
                } 
        }
        
        if (id != null) {
            staff = addao.getStaffByID(id);
        }
        if ("add".equals(action)) {
            staff.setFull_name(full_name);
            staff.setPosition(position);
            staff.setPhone(phone);
            staff.setEmail(email);
            staff.setAddress(address);
            if (username != null && !username.isEmpty()) {
                staff.setUsername(username);
            } else {
                staff.setUsername(null);
            }
            if (password != null && !password.isEmpty()) {
                staff.setPassword(password);
            } else {
                staff.setPassword(null);
            }

        
            if (birthdate != null && !birthdate.isEmpty()) {
                staff.setBirthdate(java.sql.Date.valueOf(birthdate));
            } else {
                staff.setBirthdate(null);
            }
            boolean result = addao.addStaff(staff);

            if (result) {
                request.setAttribute("message", "Success!");

            } else {
                request.setAttribute("message", "Add failed!");
            }
        }
        if ("update".equals(action)) {
            staff.setFull_name(full_name);
            staff.setPosition(position);
            staff.setPhone(phone);
            staff.setEmail(email);
            staff.setAddress(address);
            if (username != null && !username.isEmpty()) {
                staff.setUsername(username);
            } else {
                staff.setUsername(null);
            }
            if (password != null && !password.isEmpty()) {
                staff.setPassword(password);
            } else {
                staff.setPassword(null);
            }

            
            if (birthdate != null && !birthdate.isEmpty()) {
                staff.setBirthdate(java.sql.Date.valueOf(birthdate));
            } else {
                staff.setBirthdate(null);
            }
             boolean result = addao.updateStaff(staff);

            if (result)
                request.setAttribute("message", "Success!");
            else
                request.setAttribute("message", "Edit failed!");
        }
        
        if (search == null || search.isBlank()) {
            staffs = addao.getStaff();
        } else {
            staffs = addao.getStaffByName(search);
        }

        request.setAttribute("id", id);
        request.setAttribute("staff", staff);
        request.setAttribute("search", search);
        request.setAttribute("staffs", staffs);
        request.getRequestDispatcher("StaffManagement.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
