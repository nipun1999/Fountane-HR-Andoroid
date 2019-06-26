package com.fountane.www.fountanehrapp.models;

public class Attendance {

    String date,month,checkIn,checkOut;

    public Attendance(){

    }

    public Attendance(String date, String month, String checkIn, String checkOut) {
        this.date = date;
        this.month = month;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
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
