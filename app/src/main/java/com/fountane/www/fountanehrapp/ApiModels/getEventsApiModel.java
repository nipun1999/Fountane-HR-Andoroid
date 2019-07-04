package com.fountane.www.fountanehrapp.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getEventsApiModel {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("events")
    @Expose
    private List<Event> events = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
    public class Event {

        @SerializedName("eventId")
        @Expose
        private String eventId;
        @SerializedName("empCode")
        @Expose
        private String empCode;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("eventDate")
        @Expose
        private String eventDate;
        @SerializedName("eventVenue")
        @Expose
        private String eventVenue;
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

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getEmpCode() {
            return empCode;
        }

        public void setEmpCode(String empCode) {
            this.empCode = empCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEventDate() {
            return eventDate;
        }

        public void setEventDate(String eventDate) {
            this.eventDate = eventDate;
        }

        public String getEventVenue() {
            return eventVenue;
        }

        public void setEventVenue(String eventVenue) {
            this.eventVenue = eventVenue;
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
