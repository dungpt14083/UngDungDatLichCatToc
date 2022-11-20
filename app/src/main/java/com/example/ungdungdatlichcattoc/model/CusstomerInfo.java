package com.example.ungdungdatlichcattoc.model;

import java.util.Date;

public class CusstomerInfo {
    private String token;
    private String nameUser;
    private String address;
    private String _id;
    private String password;
    private String created;
    private Date birthOfYear;
    private String phone;

    public CusstomerInfo(String token, String nameUser, String address, String _id, String password, String created, Date birthOfYear, String phone) {
        this.token = token;
        this.nameUser = nameUser;
        this.address = address;
        this._id = _id;
        this.password = password;
        this.created = created;
        this.birthOfYear = birthOfYear;
        this.phone = phone;
    }

    public CusstomerInfo() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Date getBirthOfYear() {
        return birthOfYear;
    }

    public void setBirthOfYear(Date birthOfYear) {
        this.birthOfYear = birthOfYear;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
