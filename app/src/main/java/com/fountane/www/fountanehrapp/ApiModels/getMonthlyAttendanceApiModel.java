package com.fountane.www.fountanehrapp.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getMonthlyAttendanceApiModel {
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

        @SerializedName("date")
        @Expose
        private Integer date;
        @SerializedName("type")
        @Expose
        private String type;

        public Integer getDate() {
            return date;
        }

        public void setDate(Integer date) {
            this.date = date;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }
}


