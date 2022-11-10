package com.example.ungdungdatlichcattoc.model;

public class HairStylish {
    private String _id;
    private String nameStylist;
    private String description;
    private String imageStylist;
    private String __v;

    public HairStylish() {
    }

    public HairStylish(String _id, String nameStylist, String description, String imageStylist, String __v) {
        this._id = _id;
        this.nameStylist = nameStylist;
        this.description = description;
        this.imageStylist = imageStylist;
        this.__v = __v;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageStylist() {
        return imageStylist;
    }

    public void setImageStylist(String imageStylist) {
        this.imageStylist = imageStylist;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }
}
