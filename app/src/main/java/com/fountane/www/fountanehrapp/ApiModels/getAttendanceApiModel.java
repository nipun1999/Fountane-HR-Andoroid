package com.fountane.www.fountanehrapp.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getAttendanceApiModel {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("attendanceobj")
    @Expose
    private List<Attendanceobj> attendanceobj = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Attendanceobj> getAttendanceobj() {
        return attendanceobj;
    }

    public void setAttendanceobj(List<Attendanceobj> attendanceobj) {
        this.attendanceobj = attendanceobj;
    }
    public class Attendanceobj {

        @SerializedName("attendanceId")
        @Expose
        private Integer attendanceId;
        @SerializedName("empCode")
        @Expose
        private String empCode;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("checkIn")
        @Expose
        private String checkIn;
        @SerializedName("checkOut")
        @Expose
        private String checkOut;
        @SerializedName("comments")
        @Expose
        private Object comments;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;

        public Integer getAttendanceId() {
            return attendanceId;
        }

        public void setAttendanceId(Integer attendanceId) {
            this.attendanceId = attendanceId;
        }

        public String getEmpCode() {
            return empCode;
        }

        public void setEmpCode(String empCode) {
            this.empCode = empCode;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCheckIn() {
            return checkIn;
        }

        public void setCheckIn(String checkIn) {
            this.checkIn = checkIn;
        }

        public String  getCheckOut() {
            return checkOut;
        }

        public void setCheckOut(String checkOut) {
            this.checkOut = checkOut;
        }

        public Object getComments() {
            return comments;
        }

        public void setComments(Object comments) {
            this.comments = comments;
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
