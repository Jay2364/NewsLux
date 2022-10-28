package com.example.newslux;

public class getset {
    public String name;
    public String desc;
    public int image;

    public getset(String name, String desc, int image){
        this.name = name;
        this.desc = desc;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

