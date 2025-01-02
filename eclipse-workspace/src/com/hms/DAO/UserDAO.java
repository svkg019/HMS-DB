package com.hms.DAO;
import com.hms.classes.*;
import com.hms.driver.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM User";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                User user = new User(
                        rs.getString("userId"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("password")
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void addUser(User user) {
        String query = "INSERT INTO User (userId, fullName, email, phoneNumber, password) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getFullName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhoneNumber());
            pstmt.setString(5, user.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(String userId, String email) {
        String query = "UPDATE User SET email = ? WHERE userId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, userId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User details are updated successfully");
            } else {
                System.out.println("User not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String userId) {
        String query = "DELETE FROM User WHERE userId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, userId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User details are deleted");
            } else {
                System.out.println("User not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(String userId) {
        String query = "SELECT * FROM User WHERE userId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getString("userId"),
                            rs.getString("fullName"),
                            rs.getString("email"),
                            rs.getString("phoneNumber"),
                            rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}