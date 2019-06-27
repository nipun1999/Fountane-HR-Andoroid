package com.fountane.www.fountanehrapp.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class registrationApiModel {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("register")
    @Expose
    private Register register;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public class Register {

        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("empCode")
        @Expose
        private String empCode;
        @SerializedName("fountaneEmail")
        @Expose
        private String fountaneEmail;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

