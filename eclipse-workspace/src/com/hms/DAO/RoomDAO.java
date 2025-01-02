package com.hms.DAO;
import com.hms.classes.*;
import com.hms.driver.*;

import java.util.List;
import java.util.ArrayList;

import java.sql.*;


public class RoomDAO {
    private Connection connection;

    public RoomDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Room> getAllRooms() {
        List<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM Room";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("roomNumber"),
                        rs.getInt("roomType"),
                        rs.getString("place"),
                        rs.getInt("price"),
                        rs.getBoolean("isBooked"),
                        rs.getString("dateOfAvailability"),
                        rs.getInt("bookingId")
                );
                roomList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public void addRoom(Room room) {
        String query = "INSERT INTO Room (roomNumber, roomType, place, price, isBooked, dateOfAvailability, bookingId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, room.getRoomNumber());
            pstmt.setInt(2, room.getRoomType());
            pstmt.setString(3, room.getPlace());
            pstmt.setInt(4, room.getPrice());
            pstmt.setBoolean(5, room.getIsBooked());
            pstmt.setString(6, room.getDateOfAvailability());
            if (room.getBookingId() != null) {
                pstmt.setInt(7, room.getBookingId());
            } else {
                pstmt.setNull(7, Types.INTEGER);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRoom(Room room) {
        String query = "UPDATE Room SET roomType = ?, place = ?, price = ?, isBooked = ?, dateOfAvailability = ?, bookingId = ? WHERE roomNumber = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, room.getRoomType());
            pstmt.setString(2, room.getPlace());
            pstmt.setInt(3, room.getPrice());
            pstmt.setBoolean(4, room.getIsBooked());
            pstmt.setString(5, room.getDateOfAvailability());
            if (room.getBookingId() != null) {
                pstmt.setInt(6, room.getBookingId());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }
            pstmt.setInt(7, room.getRoomNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoom(int roomNumber) {
        String query = "DELETE FROM Room WHERE roomNumber = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roomNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Room getRoomByNumber(int roomNumber) {
        String query = "SELECT * FROM Room WHERE roomNumber = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roomNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Room(
                            rs.getInt("roomNumber"),
                            rs.getInt("roomType"),
                            rs.getString("place"),
                            rs.getInt("price"),
                            rs.getBoolean("isBooked"),
                            rs.getString("dateOfAvailability"),
                            rs.getObject("bookingId", Integer.class)
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

