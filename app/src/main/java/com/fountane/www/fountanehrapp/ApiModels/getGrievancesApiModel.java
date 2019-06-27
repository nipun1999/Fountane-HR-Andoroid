package com.fountane.www.fountanehrapp.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getGrievancesApiModel {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("grievance")
    @Expose
    private List<Grievance> grievance = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Grievance> getGrievance() {
        return grievance;
    }

    public void setGrievance(List<Grievance> grievance) {
        this.grievance = grievance;
    }
    public class Grievance {

        @SerializedName("grievanceId")
        @Expose
        private String grievanceId;
        @SerializedName("empCode")
        @Expose
        private String empCode;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;

        public String getGrievanceId() {
            return grievanceId;
        }

        public void setGrievanceId(String grievanceId) {
            this.grievanceId = grievanceId;
        }

        public String getEmpCode() {
            return empCode;
        }

        public void setEmpCode(String empCode) {
            this.empCode = empCode;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
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
