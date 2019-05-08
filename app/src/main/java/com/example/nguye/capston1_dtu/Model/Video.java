package com.example.nguye.capston1_dtu.Model;

public class Video {
    private int view;
    private String name;

    public Video(int view, String name) {
        this.view = view;
        this.name = name;
    }

    public Video() {
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
