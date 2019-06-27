package com.fountane.www.fountanehrapp.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class personalEmployeeProfileApiModel {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("profile")
    @Expose
    private List<Profile> profile = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Profile> getProfile() {
        return profile;
    }

    public void setProfile(List<Profile> profile) {
        this.profile = profile;
    }

    public class Profile {

        @SerializedName("empCode")
        @Expose
        private String empCode;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("fountaneEmail")
        @Expose
        private String fountaneEmail;
        @SerializedName("personalEmail")
        @Expose
        private String personalEmail;
        @SerializedName("mobileNo")
        @Expose
        private String mobileNo;
        @SerializedName("profilePic")
        @Expose
        private String profilePic;
        @SerializedName("panNo")
        @Expose
        private String panNo;
        @SerializedName("aadharNo")
        @Expose
        private String aadharNo;
        @SerializedName("bankAccountNo")
        @Expose
        private String bankAccountNo;
        @SerializedName("ifscCode")
        @Expose
        private String ifscCode;
        @SerializedName("roleId")
        @Expose
        private String roleId;
        @SerializedName("eduQualification")
        @Expose
        private String eduQualification;
        @SerializedName("fountaneEXP")
        @Expose
        private String fountaneEXP;
        @SerializedName("otherEXP")
        @Expose
        private String otherEXP;
        @SerializedName("designation")
        @Expose
        private String designation;
        @SerializedName("department")
        @Expose
        private String department;
        @SerializedName("branchLocation")
        @Expose
        private String branchLocation;
        @SerializedName("DOB")
        @Expose
        private String dOB;
        @SerializedName("dateOfJoining")
        @Expose
        private String dateOfJoining;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("province")
        @Expose
        private String province;
        @SerializedName("empType")
        @Expose
        private String empType;
        @SerializedName("role_responsibility")
        @Expose
        private String roleResponsibility;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFountaneEmail() {
            return fountaneEmail;
        }

        public void setFountaneEmail(String fountaneEmail) {
            this.fountaneEmail = fountaneEmail;
        }

        public String getPersonalEmail() {
            return personalEmail;
        }

        public void setPersonalEmail(String personalEmail) {
            this.personalEmail = personalEmail;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }

        public String getPanNo() {
            return panNo;
        }

        public void setPanNo(String panNo) {
            this.panNo = panNo;
        }

        public String getAadharNo() {
            return aadharNo;
        }

        public void setAadharNo(String aadharNo) {
            this.aadharNo = aadharNo;
        }

        public String getBankAccountNo() {
            return bankAccountNo;
        }

        public void setBankAccountNo(String bankAccountNo) {
            this.bankAccountNo = bankAccountNo;
        }

        public String getIfscCode() {
            return ifscCode;
        }

        public void setIfscCode(String ifscCode) {
            this.ifscCode = ifscCode;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getEduQualification() {
            return eduQualification;
        }

        public void setEduQualification(String eduQualification) {
            this.eduQualification = eduQualification;
        }

        public String getFountaneEXP() {
            return fountaneEXP;
        }

        public void setFountaneEXP(String fountaneEXP) {
            this.fountaneEXP = fountaneEXP;
        }

        public String getOtherEXP() {
            return otherEXP;
        }

        public void setOtherEXP(String otherEXP) {
            this.otherEXP = otherEXP;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getBranchLocation() {
            return branchLocation;
        }

        public void setBranchLocation(String branchLocation) {
            this.branchLocation = branchLocation;
        }

        public String getDOB() {
            return dOB;
        }

        public void setDOB(String dOB) {
            this.dOB = dOB;
        }

        public String getDateOfJoining() {
            return dateOfJoining;
        }

        public void setDateOfJoining(String dateOfJoining) {
            this.dateOfJoining = dateOfJoining;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getEmpType() {
            return empType;
        }

        public void setEmpType(String empType) {
            this.empType = empType;
        }

        public String getRoleResponsibility() {
            return roleResponsibility;
        }

        public void setRoleResponsibility(String roleResponsibility) {
            this.roleResponsibility = roleResponsibility;
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
