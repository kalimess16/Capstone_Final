package com.example.nguye.capston1_dtu.Model;

public class User {
    private String username;
    private String email,city;
    private String password, school;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email,String city, String password, String school) {
        this.email = email;
        this.city=city;
        this.password = password;
        this.school = school;
    }
    public String getCity(){
        return city;
    }
    public void setCity(){
        this.city=city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
