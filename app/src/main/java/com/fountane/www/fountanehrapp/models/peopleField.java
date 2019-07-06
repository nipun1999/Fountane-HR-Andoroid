package com.fountane.www.fountanehrapp.models;

public class peopleField {

    public peopleField(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public peopleField(String name, String imageLink, String empCode) {
        this.name = name;
        this.imageLink = imageLink;
        this.empCode = empCode;
    }

    String name,imageLink,empCode;
}
