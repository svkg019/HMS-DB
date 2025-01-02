package com.hms.DAO;
import com.hms.classes.*;
import com.hms.driver.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    private Connection connection;

    public BookingDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void addBooking(Booking booking) {
        String query = "INSERT INTO Booking (customerId, name, mobileNumber, email, location, roomType, checkInDate, checkOutDate, charge, roomNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, booking.getCustomerId());
            pstmt.setString(2, booking.getName());
            pstmt.setString(3, booking.getMobileNumber());
            pstmt.setString(4, booking.getEmail());
            pstmt.setString(5, booking.getLocation());
            pstmt.setInt(6, booking.getRoomType());
            pstmt.setString(7, booking.getCheckInDate());
            pstmt.setString(8, booking.getCheckOutDate());
            pstmt.setDouble(9, booking.getCharge());
            pstmt.setInt(10, booking.getRoomNumber());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    booking.setBookingId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookingList = new ArrayList<>();
        String query = "SELECT * FROM Booking";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getString("customerId"),
                        rs.getString("name"),
                        rs.getString("mobileNumber"),
                        rs.getString("email"),
                        rs.getString("location"),
                        rs.getInt("roomType"),
                        rs.getString("checkInDate"),
                        rs.getString("checkOutDate"),
                        rs.getDouble("charge"),
                        rs.getInt("roomNumber"),
                        rs.getInt("bookingId")
                );
                bookingList.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }

    public void updateBooking(int bookingId, int roomType) {
        String query = "UPDATE Booking SET roomType = ? WHERE bookingId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roomType);
            pstmt.setInt(2, bookingId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBooking(int bookingId) {
        String query = "DELETE FROM Booking WHERE bookingId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, bookingId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Booking> getBookingsByCustomerId(String customerId) {
        List<Booking> bookingList = new ArrayList<>();
        String query = "SELECT * FROM Booking WHERE customerId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Booking booking = new Booking(
                            rs.getString("customerId"),
                            rs.getString("name"),
                            rs.getString("mobileNumber"),
                            rs.getString("email"),
                            rs.getString("location"),
                            rs.getInt("roomType"),
                            rs.getString("checkInDate"),
                            rs.getString("checkOutDate"),
                            rs.getDouble("charge"),
                            rs.getInt("roomNumber"),
                            rs.getInt("bookingId")
                    );
                    bookingList.add(booking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }
}