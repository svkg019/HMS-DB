package com.hms.DAO;
import com.hms.classes.*;
import com.hms.driver.*;
import java.util.List;
import java.util.ArrayList;


import java.sql.*;


public class PaymentDetailsDAO {
    private Connection connection;

    public PaymentDetailsDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void addPaymentDetails(PaymentDetails pd) {
        String query = "INSERT INTO PaymentDetails (userId, cardHolderName, cardNumber, cvv, expiryDate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, pd.getUserId());
            pstmt.setString(2, pd.getCardHolderName());
            pstmt.setString(3, pd.getCardNumber());
            pstmt.setInt(4, pd.getCvv());
            pstmt.setString(5, pd.getExpiryDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PaymentDetails> getAllPaymentDetails() {
        List<PaymentDetails> paymentDetailsList = new ArrayList<>();
        String query = "SELECT * FROM PaymentDetails";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                PaymentDetails pd = new PaymentDetails(
                        rs.getString("userId"),
                        rs.getString("cardHolderName"),
                        rs.getString("cardNumber"),
                        rs.getInt("cvv"),
                        rs.getString("expiryDate")
                );
                paymentDetailsList.add(pd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentDetailsList;
    }

    public List<PaymentDetails> getPaymentDetailsByUserId(String userId) {
        List<PaymentDetails> paymentDetailsList = new ArrayList<>();
        String query = "SELECT * FROM PaymentDetails WHERE userId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PaymentDetails pd = new PaymentDetails(
                            rs.getString("userId"),
                            rs.getString("cardHolderName"),
                            rs.getString("cardNumber"),
                            rs.getInt("cvv"),
                            rs.getString("expiryDate")
                    );
                    paymentDetailsList.add(pd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentDetailsList;
    }
}

