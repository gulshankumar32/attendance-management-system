package com.attendance.management.system.dto;

public class AttendanceDTO {
    private  String id;
    private String createdDate;
    private String checkIn;
    private String checkOut;
    private UserDTO userBean;
    private String hours;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public UserDTO getUserBean() {
        return userBean;
    }

    public void setUserBean(UserDTO userBean) {
        this.userBean = userBean;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
