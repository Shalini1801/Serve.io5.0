package com.spoton.serveio.model;

public class Volunteer {

    String id;
    String name;
    String email;
    String password;
    String phoneNo;
    String location;
    String age;
    String gender;
    String bgroup;
    String vwork;
    String pastexp;


    public Volunteer() {
    }

    public Volunteer(String id, String name, String email, String password, String phoneNo, String location, String age, String gender, String bgroup, String vwork, String pastexp) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.location = location;
        this.age = age;
        this.gender = gender;
        this.bgroup = bgroup;
        this.vwork = vwork;
        this.pastexp = pastexp;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBgroup() {
        return bgroup;
    }

    public void setBgroup(String bgroup) {
        this.bgroup = bgroup;
    }

    public String getVwork() {
        return vwork;
    }

    public void setVwork(String vwork) {
        this.vwork = vwork;
    }

    public String getPastexp() {
        return pastexp;
    }

    public void setPastexp(String pastexp) {
        this.pastexp = pastexp;
    }
}

