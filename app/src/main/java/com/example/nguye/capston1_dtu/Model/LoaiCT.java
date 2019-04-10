package com.example.nguye.capston1_dtu.Model;

public class LoaiCT {
    private int image;
    private String type;

    public LoaiCT(int image, String type) {
        this.image = image;
        this.type = type;
    }

    public LoaiCT() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
