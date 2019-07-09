package com.fountane.www.fountanehrapp.models;

public class DirectoryList {

    private String img, name, position, empCode, role_responsibility;

    public DirectoryList(String name, String img, String position, String empCode, String role_responsibility) {
        this.name = name;
        this.img = img;
        this.role_responsibility = role_responsibility;
        this.position = position;
        this.empCode = empCode;
    }

    public DirectoryList() {
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getRole_responsibility() {
        return role_responsibility;
    }

    public void setRole_responsibility(String role_responsibility) {
        this.role_responsibility = role_responsibility;
    }
}
