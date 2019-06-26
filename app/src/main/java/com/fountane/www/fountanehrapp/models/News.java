package com.fountane.www.fountanehrapp.models;

public class News {

    private String title,date,publishedby,time,imageUrl,month;

    public News(String title, String date, String publishedby, String time,String imageUrl,String month) {
        this.title = title;
        this.date = date;
        this.publishedby = publishedby;
        this.time = time;
        this.imageUrl = imageUrl;
        this.month = month;
    }

    public News(){

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPublishedby() {
        return publishedby;
    }

    public void setPublishedby(String publishedby) {
        this.publishedby = publishedby;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
