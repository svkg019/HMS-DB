package com.hms.driver;

import com.hms.classes.*;
import com.hms.DAO.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class HotelService {
    private final UserDAO userDAO;
    private final BookingDAO bookingDAO;
    private final RoomDAO roomDAO;
    private final ComplaintDAO complaintDAO;
    private final PaymentDetailsDAO paymentDetailsDAO;

    public HotelService() {
        userDAO = new UserDAO();
        bookingDAO = new BookingDAO();
        roomDAO = new RoomDAO();
        complaintDAO = new ComplaintDAO();
        paymentDetailsDAO = new PaymentDetailsDAO();
    }

    public void registerUser(User user) {
        userDAO.addUser(user);
    }

    public void updateUser(String userId, String email) {
        userDAO.updateUser(userId, email);
    }

    public void deleteUser(String userId) {
        userDAO.deleteUser(userId);
    }

    public void viewUsers() {
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            System.out.println(user.getUserId() + " " + user.getFullName() + " " + user.getEmail() + " " + user.getPhoneNumber());
        }
    }

    public boolean userVerification(String userId, String password) {
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            if (user.getUserId().equalsIgnoreCase(userId) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdmin(String userId) {
        return userId.toLowerCase().contains("hotel.com");
    }

    public void addBooking(Booking booking) {
        bookingDAO.addBooking(booking);
    }

    public void deleteBooking(int bookingId) {
        bookingDAO.deleteBooking(bookingId);
    }

    public void viewBookingHistoryByID(String userId) {
        List<Booking> bookings = bookingDAO.getBookingsByCustomerId(userId);
        if (bookings.isEmpty()) {
            System.out.println("No booking history found");
            return;
        }
        for (Booking booking : bookings) {
            System.out.println(booking.getBookingId() + " " + booking.getCheckInDate() + " " + booking.getCheckOutDate() + " " + booking.getRoomNumber());
        }
    }

    public void viewAllBookingHistory() {
        List<Booking> bookings = bookingDAO.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("No booking history found");
            return;
        }
        for (Booking booking : bookings) {
            System.out.println(booking.getBookingId() + " " + booking.getCheckInDate() + " " + booking.getCheckOutDate() + " " + booking.getRoomNumber());
        }
    }

    public void addRoom(Room room) {
        roomDAO.addRoom(room);
    }

    public void getRoomDetails() {
        List<Room> rooms = roomDAO.getAllRooms();
        for (Room room : rooms) {
            System.out.println();
            System.out.print(room.getRoomNumber() + " " + room.getRoomType() + " " + room.getPlace() + " " + room.getPrice() + " ");
            if (room.getIsBooked()) {
                System.out.print("Occupied" + " ");
            } else {
                System.out.print("Vacant" + " ");
            }
            System.out.print(room.getDateOfAvailability());
            System.out.println();
        }
    }

    public void changeIsBooked(int roomNumber) {
        List<Room> rooms = roomDAO.getAllRooms();
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                if (room.getIsBooked()) {
                    room.setIsBooked(false);
                } else {
                    room.setIsBooked(true);
                }
            }
        }
    }

    // change date of availability
    public void changeDateOfAvailability(int roomNumber, String date) {
        List<Room> rooms = roomDAO.getAllRooms();
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setDateOfAvailability(date);
            }
        }
    }

    public int checkRoomAvailability(int roomType, String place) {
        List<Room> rooms = roomDAO.getAllRooms();
        for (Room room : rooms) {
            if (room.getRoomType() == roomType && room.getPlace().equalsIgnoreCase(place) && !room.getIsBooked()) {
                return room.getRoomNumber();
            }
        }
        return -1;
    }

//    public void updateNameInBooking(int bookingId, String name) {
//        bookingDAO.updateBookingName(bookingId, name);
//    }
//
//    public void updateRoomTypeInBooking(int bookingId, int roomType) {
//        bookingDAO.updateBookingRoomType(bookingId, roomType);
//    }

    // Contact
    public void contactInformation() {
        System.out.println("Mobile No.: +91-0000000000");
        System.out.println("Email: support@hotel.com");
    }

    // Complaint
    public void registerComplaint(Complaint complaint) {
        complaintDAO.addComplaint(complaint);
    }

    public void viewComplaints() {
        List<Complaint> complaintsList = complaintDAO.getAllComplaints();
        if (complaintsList.isEmpty()) {
            System.out.println("No complaints found");
            return;
        }
        for (Complaint c : complaintsList) {
            System.out.println(c.getUserId() + " " + c.getContactNumber() + " " + c.getRoomNumber() + " " + c.getTypeOfComplaint() + " " + c.getFeedbackRating());
            System.out.println("-------------------------");
        }
    }

    // Payment
    public void billCalculation(Booking booking) {
        double totalBill = 0;
        int days = calculateDurationInDays(booking.getCheckInDate(), booking.getCheckOutDate());
        if (days == 0) {
            days = 1;
        }
        int roomCharge = 0;
        if (booking.getRoomType() == 1) {
            roomCharge = 1000;
        } else if (booking.getRoomType() == 2) {
            roomCharge = 2000;
        } else {
            roomCharge = 3000;
        }
        int extra = 0;
        if (booking.getLocation().equalsIgnoreCase("Darjeeling")) {
            extra = 1000;
        }
        totalBill = (roomCharge * days) + extra;
        System.out.println("Room Charge/Day: " + roomCharge);
        System.out.println("Number of days: " + days);
        System.out.println("Extra charge for premium location: " + extra);
        System.out.println("Total Bill: " + totalBill);
    }

    public List<Booking> bookingFinder(String userId) {
        return bookingDAO.getBookingsByCustomerId(userId);
    }

    public static int calculateDurationInDays(String startDateStr, String endDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }

    public void addPaymentDetails(PaymentDetails pd) {
        paymentDetailsDAO.addPaymentDetails(pd);
    }

    public void getPaymentDetailsByUserID(String userId) {
        List<PaymentDetails> paymentHistory = paymentDetailsDAO.getPaymentDetailsByUserId(userId);
        for (PaymentDetails pd : paymentHistory) {
            System.out.println(pd.getUserId() + " " + pd.getCardHolderName() + " " + pd.getCardNumber() + " " + pd.getCvv() + " " + pd.getExpiryDate());
        }
    }

    // bill calculation by booking id
    public void billCalculationByUserId(String userId) {
        List<Booking> bookings = bookingDAO.getBookingsByCustomerId(userId);
        if (bookings.isEmpty()) {
            System.out.println("No bookings found for user ID: " + userId);
            return;
        }
        for (Booking booking : bookings) {
            double totalBill = 0;
            int days = calculateDurationInDays(booking.getCheckInDate(), booking.getCheckOutDate());
            if (days == 0) {
                days = 1;
            }
            int roomCharge = 0;
            if (booking.getRoomType() == 1) {
                roomCharge = 1000;
            } else if (booking.getRoomType() == 2) {
                roomCharge = 2000;
            } else {
                roomCharge = 3000;
            }
            int extra = 0;
            if (booking.getLocation().equalsIgnoreCase("Darjeeling")) {
                extra = 1000;
            }
            totalBill = (roomCharge * days) + extra;
            System.out.println("Booking ID: " + booking.getBookingId() + ", Total Bill: " + totalBill);
        }
    }
}