package com.fountane.www.fountanehrapp.models;

public class Grievances {

    private String date,month,time,status,desc;
    public Grievances(){

    }

    public Grievances(String date,String month,String time, String status, String desc) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.desc = desc;
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
