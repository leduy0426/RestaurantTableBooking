/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Reservation;
import models.Staff;

/**
 *
 * @author fpt
 */
public class EditReservation extends HttpServlet {

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
            out.println("<title>Servlet EditReservation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditReservation at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        String idParam = request.getParameter("id");
        int id = Integer.parseInt(idParam);
        Staff staff = (Staff) request.getSession().getAttribute("staff");
        Object admin = request.getSession().getAttribute("admin"); // có thể là model Admin

        if (staff == null && admin == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        StaffDAO dao = new StaffDAO();
        Reservation r = dao.getCustomerById(id);

        request.setAttribute("reservation", r);
        request.getRequestDispatcher("views/editReservation.jsp").forward(request, response);
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
        //processRequest(request, response);
        Staff staff = (Staff) request.getSession().getAttribute("staff");
        Object admin = request.getSession().getAttribute("admin"); // có thể là model Admin

        if (staff == null && admin == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("customer_name");
        String phone = request.getParameter("phone");
        int tableNumber = Integer.parseInt(request.getParameter("table_number"));
        String reservationTime = request.getParameter("reservation_time");
        int numPeople = Integer.parseInt(request.getParameter("num_people"));
        String note = request.getParameter("note");

        StaffDAO dao = new StaffDAO();
        dao.updateReservation(id, name, phone, tableNumber, reservationTime, numPeople, note);

        response.sendRedirect("ReservationController");
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
