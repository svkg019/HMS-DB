package com.hms.classes;

public class User {
    private String userId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;

    public User(String userId, String fullName, String email, String phoneNumber, String password) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


    public String getUserId() {
        return userId;
    }
    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getPassword() {
        return password;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setPassword(String password) {
        this.password = password;
    }



}
