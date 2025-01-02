package com.hms.classes;

public class PaymentDetails {
    private String userId;
    private String cardHolderName;
    private String cardNumber;
    private int cvv;
    private String expiryDate;

    public PaymentDetails(String userId, String cardHolderName, String cardNumber, int cvv, String expiryDate) {
        this.userId = userId;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    public String getUserId() {
        return userId;
    }
    public String getCardHolderName() {
        return cardHolderName;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public int getCvv() {
        return cvv;
    }
    public String getExpiryDate() {
        return expiryDate;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}