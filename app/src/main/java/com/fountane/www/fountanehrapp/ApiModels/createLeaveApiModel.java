package com.fountane.www.fountanehrapp.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class createLeaveApiModel {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("leavesobj")
    @Expose
    private Leavesobj leavesobj;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Leavesobj getLeavesobj() {
        return leavesobj;
    }

    public void setLeavesobj(Leavesobj leavesobj) {
        this.leavesobj = leavesobj;
    }
    public class Leavesobj {

        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("leaveId")
        @Expose
        private Integer leaveId;
        @SerializedName("empCode")
        @Expose
        private String empCode;
        @SerializedName("leaveType")
        @Expose
        private String leaveType;
        @SerializedName("fromDate")
        @Expose
        private String fromDate;
        @SerializedName("toDate")
        @Expose
        private String toDate;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getLeaveId() {
            return leaveId;
        }

        public void setLeaveId(Integer leaveId) {
            this.leaveId = leaveId;
        }

        public String getEmpCode() {
            return empCode;
        }

        public void setEmpCode(String empCode) {
            this.empCode = empCode;
        }

        public String getLeaveType() {
            return leaveType;
        }

        public void setLeaveType(String leaveType) {
            this.leaveType = leaveType;
        }

        public String getFromDate() {
            return fromDate;
        }

        public void setFromDate(String fromDate) {
            this.fromDate = fromDate;
        }

        public String getToDate() {
            return toDate;
        }

        public void setToDate(String toDate) {
            this.toDate = toDate;
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
