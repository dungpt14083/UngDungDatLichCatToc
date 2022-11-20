package com.example.ungdungdatlichcattoc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ProfileCus {
    @SerializedName("_id")
    @Expose
    String _id;
    @SerializedName("password")
    @Expose
    String password;
    @SerializedName("phone")
    @Expose
    String phone;
    @SerializedName("created")
    @Expose
    String created;
    @SerializedName("birthOfYear")
    @Expose
    String birthOfYear;


    @SerializedName("address")
    @Expose
    String address;
    @SerializedName("nameUser")
    @Expose
    String nameUser;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getBirthOfYear() {
        return birthOfYear;
    }

    public void setBirthOfYear(String birthOfYear) {
        this.birthOfYear = birthOfYear;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}
