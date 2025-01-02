package com.hms.classes;

public class Feedback {
    private String userName;
    private int contactNumber;
    private int RoomNumber;
    private String typeOfComplaint;
    private String feedback;

    public Feedback(String userName, int contactNumber, int RoomNumber, String typeOfComplaint, String feedback) {
        this.userName = userName;
        this.contactNumber = contactNumber;
        this.RoomNumber = RoomNumber;
        this.typeOfComplaint = typeOfComplaint;
        this.feedback = feedback;
    }
}