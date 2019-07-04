package com.fountane.www.fountanehrapp.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class registrationApiModel {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("checkEmail")
    @Expose
    private CheckEmail checkEmail;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public CheckEmail getCheckEmail() {
        return checkEmail;
    }

    public void setCheckEmail(CheckEmail checkEmail) {
        this.checkEmail = checkEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class CheckEmail {

        @SerializedName("empCode")
        @Expose
        private String empCode;
        @SerializedName("fountaneEmail")
        @Expose
        private String fountaneEmail;
        @SerializedName("newUser")
        @Expose
        private Boolean newUser;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;

        public String getEmpCode() {
            return empCode;
        }

        public void setEmpCode(String empCode) {
            this.empCode = empCode;
        }

        public String getFountaneEmail() {
            return fountaneEmail;
        }

        public void setFountaneEmail(String fountaneEmail) {
            this.fountaneEmail = fountaneEmail;
        }

        public Boolean getNewUser() {
            return newUser;
        }

        public void setNewUser(Boolean newUser) {
            this.newUser = newUser;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

