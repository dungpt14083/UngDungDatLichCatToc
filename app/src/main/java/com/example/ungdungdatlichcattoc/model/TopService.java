package com.example.ungdungdatlichcattoc.model;

public class TopService {
    private String _id;
    private int count;
    private String name;
    private int price;
    private int workTime;
    private String images;
    private String describe;

    public TopService(String _id, int count, String name, int price, int workTime, String images, String describe) {
        this._id = _id;
        this.count = count;
        this.name = name;
        this.price = price;
        this.workTime = workTime;
        this.images = images;
        this.describe = describe;
    }

    public TopService() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
