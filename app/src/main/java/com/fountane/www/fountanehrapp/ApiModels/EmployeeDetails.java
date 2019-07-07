package com.fountane.www.fountanehrapp.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeDetails {
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

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("empCode")
        @Expose
        private String empCode;

        private String profilePic, designation;

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

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }
    }
}
