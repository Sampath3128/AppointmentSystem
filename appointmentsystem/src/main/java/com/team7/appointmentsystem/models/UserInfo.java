package com.team7.appointmentsystem.models;

public class UserInfo {
    private String email;
    private Long userId;
    private String name;
    private String mobileNumber;

    public UserInfo(String email, Long userId, String name, String mobileNumber) {
        this.email = email;
        this.userId = userId;
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "email='" + email + '\'' +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
