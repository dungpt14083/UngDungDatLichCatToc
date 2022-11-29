package com.example.ungdungdatlichcattoc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponeAvatandNameCustomer {
    @SerializedName("image")
    @Expose
    String image;

    public ResponeAvatandNameCustomer() {
    }

    public ResponeAvatandNameCustomer(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
