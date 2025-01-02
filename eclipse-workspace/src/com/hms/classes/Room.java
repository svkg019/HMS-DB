package com.hms.classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Room {
    private int roomNumber;
    private int roomType;
    private String place;
    private int price;
    private boolean isBooked;
    private String dateOfAvailability;
    private int bookingId; // Nullable field

    public Room(int roomNumber, int roomType, String place, int price, boolean isBooked, String dateOfAvailability, int bookingId) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.place = place;
        this.price = price;
        this.isBooked = isBooked;
        this.dateOfAvailability = dateOfAvailability;
        this.bookingId = bookingId;
    }

    public Room(int roomNumber, int roomType, String place, int price, boolean isBooked) {
        this(roomNumber, roomType, place, price, isBooked, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 0);
    }



	public int getRoomNumber() {
        return roomNumber;
    }
    public boolean getIsBooked() {
        return isBooked;
    }
    public int getRoomType() {
        return roomType;
    }
    public String getDateOfAvailability() {
        return dateOfAvailability;
    }
    public int getPrice() {
        return price;
    }
    public String getPlace() {
        return place;
    }
    public Integer getBookingId() {
        return bookingId;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }
    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }
    public void setDateOfAvailability(String dateOfAvailability) {
        this.dateOfAvailability = dateOfAvailability;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
}