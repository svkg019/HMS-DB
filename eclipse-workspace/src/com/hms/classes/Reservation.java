package com.hms.classes;

public class Reservation {
    private int reservationId;
    private String checkInDate;
    private String checkOutDate;
    private String roomPreference;
    private String name;
    private String contact;

    public Reservation(String checkInDate, String checkOutDate, String roomPreference, String name, String contact) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomPreference = roomPreference;
        this.name = name;
        this.contact = contact;
    }


    public int getReservationId() {
        return reservationId;
    }
    public String getCheckInDate() {
        return checkInDate;
    }
    public String getCheckOutDate() {
        return checkOutDate;
    }
    public String getRoomPreference() {
        return roomPreference;
    }
    public String getName() {
        return name;
    }
    public String getContact() {
        return contact;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }
    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    public void setRoomPreference(String roomPreference) {
        this.roomPreference = roomPreference;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
}

