package com.hms.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hms.classes.Booking;
import com.hms.classes.User;
import com.hms.dao.UserDAO;
import com.hms.dao.BookingDAO;
import com.hms.driver.*;

@WebServlet("/reservation")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDAO bookingDAO = new BookingDAO();
    private UserDAO userDAO = new UserDAO();
    private HotelService hotelService = new HotelService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        User user = userDAO.getUserById(userId);

        if (user == null) {
            response.sendRedirect("login.html");
            return;
        }

        String checkInDate = request.getParameter("check-in");
        String checkOutDate = request.getParameter("check-out");
        String roomTypeStr = request.getParameter("room-type");
        String fullName = request.getParameter("full-name");
        String contact = request.getParameter("contact");

        int roomType = getRoomType(roomTypeStr);
        int roomNumber = hotelService.checkRoomAvailability(roomType, "Kolkata");
        if (roomNumber == -1) {
            response.sendRedirect("reservation.html?error=No rooms available");
            return;
        }

        double charge = 
//        		hotelService.calculateCharge(roomType, "Kolkata", checkInDate, checkOutDate);
        100.00;
        Booking booking = new Booking(userId, fullName, contact, user.getEmail(), "Kolkata", roomType, checkInDate, checkOutDate, charge, roomNumber);
        bookingDAO.addBooking(booking);

        response.setContentType("application/json");
        response.getWriter().write("{\"bookingId\": " + booking.getBookingId() + ", \"checkIn\": \"" + checkInDate + "\", \"checkOut\": \"" + checkOutDate + "\", \"roomType\": \"" + roomTypeStr + "\", \"guestName\": \"" + fullName + "\", \"contact\": \"" + contact + "\"}");
    }

    private int getRoomType(String roomTypeStr) {
        switch (roomTypeStr) {
            case "standard":
                return 1;
            case "deluxe":
                return 2;
            case "executive":
                return 3;
            default:
                return 1;
        }
    }
}