package com.hms.classes;

public class Complaint {
    private String userId;
    private String contactNumber;
    private int roomNumber;
    private String typeOfComplaint;
    private String feedbackRating;

    public Complaint(String userId, String contactNumber, int roomNumber, String typeOfComplaint, String feedbackRating) {
        this.userId = userId;
        this.contactNumber = contactNumber;
        this.roomNumber = roomNumber;
        this.typeOfComplaint = typeOfComplaint;
        this.feedbackRating = feedbackRating;
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public String getTypeOfComplaint() {
        return typeOfComplaint;
    }
    public void setTypeOfComplaint(String typeOfComplaint) {
        this.typeOfComplaint = typeOfComplaint;
    }
    public String getFeedbackRating() {
        return feedbackRating;
    }
    public void setFeedbackRating(String feedbackRating) {
        this.feedbackRating = feedbackRating;
    }
}