package com.attendance.management.system.bean;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "attendance")
public class AttendanceBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private Date createdDate;
    private String checkIn;
    private String checkOut;
    private boolean checkInLate;
    private boolean checkOutEarly;
    private String remarksCheckIn;
    private String remarksCheckOut;
    private Integer hours;

    @Column(name = "status",columnDefinition = "SMALLINT")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserBean userBean;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
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

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public boolean isCheckInLate() {
        return checkInLate;
    }

    public void setCheckInLate(boolean checkInLate) {
        this.checkInLate = checkInLate;
    }

    public boolean isCheckOutEarly() {
        return checkOutEarly;
    }

    public void setCheckOutEarly(boolean checkOutEarly) {
        this.checkOutEarly = checkOutEarly;
    }

    public String getRemarksCheckIn() {
        return remarksCheckIn;
    }

    public void setRemarksCheckIn(String remarksCheckIn) {
        this.remarksCheckIn = remarksCheckIn;
    }

    public String getRemarksCheckOut() {
        return remarksCheckOut;
    }

    public void setRemarksCheckOut(String remarksCheckOut) {
        this.remarksCheckOut = remarksCheckOut;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
