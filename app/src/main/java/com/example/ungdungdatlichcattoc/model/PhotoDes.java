package com.example.ungdungdatlichcattoc.model;

import java.io.Serializable;

public class PhotoDes implements Serializable {
    private String resouceid;

    public PhotoDes(String resouceid) {
        this.resouceid = resouceid;
    }

    public String getResouceid() {
        return resouceid;
    }

    public void setResouceid(String resouceid) {
        this.resouceid = resouceid;
    }
}
