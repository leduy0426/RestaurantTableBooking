package controller;

import dal.ReservationDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Reservation;

@WebServlet("/abc")
public class BookTableServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String customerName = request.getParameter("customerName");
        String phone = request.getParameter("phone");
        int tableNumber = Integer.parseInt(request.getParameter("tableNumber"));
        String reservationTime = request.getParameter("reservationTime");
        int numPeople = Integer.parseInt(request.getParameter("numPeople"));
        String note = request.getParameter("note");

        Reservation r = new Reservation(customerName, phone, tableNumber, reservationTime, numPeople, note);

    ReservationDAO dao = new ReservationDAO();
    boolean success = dao.addReservation(r);

    if (success) {
        response.sendRedirect("success.jsp");
    } else {
        response.sendRedirect("error.jsp");
    }
    }
}
