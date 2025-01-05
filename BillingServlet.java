package com.hms.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hms.classes.Booking;
import com.hms.dao.BookingDAO;

@WebServlet("/billing")
public class BillingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDAO bookingDAO = new BookingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.html");
            return;
        }

        Booking booking = bookingDAO.getLatestBookingByCustomerId(userId);
        if (booking == null) {
            response.sendRedirect("noBooking.html");
            return;
        }

        double roomServiceCharge = 50.0;
        double spaServiceCharge = 120.0;
        double tax = 52.0;
        double totalAmount = booking.getCharge() + roomServiceCharge + spaServiceCharge + tax;

        request.setAttribute("bookingId", booking.getBookingId());
        request.setAttribute("roomRate", booking.getCharge());
        request.setAttribute("roomServiceCharge", roomServiceCharge);
        request.setAttribute("spaServiceCharge", spaServiceCharge);
        request.setAttribute("tax", tax);
        request.setAttribute("totalAmount", totalAmount);

        request.getRequestDispatcher("billing.jsp").forward(request, response);
    }
}