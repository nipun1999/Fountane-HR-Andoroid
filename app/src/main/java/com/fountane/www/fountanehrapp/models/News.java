package com.fountane.www.fountanehrapp.models;

public class News {

    private String id, title, date, publishedby, time, imageUrl, month, data;

    public News(String newsId, String title, String date, String publishedby, String time, String imageUrl, String month, String data) {
        this.id = newsId;
        this.title = title;
        this.date = date;
        this.publishedby = publishedby;
        this.time = time;
        this.imageUrl = imageUrl;
        this.month = month;
        this.data = data;
    }

    public News() {

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
