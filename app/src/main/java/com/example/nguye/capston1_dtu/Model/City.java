package com.example.nguye.capston1_dtu.Model;

public class City {

    private String name;
    private int categoryID;
    public String getName() {
        return name;
    }
    public int getCategoryId() {
        return categoryID;
    }
    public City(String name, int categoryID) {
        this.name = name;
        this.categoryID = categoryID;
    }
    @Override
    public String toString() {
        return name;
    }
}
