package com.fountane.www.fountanehrapp.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class signInApiModel {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("auth")
    @Expose
    private Auth auth;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("empCode")
    @Expose
    private String empCode;
    @SerializedName("status")
    @Expose
    private Boolean status;


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public class Auth {

        @SerializedName("fountaneEmail")
        @Expose
        private String fountaneEmail;
        @SerializedName("empCode")
        @Expose
        private String empCode;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("roleId")
        @Expose
        private String roleId;

        public String getFountaneEmail() {
            return fountaneEmail;
        }

        public void setFountaneEmail(String fountaneEmail) {
            this.fountaneEmail = fountaneEmail;
        }

        public String getEmpCode() {
            return empCode;
        }

        public void setEmpCode(String empCode) {
            this.empCode = empCode;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

    }
}
