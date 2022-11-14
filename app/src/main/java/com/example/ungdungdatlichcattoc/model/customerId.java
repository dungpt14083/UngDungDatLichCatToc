package com.example.ungdungdatlichcattoc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class customerId {
    @SerializedName("nameUser")
    @Expose
    private String nameUser;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("_id")
    @Expose
    private _idOrder id;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("phone")
    @Expose
    private Integer phone;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("birthOfYear")
    @Expose
    private String birthOfYear;
    @SerializedName("__v")
    @Expose
    private Integer v;

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

    public _idOrder getId() {
        return id;
    }

    public void setId(_idOrder id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
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

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public customerId() {
    }

    public customerId(String nameUser, String address, _idOrder id, String password, Integer phone, String created, String birthOfYear, Integer v) {
        this.nameUser = nameUser;
        this.address = address;
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.created = created;
        this.birthOfYear = birthOfYear;
        this.v = v;
    }
}
