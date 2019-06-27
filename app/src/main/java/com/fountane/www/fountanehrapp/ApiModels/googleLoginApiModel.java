package com.fountane.www.fountanehrapp.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class googleLoginApiModel {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("empCode")
    @Expose
    private String empCode;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("name")
    @Expose
    private String name;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
