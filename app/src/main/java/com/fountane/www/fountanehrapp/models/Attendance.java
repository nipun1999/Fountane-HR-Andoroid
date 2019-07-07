package com.fountane.www.fountanehrapp.models;

public class Attendance {

    String date;
    String month;
    String checkIn;
    String checkOut;
    String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    String attendanceId;

    public Attendance() {

    }

    public Attendance(String date, String month, String checkIn, String checkOut, String attendanceId, String comment) {
        this.date = date;
        this.month = month;
        this.comment = comment;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.attendanceId = attendanceId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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


}
