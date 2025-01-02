package com.hms.DAO;
import com.hms.classes.*;
import com.hms.driver.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {
    private Connection connection;

    public ComplaintDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void addComplaint(Complaint complaint) {
        String query = "INSERT INTO Complaint (userId, contactNumber, roomNumber, typeOfComplaint, feedbackRating) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, complaint.getUserId());
            pstmt.setString(2, complaint.getContactNumber());
            pstmt.setInt(3, complaint.getRoomNumber());
            pstmt.setString(4, complaint.getTypeOfComplaint());
            pstmt.setString(5, complaint.getFeedbackRating());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Complaint> getAllComplaints() {
        List<Complaint> complaintList = new ArrayList<>();
        String query = "SELECT * FROM Complaint";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Complaint complaint = new Complaint(
                        rs.getString("userId"),
                        rs.getString("contactNumber"),
                        rs.getInt("roomNumber"),
                        rs.getString("typeOfComplaint"),
                        rs.getString("feedbackRating")
                );
                complaintList.add(complaint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return complaintList;
    }

    public List<Complaint> getComplaintsByUserId(String userId) {
        List<Complaint> complaintList = new ArrayList<>();
        String query = "SELECT * FROM Complaint WHERE userId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Complaint complaint = new Complaint(
                            rs.getString("userId"),
                            rs.getString("contactNumber"),
                            rs.getInt("roomNumber"),
                            rs.getString("typeOfComplaint"),
                            rs.getString("feedbackRating")
                    );
                    complaintList.add(complaint);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return complaintList;
    }
}