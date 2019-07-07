package com.fountane.www.fountanehrapp.models;

public class DirectoryList {

    private String img, name, position;

    public DirectoryList(String name, String img, String position) {
        this.name = name;
        this.img = img;
        this.position = position;
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
}
