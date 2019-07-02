package com.fountane.www.fountanehrapp.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getNewsApiModel {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("newsobj")
    @Expose
    private List<Newsobj> newsobj = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Newsobj> getNewsobj() {
        return newsobj;
    }

    public void setNewsobj(List<Newsobj> newsobj) {
        this.newsobj = newsobj;
    }
    public class Newsobj {

        @SerializedName("newsId")
        @Expose
        private String newsId;
        @SerializedName("empCode")
        @Expose
        private String empCode;
        @SerializedName("venue")
        @Expose
        private String venue;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("imageFirebaseLink")
        @Expose
        private String imageFirebaseLink;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;

        public String getNewsId() {
            return newsId;
        }

        public void setNewsId(String newsId) {
            this.newsId = newsId;
        }

        public String getEmpCode() {
            return empCode;
        }

        public void setEmpCode(String empCode) {
            this.empCode = empCode;
        }

        public String getVenue() {
            return venue;
        }

        public void setVenue(String venue) {
            this.venue = venue;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageFirebaseLink() {
            return imageFirebaseLink;
        }

        public void setImageFirebaseLink(String imageFirebaseLink) {
            this.imageFirebaseLink = imageFirebaseLink;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Object getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(Object deletedAt) {
            this.deletedAt = deletedAt;
        }

    }
}
