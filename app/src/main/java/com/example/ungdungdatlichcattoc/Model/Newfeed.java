package com.example.ungdungdatlichcattoc.Model;

import android.media.Image;

public class Newfeed {

    public String _id,title , contentTitle , description;
    public String[] image ;

    public Newfeed(String _id, String title, String contentTitle, String description, String[] image) {
        this._id = _id;
        this.title = title;
        this.contentTitle = contentTitle;
        this.description = description;
        this.image = image;
    }

    public String get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public String getDescription() {
        return description;
    }

    public String[] getImage() {
        return image;
    }
}
