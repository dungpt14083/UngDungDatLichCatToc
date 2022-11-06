package com.example.ungdungdatlichcattoc.model;

public class Service {
    private String _id;
    private String nameService;
    private int price;
    private int dateTime;
    private String describe;
    private String images;
    private String __v;

    public Service(String _id, String nameService, int price, int dateTime, String describe, String images, String __v) {
        this._id = _id;
        this.nameService = nameService;
        this.price = price;
        this.dateTime = dateTime;
        this.describe = describe;
        this.images = images;
        this.__v = __v;
    }

    public Service() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDateTime() {
        return dateTime;
    }

    public void setDateTime(int dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }
}
