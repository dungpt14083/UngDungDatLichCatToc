package com.example.ungdungdatlichcattoc.model;

public class TopStylish {
    private String _id;
    private String nameStylist;
    private String imageStylist;
    private String description;
    private int count;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNameStylist() {
        return nameStylist;
    }

    public void setNameStylist(String nameStylist) {
        this.nameStylist = nameStylist;
    }

    public String getImageStylist() {
        return imageStylist;
    }

    public void setImageStylist(String imageStylist) {
        this.imageStylist = imageStylist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public TopStylish() {
    }

    public TopStylish(String _id, String nameStylist, String imageStylist, String description, int count) {
        this._id = _id;
        this.nameStylist = nameStylist;
        this.imageStylist = imageStylist;
        this.description = description;
        this.count = count;
    }
}
