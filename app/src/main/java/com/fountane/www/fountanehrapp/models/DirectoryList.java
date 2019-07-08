package com.fountane.www.fountanehrapp.models;

public class DirectoryList {

    private String img, name, position,empCode;

    public DirectoryList(String name, String img, String position,String empCode) {
        this.name = name;
        this.img = img;
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
}
