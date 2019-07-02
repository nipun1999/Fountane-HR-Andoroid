package com.fountane.www.fountanehrapp.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class updateAttendanceApiModel {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("attendanceobj")
    @Expose
    private List<Integer> attendanceobj = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Integer> getAttendanceobj() {
        return attendanceobj;
    }

    public void setAttendanceobj(List<Integer> attendanceobj) {
        this.attendanceobj = attendanceobj;
    }
}
