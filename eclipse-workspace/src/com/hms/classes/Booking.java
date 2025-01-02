package com.hms.classes;

public class Booking {
    private String customerId;
    private String name;
    private String mobileNumber;
    private String email;
    private int roomType;
    private String checkInDate;
    private String checkOutDate;
    private int bookingId;
    private double charge;
    private int roomNumber;
    private String location;

    public Booking(String customerId, String name, String mobileNumber, String email, String location, int roomType, String checkInDate, String checkOutDate, double charge, int roomNumber) {
        this.customerId = customerId;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.location = location;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.charge = charge;
        this.roomNumber = roomNumber;
    }

    public Booking(String customerId, String name, String mobileNumber, String email, String location, int roomType, String checkInDate, String checkOutDate, double charge, int roomNumber, int bookingId) {
        this.customerId = customerId;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.location = location;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.charge = charge;
        this.roomNumber = roomNumber;
        this.bookingId = bookingId;
    }

	// Getters and setters
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getRoomType() {
        return roomType;
    }
    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }
    public String getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }
    public String getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public double getCharge() {
        return charge;
    }
    public void setCharge(double charge) {
        this.charge = charge;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}